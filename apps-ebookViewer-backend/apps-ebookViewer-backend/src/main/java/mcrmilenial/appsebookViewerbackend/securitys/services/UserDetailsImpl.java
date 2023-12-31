package mcrmilenial.appsebookViewerbackend.securitys.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import mcrmilenial.appsebookViewerbackend.entities.Roles;
import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int detail_id;

    private String username;

    private String email;
    @JsonIgnore
    private String password;
    public static Set<StatusRoles> grantedRoles = new HashSet<>();

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(
            int detail_id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.detail_id = detail_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().getName())
        );

        return new UserDetailsImpl(
                user.getUser_id(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}

