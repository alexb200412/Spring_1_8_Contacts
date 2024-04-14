package org.example;

import org.example.model.Contact;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ContactsManager {
    private final EnvContact envContact;
    private List<Contact> contacts;

    public ContactsManager(EnvContact envContact) {
        this.envContact = envContact;
        contacts = new ArrayList<>();
    }

    public void work() {
        contacts = envContact.init();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Constant.ANSI_BLUE + "Введите команду:");
            System.out.println("LIST                 - вывести список контактов");
            System.out.println("ADD FIO;PHONE;EMAIL  - добавить контакт");
            System.out.println("DEL EMAIL            - удалить контакт c указанным email");
            System.out.println("SAVE                 - сохранить контакты в файл " + envContact.file4save());
            System.out.println("EXIT                 - выйти из приложения" + Constant.ANSI_RESET);
            String command = scanner.nextLine().trim();
            switch (command.split(" ")[0].toUpperCase()) {
                case "LIST": {
                    doList();
                    break;
                }
                case "ADD": {
                    doInsert(command);
                    break;
                }
                case "DEL": {
                    doDelete(command);
                    break;
                }
                case "SAVE": {
                    doSave();
                    break;
                }
                case "EXIT": {
                    return;
                }
                default:
                    System.out.println(Constant.ANSI_RED + "Неверная команда: " + command + Constant.ANSI_RESET);
            }
            System.out.println("------------------------");
        }
    }

    private void doList() {
        if (contacts.size() < 1) {
            System.out.println("Нет данных");
            return;
        }
        contacts.stream().forEach(System.out::println);
    }

    private void doDelete(String command) {
        if (command.split(" ").length != 2) {
            System.out.println(Constant.ANSI_RED + "Неверно указан email" + Constant.ANSI_RESET);
            return;
        }
        String email = command.split(" ")[1].toUpperCase();
        List<Contact> removed = contacts.stream().filter(c -> c.getEmail().toUpperCase().equals(email)).collect(Collectors.toList());
        if (removed.size() < 1) {
            System.out.println("Нет данных с указанным email: " + command.split(" ")[1]);
            return;
        }
        contacts.removeAll(removed);
    }

    private void doInsert(String command) {
        if (command.split(" ").length != 2) {
            System.out.println(Constant.ANSI_RED + "Неверно указан контакт" + Constant.ANSI_RESET);
            return;
        }
        String line = command.split(" ")[1];
        String[] words = line.split(";");

        if (words.length != 3) {
            System.out.println(Constant.ANSI_RED + "Неверно указан контакт, должно быть 3 поля" + Constant.ANSI_RESET);
            return;
        }
        Contact contact = new Contact();
        contact.setName(words[0]);
        contact.setPhone(words[1]);
        contact.setEmail(words[2]);
        contacts.add(contact);
    }

    private void doSave() {
        try {
            FileWriter writer = new FileWriter(envContact.file4save());
            contacts.stream().forEach(c -> {
                try {
                    writer.write(c.getName() + ";" + c.getPhone() + ";" + c.getEmail() + '\n');
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
