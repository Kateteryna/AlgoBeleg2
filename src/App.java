import HashTable.HashTableImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the hash table management!\n");
        System.out.println("Enter the size of a hash table");

        HashTableImpl hashTable = new HashTableImpl(scan.nextInt() );
        char chosenOptionChar;

        do
        {
            System.out.println("\nPlease, enter what operation you want to do\n");
            System.out.println("Enter 0 to INSERT some item");
            System.out.println("Enter 1 to INSERT items from a csv. file");
            System.out.println("Enter 2 to REMOVE a certain item");
            System.out.println("Enter 3 to SEARCH for a certain item");
            System.out.println("Enter 4 to check how FULL the hash table is(Belegungsgrad)");
            System.out.println("Enter 5 to print the content of hash table");
            System.out.println("\nEnter 6 to EXIT");
            int choice = scan.nextInt();


            String filename = "names.csv";

            String line;

            switch (choice)
            {
                case 0:
                    System.out.println("Enter an item you want to insert");
                    hashTable.insert(scan.next());
                    break;
                case 1:
                    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                        while((line = br.readLine()) != null) {
                            String[] data = line.split(",");
                            String name = data[0];
                            hashTable.insert(name);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2 :
                    System.out.println("Enter what item you want to delete:");
                    hashTable.delete( scan.next() );
                    break;
                case 3 :
                    System.out.println("Enter an item you want to look up:");
                    String entry = hashTable.search( scan.next() );
                    if(entry!= null) {
                        System.out.println("Item = "+ entry + "is in the table");
                    } else {
                        System.out.println("There is no such item in the table");
                    }
                    break;
                case 4 :
                    hashTable.getRateOfFullfilment();
                    break;
                case 5 :
                    System.out.println("\nCurrent state of a hash table\n");
                    hashTable.printHashTable();
                    break;
                case 6 :
                    System.exit(0);
                    break;
                default :
                    System.out.println("Wrong Entry\n ");
                    break;
            }

            System.out.println("\nType 'y', if you want to continue, otherwise the app will terminate\n");
            chosenOptionChar = scan.next().charAt(0);
        }
        while (chosenOptionChar == 'Y'|| chosenOptionChar == 'y');
    }
}
