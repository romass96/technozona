package ua.com.technozona.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.com.technozona.service.impl.DelegatingUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ua.com.technozona.service", "ua.com.technozona.config"} )
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    DelegatingUserDetailsService userDetailsService;

    @Override
    protected void configure(final HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/manager/**").access("hasRole('ADMIN') and hasRole('MANAGER')");
        httpSecurity.formLogin().loginPage("/login").successHandler(customSuccessHandler)
                .usernameParameter("email_signin").passwordParameter("password_signin");
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
        httpSecurity.rememberMe().key("remember-me-key")
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me-cookie");


    }

    @Override
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        userDetailsService.initialize();
        builder.userDetailsService(userDetailsService);
    }

}
