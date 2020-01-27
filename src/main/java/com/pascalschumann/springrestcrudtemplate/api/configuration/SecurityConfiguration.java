package com.pascalschumann.springrestcrudtemplate.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pascalschumann.springrestcrudtemplate.api.model.User;

/**
 * Configures global basic auth for the service
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final User admin;

    @Autowired
    public SecurityConfiguration(final User admin) {
        this.admin = admin;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER")
                        .and().withUser("admin").password("{noop}" + admin.getPassword())
                        .roles("ADMIN");

    }
}
