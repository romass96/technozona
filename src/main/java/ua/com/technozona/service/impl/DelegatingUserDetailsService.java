package ua.com.technozona.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.technozona.model.Manager;
import ua.com.technozona.service.interfaces.AdminService;
import ua.com.technozona.service.interfaces.ClientService;
import ua.com.technozona.service.interfaces.ManagerService;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan(basePackages = {"ua.com.technozona.service"} )
public class DelegatingUserDetailsService implements UserDetailsService {


    @Autowired
    private AdminService adminService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ClientService clientService;

    private List<UserDetailsService> userDetailsServices = new ArrayList<>();



    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        RuntimeException last = null;
        for(UserDetailsService uds : userDetailsServices) {
            try {
                return uds.loadUserByUsername(username);
            } catch(RuntimeException error) {
                last = error;
            }
        }
        throw last;
    }

   public DelegatingUserDetailsService addService(UserDetailsService userDetailsService){
        userDetailsServices.add(userDetailsService);
        return this;
   }

   public void initialize(){
       this.userDetailsServices.add(clientService);
       this.userDetailsServices.add(adminService);
       this.userDetailsServices.add(managerService);
   }
}