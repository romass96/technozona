package ua.com.technozona.repository;

import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;

import java.util.List;

public interface ManagerRepository extends MainRepository<Manager,Long> {

    Manager findByEmail(String email);

    void deleteByEmail(String email);

}