package org.contacts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactsBook {
    private ContactsMap contactsMap;
    @Value("${app.default-contacts}")
    String path;

    public ContactsBook(ContactsMap contactsMap) {
        this.contactsMap = contactsMap;
    }

    public void setContact(Contact contact) {
        contactsMap.getMap().put(contact.getEmail(), contact);
    }

    public void printContacts() {
        for(String key: contactsMap.getMap().keySet()) {
            Contact contact = contactsMap.getMap().get(key);
            System.out.println(MessageFormat.format("{0}| {1}| {2}",
                    contact.getFullName(),
                    contact.getPhoneNumber(), contact.getEmail()));
        }
    }

    public void deleteContactByEmail(String email) {
        if (contactsMap.getMap().containsKey(email)) {
            contactsMap.getMap().remove(email);
            System.out.println(MessageFormat.format("Контакт с e-mail {0} удалён.", email ));
        }
        else {
            System.out.println(MessageFormat.format("Контакт с e-mail {0} не найден.", email ));
        }
    }

    @PreDestroy
    public void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (String key : contactsMap.getMap().keySet()) {
            Contact contact = contactsMap.getMap().get(key);
            lines.add(contact.getFullName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail());
        }

        try {
            Files.write(Paths.get(path), lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Контакты сохранены в файл.");
    }

}
