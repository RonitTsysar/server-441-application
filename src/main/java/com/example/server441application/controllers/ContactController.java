package com.example.server441application.controllers;

import com.example.server441application.models.Contact;
import com.example.server441application.models.ContactModelAssembler;
import com.example.server441application.services.ContactService;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;

@RestController // indicates that the data returned by each method
                // will be written straight into the response body.
public class ContactController
{
    private final ContactService contactService;
    private final ContactModelAssembler assembler;

    public ContactController(ContactService contactService, ContactModelAssembler assembler) {
        this.contactService = contactService;
        this.assembler = assembler;
    }

    //GET - retrieve all users
    @GetMapping("/contacts")
    public CollectionModel<EntityModel<Contact>> getAllContacts() {

        List<EntityModel<Contact>> contacts = contactService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(contacts,
                linkTo(methodOn(ContactController.class).getAllContacts()).withSelfRel());
    }

    //GET - retrieve the user
    // EntityModel - is a generic container from Spring HATEOAS,
    // that includes not only the data but a collection of links.
    @GetMapping("/contacts/{phoneNumber}")
    public EntityModel<Contact> findContact(@PathVariable String phoneNumber){

        Contact contact = contactService.findContactByNumber(phoneNumber);
        return assembler.toModel(contact);
    }

    //POST - add another user and phone
    @PostMapping("/contacts")
    Contact newContact(@RequestBody Contact newContact){
        return contactService.addNewContact(newContact);
    }

    //PUT - update user name for phone number
    @PutMapping("/contacts/{phoneNumber}")
    Contact replaceContact(@RequestBody Contact newContact, @NonNull @PathVariable String phoneNumber){
        return contactService.updateContact(newContact,phoneNumber);
    }

    //DELETE – delete phone number
    @DeleteMapping("/contacts/{phoneNumber}")
    void deleteContact(@PathVariable String phoneNumber){
        contactService.deleteContact(phoneNumber);
    }
}