package com.example.server441application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.server441application.model.Contact;

/**
 * This interface extends Spring Data JPA’s JpaRepository,
 * specifying the domain type as Contact and the phoneNumber type as String.
 */
public interface IContactRepository extends JpaRepository<Contact, String> {}
