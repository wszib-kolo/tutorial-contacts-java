package pl.wszib.kolo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wszib.kolo.dao.ContactDAO;
import pl.wszib.kolo.model.Contact;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
    private ContactDAO contactDAO;
     
    @Transactional
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }
 
    @Transactional
    public List<Contact> listContacts() {
        return contactDAO.listContacts();
    }
 
    @Transactional
    public void removeContact(Integer id) {
        contactDAO.removeContact(id);
    }
}
