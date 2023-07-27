package mcrmilenial.appsebookViewerbackend.securitys;

import mcrmilenial.appsebookViewerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/api/addUser").permitAll()
                .antMatchers(HttpMethod.GET, "/api/getUser").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/updateUser/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/updateUser/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/delete/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/getUser/{id}").permitAll()

                .antMatchers(HttpMethod.POST,"/apikey/addRoles").permitAll()
                .antMatchers(HttpMethod.GET,"/apikey/getRoles").permitAll()
                .antMatchers(HttpMethod.PUT,"/apikey/updateRoles/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/apikey/deleteRoles/{id}").permitAll()
                .anyRequest().authenticated();
    }
}