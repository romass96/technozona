package ua.com.technozona.repository;

import ua.com.technozona.model.Product;

import java.util.List;


public interface ProductRepository extends MainRepository<Product, Long> {

    Product findByUrl(String url);

    void deleteByUrl(String url);


    List<Product> findByCategoryId(long id);
}
