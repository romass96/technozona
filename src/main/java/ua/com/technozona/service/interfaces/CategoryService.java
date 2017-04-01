package ua.com.technozona.service.interfaces;

import ua.com.technozona.model.Category;


public interface CategoryService extends MainService<Category> {

    Category get(String url);

    void remove(String url);
}
