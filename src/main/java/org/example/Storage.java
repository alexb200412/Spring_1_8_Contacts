package org.example;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void load() {
        System.out.println("run Load from " + fileName);
    }
}
