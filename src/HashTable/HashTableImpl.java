package HashTable;

import static HashTable.UtilityFunctions.getSmallerPrime;
import static HashTable.UtilityFunctions.isPrime;

public class HashTableImpl {

    private final String[] hashTableSlots;
    int primeTableSize = 0; // m'
    private final int hashTableSize; //m
    private int elementsCounterInHashTable = 0; //j
    int collisionCounter = 0;

    public HashTableImpl(int slotsNumber) {
        if (isPrime(slotsNumber)) {
            hashTableSize = slotsNumber;
        } else {
            int primeNumber = getSmallerPrime(slotsNumber);
            hashTableSize = primeNumber;
            System.out.println("This hash table size: " + slotsNumber + " is not a prime number");
            System.out.println("The size has been changed to: " + primeNumber);
        }
        hashTableSlots = new String[hashTableSize];
        primeTableSize = getSmallerPrime(hashTableSize);
    }

    //Retrieves the current number of entries in the hash table
    public int getCurrentAmountOfEntries() {
        return elementsCounterInHashTable;
    }

    // Calculates and prints current rate of fulfillment of the hash table in percent
    public void getRateOfFullfilment() {
        int percentage = 100 * elementsCounterInHashTable / hashTableSize;
        System.out.println("The table is filled up to: " + percentage + "%");
    }

    private int firstHashingFunction(String entryString) {
        int hashCode = entryString.hashCode();
        hashCode = hashCode % hashTableSize;
        if (hashCode < 0) {
            hashCode = hashCode + hashTableSize;
        }
        return hashCode;
    }

    //I had problems with the second hash function and had to look up an example implementation
    //and as a result implemented it in the way was shown here:
    //https://www.geeksforgeeks.org/java-program-to-implement-hash-tables-with-double-hashing/
    private int secondHashingFunction(String entryString) {
        int hashCode = entryString.hashCode() % hashTableSize;
        if (hashCode < 0) {
            hashCode = hashCode + hashTableSize;
        }
        return primeTableSize - hashCode % primeTableSize;
    }

    public void insert(String entryString) {
        if (elementsCounterInHashTable == hashTableSize) {
            System.out.println("Table is already full");
            return;
        }

        int hashCode = firstHashingFunction(entryString);
        int stepSize = secondHashingFunction(entryString);
        int i = 0;

        while (hashTableSlots[hashCode] != null) {
            if(String.valueOf(hashTableSlots[hashCode]).equals(entryString)) {
                return;
            }
            // Updates the hash code using the step size and handles negative hash codes
            hashCode = hashCode - (stepSize * i +1) % hashTableSize; //hi(k)=(h(k)−s(k)∗i)mod m
            if (hashCode < 0) {
                hashCode += hashTableSize;
            }
            collisionCounter++;
            i++;
        }

        hashTableSlots[hashCode] = entryString;
        System.out.println("String: '" + entryString + "' has been added successfully");
        elementsCounterInHashTable++;
    }

    /**
     * Searches for a specific string in the hash table and returns it if found
     *
     * @param searchedString the string to search for
     * @return the found string, or null if not found
     */
    public String search(String searchedString) {
        int hashCode = firstHashingFunction(searchedString);
        int stepSize = secondHashingFunction(searchedString);
        int i = 0;

        while (hashTableSlots[hashCode] != null) {
            if (hashTableSlots[hashCode].equals(searchedString)) {
                return hashTableSlots[hashCode];
            }
            // Updates the hash code using the step size and handles negative hash codes
            hashCode = hashCode - (stepSize * i +1) % hashTableSize; //hi(k)=(h(k)−s(k)∗i)mod m
            if (hashCode < 0) {
                hashCode += hashTableSize;
            }
            i++;
        }

        return hashTableSlots[hashCode];
    }

    /**
     * Deletes a string from the hash table.
     * @param deletedString the string to be deleted
     */
    public void delete(String deletedString) {
        if (search(deletedString) != null) {
            int hashCode = firstHashingFunction(deletedString);
            int stepSize = secondHashingFunction(deletedString);
            int i = 0;

            while (hashTableSlots[hashCode] != null) {
                // If the current slot matches the deleted string, delete the string
                if (hashTableSlots[hashCode].equals(deletedString)) {
                    hashTableSlots[hashCode] = null;
                    System.out.println("String: '" + deletedString + "' has been deleted successfully");
                    elementsCounterInHashTable--;
                    return;
                }
                // Updates the hash code using the step size and handles negative hash codes
                hashCode = hashCode - (stepSize * i +1) % hashTableSize; //hi(k)=(h(k)−s(k)∗i)mod m
                if (hashCode < 0) {
                    hashCode += hashTableSize;
                }
                i++;
            }
        } else {
            System.out.println("String: '" + deletedString + "' was not found in the hash table");        }
    }
    /**
     * Retrieves the number of collisions that have occurred during insertion.
     * (To check the efficiency and runtime of the insert method)
     *
     * @return the collision count
     */
    public int getCollisionCount() {
        return collisionCounter;
    }

    public void printHashTable() {
        for (int i = 0; i < hashTableSize; i++) {
            System.out.println("Index " + i + ": " + hashTableSlots[i]);
        }
    }
}