package ua.com.technozona.repository;

import ua.com.technozona.enums.RoleEnum;
import ua.com.technozona.model.Role;


public interface RoleRepository extends MainRepository<Role, Long> {

    Role findByTitle(RoleEnum title);

    void deleteByTitle(RoleEnum title);
}
