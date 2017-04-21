package ua.com.technozona.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.technozona.service.impl.DelegatingUserDetailsService;


@Configuration
@EnableWebSecurity
//@ComponentScan(basePackages = {"ua.com.technozona.service", "ua.com.technozona.config"} )
public class MultiHttpSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new DelegatingUserDetailsService();
    }

//    @Autowired
//    private DelegatingUserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        userDetailsService.initialize();
//        auth.userDetailsService(userDetailsService);
//    }

    @Configuration
    @Order(2)
    public static class ClientWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


        public ClientWebSecurityConfigurerAdapter(){
            super();
        }

        @Override
        protected void configure(final HttpSecurity httpSecurity)
                throws Exception {
            httpSecurity.authorizeRequests()
                    .antMatchers("/**").permitAll();
            httpSecurity.formLogin().loginPage("/main").defaultSuccessUrl("/main").loginProcessingUrl("client_login")
                    .usernameParameter("email").passwordParameter("password");
            httpSecurity.logout().logoutUrl("/client_logout").logoutSuccessUrl("/main").permitAll();
            httpSecurity.rememberMe().key("remember-me-key-client")
                    .rememberMeParameter("remember-me-client")
                    .rememberMeCookieName("remember-me-cookie-client");

        }

    }

    @Configuration
    @Order(1)
    public static class EmployeeWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomSuccessHandler customSuccessHandler;

        public EmployeeWebSecurityConfigurerAdapter(){
            super();
        }

        @Override
        protected void configure(final HttpSecurity httpSecurity)
                throws Exception {
            httpSecurity.authorizeRequests()
                    .antMatchers("/admin**").access("hasRole('ADMIN')")
                    .antMatchers("/manager**").access("hasRole('ADMIN') and hasRole('MANAGER')");
            httpSecurity.formLogin().loginPage("/loginAdmin").successHandler(customSuccessHandler).loginProcessingUrl("admin_login")
                    .usernameParameter("email").passwordParameter("password");
            httpSecurity.logout().logoutUrl("/admin_logout").logoutSuccessUrl("/loginAdmin").permitAll();
            httpSecurity.rememberMe().key("remember-me-key")
                    .rememberMeParameter("remember-me")
                    .rememberMeCookieName("remember-me-cookie");
        }

    }



}

