package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Category;
import ua.com.technozona.model.Product;
import ua.com.technozona.repository.CategoryRepository;
import ua.com.technozona.repository.ProductRepository;
import ua.com.technozona.service.interfaces.ProductService;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;


@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public final class ProductServiceImpl
        extends MainServiceImpl<Product>
        implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ProductServiceImpl(
            final ProductRepository productRepository,
            final CategoryRepository categoryRepository
    ) {
        super(productRepository);
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByUrl(final String url) throws WrongInformationException, BadRequestException {
        if (isBlank(url)) {
            throw new WrongInformationException("No product URL!");
        }
        final Product product = this.productRepository.findByUrl(url);
        if (product == null) {
            throw new BadRequestException("Can't find product by url " + url + "!");
        }
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByArticle(final int article)
            throws BadRequestException {
        final Product product = this.productRepository.findByArticle(article);
        if (product == null) {
            throw new BadRequestException("Can't find product by article " + article + "!");
        }
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryUrl(final String url)
            throws WrongInformationException, BadRequestException {
        if (isBlank(url)) {
            throw new WrongInformationException("No category URL!");
        }
        final Category category = this.categoryRepository.findByUrl(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        return this.productRepository.findByCategoryId(category.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryId(final Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No category id!");
        }
        return this.productRepository.findByCategoryId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(
            final int size,
            final Long id
    ) {
        return getRandomByCategoryId(size, id, -1L);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(
            final int size,
            final Long categoryId,
            final Long differentProductId
    ) throws WrongInformationException {
        if (categoryId == null || differentProductId == null) {
            throw new WrongInformationException("No category or product id!");
        }
        final List<Product> products = this.productRepository.findByCategoryId(categoryId);
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        products.remove(this.productRepository.findOne(differentProductId));
        return getShuffleSubList(products, 0, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandom(final int size) {
        final List<Product> products = this.productRepository.findAll();
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        return getShuffleSubList(products, 0, size);
    }

    @Override
    @Transactional
    public void removeByUrl(final String url) throws WrongInformationException {
        if (isBlank(url)) {
            throw new WrongInformationException("No product URL!");
        }
        this.productRepository.deleteByUrl(url);
    }

    @Override
    @Transactional
    public void removeByArticle(final int article) {
        this.productRepository.deleteByArticle(article);
    }

    @Override
    @Transactional
    public void removeByCategoryUrl(final String url)
            throws WrongInformationException, BadRequestException {
        if (isBlank(url)) {
            throw new WrongInformationException("No category URL!");
        }
        final Category category = this.categoryRepository.findByUrl(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        this.productRepository.delete(productRepository.findByCategoryId(category.getId()));
    }

    @Override
    @Transactional
    public void removeByCategoryId(final Long id)
            throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        if (this.categoryRepository.findOne(id) == null) {
            throw new BadRequestException("Can't find category by id " + id + "!");
        }
        this.productRepository.delete(productRepository.findByCategoryId(id));
    }

    private static List<Product> getShuffleSubList(
            final List<Product> products,
            final int start,
            final int end
    ) {
        if ((products == null) || (products.isEmpty()) ||
                (start > products.size()) ||
                (start > end) || (start < 0) || (end < 0)) {
            return new ArrayList<>();
        }
        Collections.shuffle(products);
        return products.subList(
                start,
                end <= products.size() ? end : products.size()
        );
    }
}
