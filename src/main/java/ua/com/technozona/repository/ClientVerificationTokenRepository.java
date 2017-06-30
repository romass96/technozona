package ua.com.technozona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import ua.com.technozona.model.Client;
import ua.com.technozona.model.ClientVerificationToken;

import java.util.Date;
import java.util.stream.Stream;

public interface ClientVerificationTokenRepository extends JpaRepository<ClientVerificationToken, Long> {

    ClientVerificationToken findByToken(String token);

    ClientVerificationToken findByClient(Client client);

    Stream<ClientVerificationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from ClientVerificationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
