package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    // Save an object to a JSON file
    public static <T> void saveToFile(T object, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Enable pretty-printing
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(object, writer); // Serialize the object to JSON
            System.out.println("Object saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving object to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load an object from a JSON file
    public static <T> T loadFromFile(String jsonFilePath, Class<T> clazz) {
        File file = new File(jsonFilePath);

        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            System.out.println("File is empty or does not exist. Returning null.");
            return null;
        }

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(file)) {
            // Attempt to deserialize the file content into the specified class type
            return gson.fromJson(reader, clazz);
        } catch (JsonSyntaxException e) {
            System.out.println("Invalid JSON content: Cannot parse as " + clazz.getSimpleName() + ". Returning null.");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("Error reading the file. Returning null.");
            e.printStackTrace();
            return null;
        }
    }
}
