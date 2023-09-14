package mcrmilenial.appsebookViewerbackend.securitys;

import mcrmilenial.appsebookViewerbackend.securitys.jwt.AccessDeniedHandlerJwt;
import mcrmilenial.appsebookViewerbackend.securitys.jwt.AuthEntryPointJwt;
import mcrmilenial.appsebookViewerbackend.securitys.jwt.AuthTokenFilter;
import mcrmilenial.appsebookViewerbackend.securitys.jwt.ExceptionHandlerJwt;
import mcrmilenial.appsebookViewerbackend.securitys.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    /*
        fungsi bean securityfilterchain ini digunakan untuk security akses yang akan diberikan kepada
        end point/api
     */
    @Autowired
    private AccessDeniedHandlerJwt deniedHandler;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(deniedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/api/**").permitAll().anyRequest().authenticated();
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    /*
        fungsi DaoAuthenticationProvider ini digunakan untuk proses validasi identitas
        yang akan menggunakan/memasukin pada suatu system yang disimpan pada database
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    /*
        fungsi authTokenFilter digunakan untuk validasi token pengguna yang nantinya akan
        mengakses end point/api
     */
    @Bean
    AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }
    @Bean
    public ExceptionHandlerJwt exceptionHandler() {
        return new ExceptionHandlerJwt();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://10.10.16.38:8080");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST, "/api/addUser").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/getUser").permitAll()
//                .antMatchers(HttpMethod.PUT, "/api/updateUser/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/delete/{id}").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/getUser/{id}").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/apikey/addRoles").permitAll()
//                .antMatchers(HttpMethod.GET,"/apikey/getRoles").permitAll()
//                .antMatchers(HttpMethod.PUT,"/apikey/updateRoles/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/apikey/deleteRoles/{id}").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/myapi/addBibliographic").permitAll()
//                .antMatchers(HttpMethod.GET,"/myapi/getBibliographic").permitAll()
//                .antMatchers(HttpMethod.PUT,"/myapi/updateBibliographic/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/myapi/deleteBibliographic/{id}").permitAll()
//                .anyRequest().authenticated();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }

}