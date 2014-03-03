package pl.wszib.kolo.service;

import java.util.List;

import pl.wszib.kolo.model.Contact;

public interface ContactService {
	
	public void addContact(Contact contact);

	public List<Contact> listContacts();

	public void removeContact(Integer id);
}
