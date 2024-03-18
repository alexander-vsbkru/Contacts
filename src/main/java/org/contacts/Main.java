package org.contacts;

import org.contacts.config.DefaultAppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        ContactsBook contactsBook = context.getBean(ContactsBook.class);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            if ((command.equals("close"))||(command.equals("0"))) {
                context.getBeanFactory().destroyBean(contactsBook);
                break;
            }
            if (command.equals("list")) {
                contactsBook.printContacts();
                continue;
            }
            if (command.equals("delete")) {
                String email = input.split(" ")[1];
                contactsBook.deleteContactByEmail(email);
                continue;
            }
            if (command.equals("save")) {
                contactsBook.saveToFile();
                continue;
            }

            boolean inputError = false;
            String fullName = "";
            String phone = "";
            String email = "";

            String[] contactData = input.split(";");

            if (contactData.length == 3) {
                fullName = contactData[0].trim();
                phone = contactData[1].trim();
                email = contactData[2].trim();
                String regex = "[A-z-]+";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(fullName);
                int i = 0;
                while (matcher.find()) {
                    i++;
                }

                if (i == 3) System.out.println(fullName);
                if (!(i == 3)) {
                    inputError = true;
                }

                regex = "\\+[0-9]{11}";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(phone);

                if (!matcher.find()) {
                    inputError = true;
                }
                else System.out.println(phone);

                regex = "^(.+)@([^@]+[^.])$";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(email);

                if (!matcher.find()) {
                    inputError = true;
                }
                else System.out.println(email);

            }
            else inputError = true;

            if (inputError) {
                System.out.println("Неверный формат ввода." ); //+ System.lineSeparator());
                System.out.println("Формат ввода: Фамилия имя Отчество; +79999999999; email@example.example");
            }
            else {
                Contact contact = new Contact(fullName, phone, email);
                contactsBook.setContact(contact);
                System.out.println("Контакт сохранен");
            }
        }
    }
}
