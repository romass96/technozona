package ua.com.technozona.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.technozona.model.Admin;
import ua.com.technozona.model.Employee;
import ua.com.technozona.model.Manager;

import java.util.List;

/**
 * Created by Roma on 14.04.2017.
 */
public interface EmployeeService extends UserDetailsService{
    void addAdmin(Admin admin);

    void addManager(Manager manager);

    void addAdmins(List<Admin> admins);

    void addManagers(List<Manager> managers);

    void updateAdmin(Admin admin);

    void updateManager(Manager manager);

    Admin getAdmin(Long id);

//    Employee getEmployee(Long id);

    Manager getManager(Long id);

    Admin getAdminByEmail(String email);

    Manager getManagerByEmail(String email);

    Admin getMainAdministrator();

    List<Employee> getAllEmployees();

    List<Admin> getAllAdmins();

    List<Manager> getAllManagers();

    void removeAdmin(Admin admin);

    void removeAdmin(Long id);

    void removeManager(Manager manager);

    void removeManager(Long id);

    void removeAdminByEmail(String email);

    void removeManagerByEmail(String email);

    void removeAllEmployees();

    void removeAllAdmins();

    void removeAllManagers();
}
