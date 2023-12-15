package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileWorker {
    @Value("${app.path}")
    private String path;
    @Value("${app.fileName}")
    private String filename;
    @Autowired
    private ContactsBook contactsBook;

    public void readFile() {
        try {
            List<String> lines = Files.readAllLines(Path.of(path + filename));
            for (String line : lines) {
                String[] fragment = line.split(";");
                Contact contact = new Contact();
                contact.setFullName(fragment[0]);
                contact.setPhoneNumber(fragment[1]);
                contact.setEmail(fragment[2]);
                contactsBook.addContact(contact);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile() {
        File file = new File(path + filename);
        try {
            FileWriter writer = new FileWriter(file);
            for (Contact contact : contactsBook.getContacts()) {
                writer.write(contact.getFullName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
