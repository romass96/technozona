package ua.com.technozona.repository;

import ua.com.technozona.model.Client;

public interface ClientRepository extends MainRepository<Client, Long> {

    Client findByEmail(String email);

    void deleteByEmail(String email);
}