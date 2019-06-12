package com.brighttalk.userrealm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

/*realm entity*/
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"realmName"})})
@JacksonXmlRootElement(localName = "realm")
public class UserRealm implements RealmResponseInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("id")
    private int id;

    @JacksonXmlProperty(localName = "name",isAttribute = true)
    @JsonProperty("name")
    private String realmName;

    @JsonProperty("description")
    private String realmDescription;

    @JsonProperty("key")
    private String realmEncryptionKey;


    public UserRealm(String realmName, String realmDescription, String realmEncryptionKey) {
        this.realmName = realmName;
        this.realmDescription = realmDescription;
        this.realmEncryptionKey = realmEncryptionKey;
    }

    public UserRealm() {
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmEncryptionKey(String realmEncryptionKey) {
        this.realmEncryptionKey = realmEncryptionKey;
    }
}
