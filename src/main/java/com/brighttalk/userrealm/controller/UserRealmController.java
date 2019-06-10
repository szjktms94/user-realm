package com.brighttalk.userrealm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRealmController {

    @GetMapping
    public String check() {
        return "check";
    }
}
