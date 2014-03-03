package pl.wszib.kolo.dao;

import java.util.List;

import pl.wszib.kolo.model.Contact;

public interface ContactDAO {
	
	public void addContact(Contact contact);

	public List<Contact> listContacts();

	public void removeContact(Integer id);
}