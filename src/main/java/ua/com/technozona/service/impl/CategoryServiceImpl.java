package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Category;
import ua.com.technozona.repository.CategoryRepository;
import ua.com.technozona.service.interfaces.CategoryService;


import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.dao")
public final class CategoryServiceImpl extends MainServiceImpl<Category> implements CategoryService {



    private final CategoryRepository repository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(final CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Category get(final String url) throws WrongInformationException, BadRequestException {
        if (isBlank(url)) {
            throw new WrongInformationException("No category URL!");
        }
        final Category category = this.repository.findByUrl(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        return category;
    }

    @Override
    @Transactional
    public void remove(final String url) throws WrongInformationException {
        if (isBlank(url)) {
            throw new WrongInformationException("No category URL!");
        }
        this.repository.deleteByUrl(url);
    }
}
