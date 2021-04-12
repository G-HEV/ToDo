package com.ghev.ToDo.sec;


import com.ghev.ToDo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private String password;
    private String username;
    private String role;
    private List<GrantedAuthority> authorities;
    private boolean active;



    @Autowired
    public MyUserDetails(User user) {
        this.password =user.getPassword();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.active=user.isActive();
        this.authorities= Arrays.stream(user.getRole()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }
}
