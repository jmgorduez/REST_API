package com.gestorinc.security.service;

import com.gestorinc.repository.IBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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