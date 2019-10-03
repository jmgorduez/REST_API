package com.gestorinc.security.service;

import com.gestorinc.repository.IBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.gestorinc.utils.Constants.USER;

@Component
public class BankDetailsService implements UserDetailsService {

    @Autowired
    private IBankRepository bankRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return bankRepository.findByCodigoAcceso(username)
                .orElseThrow(this::throwUsernameNotFoundException);
    }

    private UsernameNotFoundException throwUsernameNotFoundException(){
        return new UsernameNotFoundException("");
    }
}