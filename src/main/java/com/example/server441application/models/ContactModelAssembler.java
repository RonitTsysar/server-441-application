package com.example.server441application.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.server441application.controllers.ContactController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 *  It is based on converting a non-resource object (Contact) into a resource-based object (EntityModel<Contact>).
 */
@Component
public class ContactModelAssembler implements RepresentationModelAssembler<Contact, EntityModel<Contact>>
{
    @Override
    // EntityModel - is a generic container from Spring HATEOAS,
    // that includes not only the data but a collection of links.
    public EntityModel<Contact> toModel(Contact contact) {

        return new EntityModel<>(contact,
                linkTo(methodOn(ContactController.class).findContact(contact.getPhoneNumber())).withSelfRel(),
                linkTo(methodOn(ContactController.class).getAllContacts()).withRel("contacts"));
    }
}
