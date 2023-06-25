import HashTable.HashTableImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        HashTableImpl hashTable = new HashTableImpl(300000);

        String filename = "names.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the line by comma (assuming CSV format)
                String name = data[0]; // Extract the desired data from the line
                hashTable.insert(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        hashTable.printHashTable();
        System.out.println(hashTable.getCurrentAmountOfEntries());
        hashTable.getCollisionCount();

        // Delete a specific string from the hash table
        String deletedString = "Alice";
        hashTable.delete(deletedString);


        // Search for a specific string in the hash table
        String searchString = "John";
        String result = hashTable.search(searchString);
        if (result != null) {
            System.out.println("String '" + searchString + "' found: " + result);
        } else {
            System.out.println("String '" + searchString + "' not found.");
        }

    }
}
