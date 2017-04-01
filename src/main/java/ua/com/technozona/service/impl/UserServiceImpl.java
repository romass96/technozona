package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Role;
import ua.com.technozona.model.User;
import ua.com.technozona.repository.RoleRepository;
import ua.com.technozona.repository.UserRepository;
import ua.com.technozona.service.interfaces.UserService;


import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;


@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public final class UserServiceImpl
        extends MainServiceImpl<User>
        implements UserService, UserDetailsService {

    /**
     * ID роли клиента в базе данных.
     */
    private final static Long CLIENT_ROLE_ID = 1L;

    /**
     * ID роли адмиистратора в базе данных.
     */
    private final static Long ADMIN_ROLE_ID = 2L;

    /**
     * ID роли менеджера в базе данных.
     */
    private final static Long MANAGER_ROLE_ID = 3L;

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(final UserRepository repository, final RoleRepository roleRepository) {
        super(repository);
        this.repository = repository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws WrongInformationException, BadRequestException {
        if (isBlank(name)) {
            throw new WrongInformationException("No user name!");
        }
        final User user = this.repository.findByName(name);
        if (user == null) {
            throw new BadRequestException("Can't find user by name " + name + "!");
        }
        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public User getByEmail(final String email)
            throws WrongInformationException, BadRequestException {
        if (isBlank(email)) {
            throw new WrongInformationException("No username!");
        }
        final User user = this.repository.findByEmail(email);
        if (user == null) {
            throw new BadRequestException("Can't find user by email " + email + "!");
        }
        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws BadRequestException {
        final User user = this.repository.findAllByRole(this.roleRepository.findOne(ADMIN_ROLE_ID)).get(0);
        if (user == null) {
            throw new BadRequestException("Can't find administrator!");
        }
        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getAdministrators() {
        return this.repository.findAllByRole(this.roleRepository.findOne(ADMIN_ROLE_ID));
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getManagers() {
        return this.repository.findAllByRole(this.roleRepository.findOne(MANAGER_ROLE_ID));
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getClients() {
        return this.repository.findAllByRole(this.roleRepository.findOne(CLIENT_ROLE_ID));
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getPersonnel() {
        final List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }


    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
            user = null;
        }
        return user;
    }


    @Override
    @Transactional
    public void removeByName(final String name) throws WrongInformationException {
        if (isBlank(name)) {
            throw new WrongInformationException("No username!");
        }
        this.repository.deleteByName(name);
    }


    @Override
    @Transactional
    public void removeByRole(final Role role) throws WrongInformationException {
        if (role == null) {
            throw new WrongInformationException("No user role!");
        }
        this.repository.deleteAllByRole(role);
    }

    @Override
    @Transactional
    public void removePersonnel() {
        final List<User> personnel = getPersonnel();
        if (personnel.isEmpty()) {
            return;
        }
        personnel.remove(getMainAdministrator());
        this.repository.delete(personnel);
    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getByEmail(s);
    }
}
