package ua.com.technozona.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "managers")
public class Manager extends Employee implements UserDetails {

    @Transient
    private String status;


    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "manager",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Manager(){
        super();
        this.status = "Менеджер";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        return roles;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
