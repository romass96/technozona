package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.EmailExistsException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Client;
import ua.com.technozona.model.ClientDTO;
import ua.com.technozona.model.ClientVerificationToken;
import ua.com.technozona.repository.ClientRepository;
import ua.com.technozona.repository.ClientVerificationTokenRepository;
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
    private ClientVerificationTokenRepository tokenRepository;



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

    @Transactional
    @Override
    public Client registerNewClientAccount(ClientDTO accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "Аккаунт с email " +  accountDto.getEmail() +  " уже существует");
        }
        final Client client = new Client();
        client.setFirstName(accountDto.getFirstName());
        client.setLastName(accountDto.getLastName());
        client.setEmail(accountDto.getEmail());
        client.setPassword(accountDto.getPassword());
        client.setPhone(accountDto.getPhone());
        repository.save(client);
        return client;
    }


    private boolean emailExist(String email) {
        Client client = repository.findByEmail(email);
        if (client != null) {
            return true;
        }
        return false;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email);
    }

    @Override
    public ClientVerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredClient(Client client) {
        repository.save(client);
    }

    @Override
    public void createVerificationToken(Client client, String token) {
        ClientVerificationToken myToken = new ClientVerificationToken(token, client);
        tokenRepository.save(myToken);
    }
}