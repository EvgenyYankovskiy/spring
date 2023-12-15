package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ContactsBook {
    private final List<Contact> contacts = new ArrayList<>();

    public void printInfo() {
        if (contacts.isEmpty()) {
            System.out.println("Contacts list is empty");
        } else {
            System.out.println("All contacts:");
            contacts.forEach(Contact::printPersonInfo);
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact " + contact + " added");
    }

    public void deleteByEmail(String email) {
        Contact contact = findByEmail(email);
        if (contact == null) {
            System.out.println("Contact not fount");
        } else {
            contacts.remove(contact);
            System.out.println("Contact " + contact + " deleted");
        }
    }

    private Contact findByEmail(String email) {
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
