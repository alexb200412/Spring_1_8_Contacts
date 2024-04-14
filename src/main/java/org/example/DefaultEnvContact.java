package org.example;

import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultEnvContact implements EnvContact {
    @Value("${source.filename}")
    private String printFileName;

    @Override
    public List<Contact> init() {
        System.out.println("run DefaultContacts");
        System.out.println(printFileName);
        List<Contact> contacts = new ArrayList<>();
        return contacts;
    }

    @Override
    public String file4save() {
        return printFileName;
    }
}
