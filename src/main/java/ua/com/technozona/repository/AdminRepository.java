package ua.com.technozona.repository;

import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Employee;

import java.util.List;

public interface AdminRepository extends MainRepository<Admin,Long> {

    Admin findByEmail(String email);

    void deleteByEmail(String email);

}
