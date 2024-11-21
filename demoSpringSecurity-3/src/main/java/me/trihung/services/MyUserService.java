package me.trihung.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.trihung.entity.Role;
import me.trihung.entity.Users;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserService implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Users user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName())); // Assuming role names are used as authorities
        }
        return authorities; // Make sure to return the list
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Return the password from the Users entity
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // Return the username from the Users entity
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can implement your own logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can implement your own logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can implement your own logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Return the enabled status from the Users entity
    }
}
