package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    private String name;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return name+"|"+phone+"|"+email;
    }
}
