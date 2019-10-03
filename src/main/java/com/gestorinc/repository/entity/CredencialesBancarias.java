package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.gestorinc.utils.Constants.USER;

@Entity
@Table(name = "TRA_CFG_CREDEN_ENTIDAD_FINANC")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredencialesBancarias implements Serializable, UserDetails {

    private static final long serialVersionUID = 7955620518359150939L;

    @EmbeddedId
    private CredencialesBancariasPK pk;
    @Column(name = "COD_ACCESO", nullable = false, length = 100)
    private String codigoAcceso;
    @Column(name = "TOKEN_ACCESO", nullable = false, length = 300)
    private String tokenAcceso;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(USER)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return tokenAcceso;
    }

    @Override
    public String getUsername() {
        return codigoAcceso;
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