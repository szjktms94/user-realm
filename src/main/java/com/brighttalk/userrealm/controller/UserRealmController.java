package com.brighttalk.userrealm.controller;

import com.brighttalk.userrealm.model.RealmError;
import com.brighttalk.userrealm.model.RealmResponseInterface;
import com.brighttalk.userrealm.model.UserRealm;
import com.brighttalk.userrealm.service.UserRealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserRealmController {

    @Autowired
    private UserRealmService userRealmService;

    @PostMapping(value = "/service/user/realm", consumes = {MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public RealmResponseInterface saveUserRealm(@RequestBody UserRealm userRealm, HttpServletResponse response) {
        RealmResponseInterface result = userRealmService.saveUserRealm(userRealm);

        if(result instanceof RealmError) {
            response.setStatus(((RealmError)result).getHttpErrorStatusCode());
        } else if (result instanceof UserRealm) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }

        return result;
    }

    @GetMapping(value ="/service/user/realm/{id}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RealmResponseInterface getUserRealmById(@PathVariable(name = "id") String id, HttpServletResponse response) {
        RealmResponseInterface result = userRealmService.getUserRealmById(id);

        if(result instanceof RealmError) {
            response.setStatus(((RealmError)result).getHttpErrorStatusCode());
        }

        return result;
    }
}
