package ua.com.technozona.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.technozona.exception.EmailExistsException;
import ua.com.technozona.model.Client;
import ua.com.technozona.model.ClientDTO;
import ua.com.technozona.model.ClientVerificationToken;

import java.util.List;

public interface ClientService extends MainService<Client>, UserDetailsService {


    Client getByEmail(String email);

    Client registerNewClientAccount(ClientDTO accountDto) throws EmailExistsException;

    ClientVerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredClient(Client client);

    void createVerificationToken(Client client, String token);

    List<Client> getAllClients();

    void removeByEmail(String email);

}