package ua.com.technozona.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Manager;

public interface ManagerService extends MainService<Manager>, UserDetailsService {

    Manager getByEmail(String email);

    void removeByEmail(String email);

}
