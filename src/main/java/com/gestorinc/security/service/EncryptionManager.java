package com.gestorinc.security.service;

import com.gestorinc.security.service.abstracts.IEncryptionManager;
import org.springframework.stereotype.Component;

@Component
public class EncryptionManager implements IEncryptionManager {

    @Override
    public String encrypt(String value) {
        return value;
    }
}
