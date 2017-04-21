package ua.com.technozona.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;

import java.util.List;

public interface AdminService extends MainService<Admin>, UserDetailsService {

    Admin getByEmail(String email);

    Admin getMainAdministrator();

    void removeByEmail(String email);

}
