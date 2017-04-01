package ua.com.technozona.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ua.com.technozona.service", "ua.com.technozona.config"} )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/").access("hasAnyRole('CLIENT','MANAGER','ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/manager/**").access("hasRole('ADMIN') and hasRole('MANAGER')");
        httpSecurity.formLogin().loginPage("/login").successHandler(customSuccessHandler)
                .usernameParameter("email_signin").passwordParameter("password_signin");
        httpSecurity.logout().permitAll().logoutUrl("/logout");
        httpSecurity.rememberMe().key("remember-me-key").rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me-cookie");

    }

    @Override
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailsService);
    }
}
