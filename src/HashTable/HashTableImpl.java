package HashTable;

import java.util.Objects;

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

    public int getElementsCounterInHashTable() {
        int count = 0;
        for (String slot : hashTableSlots) {
            if (slot != null) {
                count++;
            }
        }
        return count;
    }
    public int getCurrentAmountOfEntries() {
        return elementsCounterInHashTable;
    }

    public void getRateOfFullfilment() {
        int percentage = 100 * getElementsCounterInHashTable() / hashTableSize;
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

        while (hashTableSlots[hashCode] != null) {
            if(Objects.equals(hashTableSlots[hashCode], entryString)) {
                return;
            }
            hashCode = (hashCode + stepSize) % hashTableSize;
            collisionCounter++;
        }

        hashTableSlots[hashCode] = entryString;
        System.out.println("String: '" + entryString + "' has been added successfully");
        elementsCounterInHashTable++;
    }

    public String search(String searchedString) {
        int hashCode = firstHashingFunction(searchedString);
        int stepSize = secondHashingFunction(searchedString);
        int i = 0;

        while (hashTableSlots[hashCode] != null) {
            if (hashTableSlots[hashCode].equals(searchedString)) {
                return hashTableSlots[hashCode];
            }

            hashCode = (hashCode - stepSize * i) % hashTableSize;
            if (hashCode < 0) {
                hashCode += hashTableSize;
            }
            i++;
        }

        return hashTableSlots[hashCode];
    }

    public void delete(String deletedString) {
        if (search(deletedString) != null) {
            int hashCode = firstHashingFunction(deletedString);
            int stepSize = secondHashingFunction(deletedString);
            int i = 0;

            while (hashTableSlots[hashCode] != null) {
                if (hashTableSlots[hashCode].equals(deletedString)) {
                    hashTableSlots[hashCode] = null;
                    System.out.println("String: '" + deletedString + "' has been deleted successfully");
                    elementsCounterInHashTable--;
                    return;
                }
                hashCode = (hashCode - stepSize * i) % hashTableSize;
                if (hashCode < 0) {
                    hashCode += hashTableSize;
                }
                i++;
            }
        } else {
            System.out.println("String: '" + deletedString + "' was not found in the hash table");        }
    }

    public int getCollisionCount() {
        return collisionCounter;
    }

    public void printHashTable() {
        for (int i = 0; i < hashTableSize; i++) {
            System.out.println("Index " + i + ": " + hashTableSlots[i]);
        }
    }
}