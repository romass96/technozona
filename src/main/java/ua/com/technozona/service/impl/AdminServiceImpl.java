package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Admin;
import ua.com.technozona.repository.AdminRepository;
import ua.com.technozona.service.interfaces.AdminService;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public class AdminServiceImpl
        extends MainServiceImpl<Admin>
        implements AdminService {


    private final AdminRepository repository;


    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminServiceImpl(final AdminRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Admin getByEmail(final String email)
            throws WrongInformationException, BadRequestException {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        Admin admin = this.repository.findByEmail(email);
        if (admin == null) {
            throw new BadRequestException("Can't find admin by email " + email + "!");
        }
        return admin;
    }


    @Override
    public Admin getMainAdministrator() {
        return this.repository.findOne(1L);
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
