package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Client;
import ua.com.technozona.repository.ClientRepository;
import ua.com.technozona.service.interfaces.ClientService;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public class ClientServiceImpl
        extends MainServiceImpl<Client>
        implements ClientService {


    private final ClientRepository repository;


    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ClientServiceImpl(final ClientRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Client getByEmail(final String email)
            throws WrongInformationException, BadRequestException {
        if (isBlank(email)) {
            throw new WrongInformationException("No email!");
        }
        final Client client = this.repository.findByEmail(email);
        if (client == null) {
            throw new BadRequestException("Can't find client by email " + email + "!");
        }
        return client;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return this.repository.findAll();
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