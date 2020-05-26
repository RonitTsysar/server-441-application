package com.example.server441application.api;

import com.example.server441application.model.Contact;
import com.example.server441application.service.ContactService;
import org.springframework.web.bind.annotation.*;

@RestController // indicates that the data returned by each method
                // will be written straight into the response body.
public class ContactController
{
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    //GET - retrieve the user
    @GetMapping("/contacts/{phoneNumber}")
    Contact findContact(@PathVariable String phoneNumber){
        return contactService.findContactByNumber(phoneNumber);
    }

    //POST - add another user and phone
    @PostMapping("/contacts")
    Contact newContact(@RequestBody Contact newContact){
        return contactService.addNewContact(newContact);
    }

    //PUT - update user name for phone number
    @PutMapping("/contacts/{phoneNumber}")
    Contact replaceContact(@RequestBody Contact newContact, @PathVariable String phoneNumber){
        return contactService.updateContact(newContact,phoneNumber);
    }

    //DELETE – delete phone number
    @DeleteMapping("/contacts/{phoneNumber}")
    void deleteContact(@PathVariable String phoneNumber){
        contactService.deleteContact(phoneNumber);
    }
}