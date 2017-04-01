package ua.com.technozona.service.interfaces;

import ua.com.technozona.model.Role;
import ua.com.technozona.model.User;

import java.util.List;


public interface UserService extends MainService<User> {

    User getByName(String name);

    User getByEmail(String email);

    User getMainAdministrator();

    List<User> getAdministrators();

    List<User> getManagers();

    List<User> getClients();

    List<User> getPersonnel();

    User getAuthenticatedUser();

    void removeByName(String name);

    void removeByRole(Role role);

    void removePersonnel();
}
