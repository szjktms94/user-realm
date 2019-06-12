package com.brighttalk.userrealm.controller;

import com.brighttalk.userrealm.model.RealmError;
import com.brighttalk.userrealm.model.RealmResponseInterface;
import com.brighttalk.userrealm.model.UserRealm;
import com.brighttalk.userrealm.service.UserRealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRealmController {

    @Autowired
    private UserRealmService userRealmService;

    @PostMapping(value = "/realm/save", consumes = {MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public RealmResponseInterface saveUserRealm(@RequestBody UserRealm userRealm) {
        return userRealmService.saveUserRealm(userRealm);
    }

    @GetMapping(value = "/getrealm", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RealmResponseInterface getUserRealmById(@RequestParam String id) {
        return userRealmService.getUserRealmById(id);
    }
}
