package com.ega.banking.entity;

import com.ega.banking.constants.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank(message = ApplicationConstants.INVALID_FIRST_NAME)
    @Size(min = 3, max = 10, message = ApplicationConstants.INVALID_FIRST_NAME)
    private String firstName;

    @NotBlank(message = ApplicationConstants.INVALID_LAST_NAME)
    @Size(min = 3, max = 10, message = ApplicationConstants.INVALID_LAST_NAME)
    private String lastName;

    @NotBlank(message = ApplicationConstants.INVALID_PASSWORD)
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+).com$", message = ApplicationConstants.INVALID_EMAIL)
    @Column(unique = true)
    @NotBlank
    private String email;

    private long accountId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
