package com.gestorinc.security.service;

import com.gestorinc.security.service.abstracts.IEncryptionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static com.gestorinc.utils.Constants.ERROR_AL_ENCRIPTAR_UNA_VALUE_CON_EL_ALGORITMO_SHA_512;

@Component
@Slf4j
public class EncryptionManager implements IEncryptionManager {

    private String segments[] = new String[4];

    @Override
    public String encryptPassword(String value) {
        try {
            return toByteArrayToString(encryptPasswordSHA512(value));
        } catch (Exception e) {
            log.error(ERROR_AL_ENCRIPTAR_UNA_VALUE_CON_EL_ALGORITMO_SHA_512, e);
            return null;
        }
    }

    private String encryptPasswordSHA512(String value) {
        try {
            int path = value.length() % 6;
            String encodeSalt = getEncodeSalt(value, path);
            int form = encodeSalt.hashCode() % 4;
            obtainSegments(encodeSalt);
            return buildResult(value, form);
        } catch (Exception e) {
            log.error(ERROR_AL_ENCRIPTAR_UNA_VALUE_CON_EL_ALGORITMO_SHA_512, e);
            return null;
        }
    }

    private String toByteArrayToString(String value) {
        return Base64.getEncoder()
                .encodeToString(
                        value.getBytes());
    }


    private String buildResult(String value, int form) {
        switch (form) {
            case 0:
                return DigestUtils.sha512Hex(segments[1] + value + segments[0]);
            case 1:
                return DigestUtils.sha512Hex(segments[2] + value + segments[3]);
            case 2:
                return DigestUtils.sha512Hex(segments[3] + value + segments[1]);
            default:
                return DigestUtils.sha512Hex(segments[0] + value + segments[2]);
        }
    }

    private void obtainSegments(String encodeSalt) {
        segments[0] = encodeSalt.substring(0, 8);
        segments[1] = encodeSalt.substring(8, 16);
        segments[2] = encodeSalt.substring(16, 24);
        segments[3] = encodeSalt.substring(24, 32);
    }

    private String getEncodeSalt(String cadena, int path) {
        String encodeSalt;
        if (path < 2) {
            encodeSalt = DigestUtils.md2Hex(cadena);
        } else if (path < 4) {
            encodeSalt = DigestUtils.md5Hex(cadena);
        } else {
            encodeSalt = DigestUtils.sha1Hex(cadena).substring(8);
        }
        return encodeSalt;
    }

}
