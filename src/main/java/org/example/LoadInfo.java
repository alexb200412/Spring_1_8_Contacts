package org.example;

import org.example.model.Contact;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadInfo {

    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> load(String fileName) {
        System.out.println("run LoadInfo from " + fileName);
        FileReader fileReader = null;
        contacts.clear();
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(Constant.ANSI_RED + "Ошибка чтения : " + e.getMessage() + Constant.ANSI_RESET);
            return contacts;
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                System.out.println("Ошибка чтения строки в " + fileName + e.getMessage());
                return contacts;
            }
            readLine(line);
        }

        return contacts;
    }

    private void readLine(String line) {
        String[] words = line.split(";");
        Contact contact = new Contact();
        contact.setName(words[0]);
        contact.setPhone(words[1]);
        contact.setEmail(words[2]);
        contacts.add(contact);
    }
}
