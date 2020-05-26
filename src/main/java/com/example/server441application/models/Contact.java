package com.example.server441application.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data //is a Lombok annotation to create all the methods, based on the fields.
@Entity //is a JPA annotation to make this object ready for storage in a JPA-based data store.
public class Contact
{
    private @Id String phoneNumber;
    private String name = "contact without a name";

    public Contact(){};

    public Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}