package ua.com.technozona.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.com.technozona.service.impl.DelegatingUserDetailsService;
import ua.com.technozona.service.interfaces.ClientService;

//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = {"ua.com.technozona.service"} )
//@Order(1)
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private ClientService userDetailsService;
//
//    @Override
//    protected void configure(final HttpSecurity httpSecurity)
//            throws Exception {
//        httpSecurity.authorizeRequests()
//                .antMatchers("/").permitAll();
//        httpSecurity.formLogin().loginPage("/").defaultSuccessUrl("/")
//                .usernameParameter("email").passwordParameter("password");
//        httpSecurity.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
//        httpSecurity.rememberMe().key("remember-me-key-client")
//                .rememberMeParameter("remember-me-client")
//                .rememberMeCookieName("remember-me-cookie-client");
//
//
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService);
//    }

}
