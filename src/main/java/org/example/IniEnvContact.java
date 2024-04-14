package org.example;

import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class IniEnvContact implements EnvContact {
    @Value("${source.filename}")
    private String printFileName;
    @Value("${source.ini.filename}")
    private String iniFileName;
    @Autowired
    private LoadInfo loadInfo;

    @Override
    public List<Contact> init() {
        System.out.println(printFileName + "!");
        System.out.println("iniFileName=" + "src/main/resources/" + iniFileName);
        return loadInfo.load(iniFileName);
    }

    @Override
    public String file4save() {
        return printFileName;
    }
}
