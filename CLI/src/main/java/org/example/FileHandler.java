package org.example;

public interface FileHandler {
    void saveToFile(String filePath);
    boolean loadFromFile(String filePath);
}
