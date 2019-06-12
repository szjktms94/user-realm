package com.brighttalk.userrealm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Error")
public class RealmError implements RealmResponseInterface {
    private String code;

    @JsonIgnore
    private int httpErrorStatusCode;

    public RealmError(String code, int httpErrorStatusCode) {
        this.code = code;
        this.httpErrorStatusCode = httpErrorStatusCode;
    }

    public RealmError() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHttpErrorStatusCode() {
        return httpErrorStatusCode;
    }

    public void setHttpErrorStatusCode(int httpErrorStatusCode) {
        this.httpErrorStatusCode = httpErrorStatusCode;
    }
}
