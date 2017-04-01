package ua.com.technozona.repository;

import ua.com.technozona.enums.StatusEnum;
import ua.com.technozona.model.Status;

public interface StatusRepository extends MainRepository<Status, Long> {

    Status findByTitle(StatusEnum title);

    void deleteByTitle(StatusEnum title);
}
