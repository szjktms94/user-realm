package com.brighttalk.userrealm.service;

import com.brighttalk.userrealm.model.RealmError;
import com.brighttalk.userrealm.model.RealmResponseInterface;
import com.brighttalk.userrealm.model.UserRealm;
import com.brighttalk.userrealm.repository.UserRealmRepository;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserRealmService {

    @Autowired
    private UserRealmRepository userRealmRepository;

    public RealmResponseInterface saveUserRealm(UserRealm userRealm) {

        if(Objects.equals(userRealm.getRealmName(), "") || userRealm.getRealmName() == null) {
            return new RealmError("InvalidRealmName");
        }

        UserRealm lastInsertedRealm;
        try {
            userRealm.setRealmEncryptionKey(generate32EncryptionKey());
            lastInsertedRealm = userRealmRepository.save(userRealm);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new RealmError("DuplicatedRealmName");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new RealmError("WrongEncryptionKeyGeneration");
        }
        return lastInsertedRealm;
    }

    public RealmResponseInterface  getUserRealmById(String id) {
        UserRealm resultUserRealm;
        try {
            //parse string instead of receiving direct int request param to be able
            // to handle the exception and create the error object
            resultUserRealm = userRealmRepository.findById(Integer.parseInt(id)).get();
        } catch (NoSuchElementException e) {
            //using the exception instead of null check by finding element by id
            e.printStackTrace();
            return new RealmError("RealmNotFound");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new RealmError("InvalidArgument");
        }
        return resultUserRealm;
    }

    private String generate32EncryptionKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(192);
        SecretKey key = keyGenerator.generateKey();
        return Base64.encode(key.getEncoded());
    }
}
