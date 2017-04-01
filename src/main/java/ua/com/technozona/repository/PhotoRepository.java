package ua.com.technozona.repository;

import ua.com.technozona.model.Photo;


public interface PhotoRepository extends MainRepository<Photo, Long> {

    Photo findByTitle(String title);

    void deleteByTitle(String title);
}
