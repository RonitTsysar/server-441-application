package com.example.server441application.services;

import com.example.server441application.dao.IContactRepository;
import com.example.server441application.exceptions.AlreadyExistsException;
import com.example.server441application.exceptions.ContactNotFoundException;
import com.example.server441application.models.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService
{
    private final IContactRepository repository;

    public ContactService(IContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> findAll() {
        return repository.findAll();
    }

    public Contact findContactByNumber(String phoneNumber){
        return repository.findById(phoneNumber)
                .orElseThrow(() -> new ContactNotFoundException(phoneNumber));
    }

    public Contact addNewContact(Contact newContact)
    {
        if(repository.existsById(newContact.getPhoneNumber()))
        {
            throw new AlreadyExistsException(newContact.getPhoneNumber());
        }
        return repository.save(newContact);
    }

    public Contact updateContact(Contact newContact,String phoneNumber)
    {
        return repository.findById(phoneNumber)
                .map(Contact -> {
                    Contact.setName(newContact.getName());
                    Contact.setPhoneNumber(newContact.getPhoneNumber());
                    return repository.save(Contact);
                })
                .orElseGet(() -> {
                    newContact.setPhoneNumber(phoneNumber);
                    return repository.save(newContact);
                });
    }
    public void deleteContact(String phoneNumber) {

        if(!repository.existsById(phoneNumber))
            throw new ContactNotFoundException(phoneNumber);

        repository.deleteById(phoneNumber);
    }
}