package com.example.server441application.service;

import com.example.server441application.dao.IContactRepository;
import com.example.server441application.exception.ContactNotFoundException;
import com.example.server441application.model.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactService
{
    private final IContactRepository repository;

    public ContactService(IContactRepository repository){
        this.repository = repository;
    }
    public Contact findContactByNumber(String phoneNumber){
        return repository.findById(phoneNumber)
                .orElseThrow(() -> new ContactNotFoundException(phoneNumber));
    }
    public Contact addNewContact(Contact newContact){
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
        repository.deleteById(phoneNumber);
    }
}

/*
@Service
public class PersonService {

    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("FakeDao") PersonDao personDao)
    {
        this.personDao = personDao;
    }

    public int addPerson(Person person)
    {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople()
    {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonByID(UUID id)
    {
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id)
    {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person)
    {
        return personDao.updatePersonById(id,person);
    }
}

 */