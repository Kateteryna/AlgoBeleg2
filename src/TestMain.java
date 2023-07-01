import HashTable.HashTableImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static HashTable.UtilityFunctions.countUniqueEntiesinCSV;

public class TestMain {
    public static void main(String[] args) {
        String filename = "names.csv";

        // Set the initial size of the hash table based on the count of unique entries in the CSV file
        HashTableImpl hashTable = new HashTableImpl((int) (countUniqueEntiesinCSV(filename)*1.2));

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                hashTable.insert(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        hashTable.printHashTable();
        System.out.println("CurrentAmountOfEntries: " + hashTable.getCurrentAmountOfEntries());
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
        hashTable.delete("Johnnie");

        System.out.println("CurrentAmountOfEntries: " + hashTable.getCurrentAmountOfEntries());
        System.out.println("Collisions: " + hashTable.getCollisionCount());

        hashTable.getRateOfFullfilment();

    }
}
