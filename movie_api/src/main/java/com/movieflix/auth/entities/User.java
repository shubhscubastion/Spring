package com.movieflix.auth.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "Field can't be blank")
    private String name;

    @NotBlank(message = "Field can't be blank")
    @Column(unique=true)    
    private String username;

    @NotBlank(message = "Field can't be blank")
    @Column(unique=true)
    @Email(message="Please enter proper email format!")
    private String email;

    @NotBlank(message = "Field can't be blank")
    @Size(min=5, message="The password must have at least 5 characters")
    private String password;

    @OneToOne(mappedBy="user")
    private RefreshToken refreshToken;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean isEnabled=true;
    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return isEnabled;
    }

}
