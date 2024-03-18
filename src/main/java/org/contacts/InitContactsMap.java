package org.contacts;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitContactsMap implements ContactsMap{
    @Value("${app.default-contacts}")
    String path;
    Map<String, Contact> contactsMap = new HashMap<>();

    @Override
    public Map<String, Contact> getMap() {
        return contactsMap;
    }

    @PostConstruct
    public void loadFromFile(){
        File file = new File(path);
        List<String> contactsFromFile = new ArrayList<>();
        try {
            contactsFromFile.addAll(Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!contactsFromFile.isEmpty()) {
           for(String line : contactsFromFile) {
               String[] record = line.split(";");
               Contact contact = new Contact(record[0].trim(), record[1].trim(), record[2].trim());
               contactsMap.put(contact.getEmail(), contact);
           }
        }
    }
}
