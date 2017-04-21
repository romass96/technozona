package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Manager;
import ua.com.technozona.repository.ManagerRepository;
import ua.com.technozona.service.interfaces.ManagerService;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public class ManagerServiceImpl
        extends MainServiceImpl<Manager>
        implements ManagerService {


    private final ManagerRepository repository;


    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ManagerServiceImpl(final ManagerRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Manager getByEmail(final String email)
            throws WrongInformationException, BadRequestException {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        Manager manager = this.repository.findByEmail(email);
        if (manager == null) {
            throw new BadRequestException("Can't find manager by email " + email + "!");
        }
        return manager;
    }



    @Override
    @Transactional
    public void removeByEmail(final String email) throws WrongInformationException {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        this.repository.deleteByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getByEmail(s);
    }
}