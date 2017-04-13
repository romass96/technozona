package ua.com.technozona.service.interfaces;

import org.springframework.data.domain.Page;
import ua.com.technozona.model.Model;
import ua.com.technozona.service.impl.MainServiceImpl;

import java.util.List;

public interface MainService<T extends Model> {

    void add(T model);

    void add(List<T> models);

    void update(T model);

    T get(Long id);

    List<T> getAll();


    void remove(T model);

    void remove(Long id);

    void remove(List<T> models);

    void removeAll();
}
