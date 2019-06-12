package com.brighttalk.userrealm.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Error")
public class RealmError implements RealmResponseInterface {
    private String code;

    public RealmError(String code) {
        this.code = code;
    }

    public RealmError() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
