package ua.com.technozona.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.technozona.model.Client;

import java.util.List;

public interface ClientService extends MainService<Client>, UserDetailsService {


    Client getByEmail(String email);

    List<Client> getAllClients();

    void removeByEmail(String email);

}