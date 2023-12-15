package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ContactsBook contactsBook = context.getBean(ContactsBook.class);
        FileWorker fileWorker = context.getBean(FileWorker.class);

        fileWorker.readFile();
        while (true) {
            System.out.println("Enter the command: ADD, DELL, INFO");
            Scanner enterCommand = new Scanner(System.in);
            String enter = enterCommand.nextLine().toUpperCase(Locale.ROOT).trim();
            switch (enter) {
                case "ADD":
                    System.out.println("Add a contact in the format: FULLNAME;PHONENUMBER;EMAIL");
                    Scanner addContact = new Scanner(System.in);
                    String add = addContact.nextLine().toUpperCase().trim();
                    String[] fragments = add.split("\\p{Punct}");

                    newContact(fragments, contactsBook);
                    break;
                case "DELL":
                    System.out.println("Enter email contact for delete:");
                    Scanner deleteContact = new Scanner(System.in);
                    String delete = deleteContact.nextLine().toUpperCase().trim();
                    contactsBook.deleteByEmail(delete);
                    break;
                case "INFO":
                    contactsBook.printInfo();
                    break;
                default:
                    System.out.println("Wrong command");
            }
            fileWorker.writeFile();
        }
    }

    private static void newContact(String[] fragments, ContactsBook contactsBook) {
        if (fragments.length == 3) {
            Contact contact = new Contact();
            contact.setFullName(fragments[0].trim());
            contact.setPhoneNumber(fragments[1].trim());
            contact.setEmail(fragments[2].trim());
            contactsBook.addContact(contact);
        } else {
            System.out.println("Invalid Input");
        }
    }
}