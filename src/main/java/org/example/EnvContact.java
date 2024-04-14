package org.example;

import org.example.model.Contact;

import java.util.List;

public interface EnvContact {
    List<Contact> init();

    String file4save();
}
