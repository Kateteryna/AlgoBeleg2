package HashTable;

import static HashTable.UtilityFunctions.getNextPrime;
import static HashTable.UtilityFunctions.isPrime;

public class HashTableImpl {

    private final String[] hashTableSlots;
    private final int hashTableSize;
    private int elementsCounterInHashTable = 0;
    int collisionCounter = 0;

    public HashTableImpl(int slotsNumber) {
        if (isPrime(slotsNumber)) {
            hashTableSize = slotsNumber;
            hashTableSlots = new String[hashTableSize];
        } else {
            int primeNumber = getNextPrime(slotsNumber);
            hashTableSize = primeNumber;
            hashTableSlots = new String[hashTableSize];
            System.out.println("This hash table size: " + slotsNumber + " is not a prime number");
            System.out.println("The size has been changed to: " + primeNumber);
        }
    }

    public int getCurrentAmountOfEntries() {
        return elementsCounterInHashTable;
    }

    public void getRateOfFullfilment() {
        int percentage = 100 * getCurrentAmountOfEntries() / hashTableSize;
        System.out.println("The table is filled up to: " + percentage + "%");
    }

    private int firstHashingFunction(String entryString) {
        int hashCode = entryString.hashCode();
        hashCode = hashCode % hashTableSize;
        if (hashCode < 0) {
            hashCode += hashTableSize;
        }
        return hashCode;
    }

    private int secondHashingFunction(String entryString, int mPrime) {
        int hashCode = 1 + (entryString.hashCode() % mPrime);
        return hashCode;
    }

    public void insert(String entryString) {
        if (elementsCounterInHashTable == hashTableSize) {
            System.out.println("Table is already full");
            return;
        }

        int hashCode = firstHashingFunction(entryString);
        int stepSize = secondHashingFunction(entryString, hashTableSize - 1);
        int i = 0;

        while (hashTableSlots[hashCode] != null) {
            hashCode = (hashCode - stepSize * i) % hashTableSize;
            if (hashCode < 0) {
                hashCode += hashTableSize;
            }
            i++;
            collisionCounter++;
        }

        hashTableSlots[hashCode] = entryString;
        System.out.println("String: '" + entryString + "' has been added successfully");
        elementsCounterInHashTable++;
    }

    public String search(String searchedString) {
        int hashCode = firstHashingFunction(searchedString);
        int stepSize = secondHashingFunction(searchedString, hashTableSize - 1);
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
            int stepSize = secondHashingFunction(deletedString, hashTableSize - 1);
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

    public void getCollisionCount() {
        System.out.println("The number of occurred collisions is: " + collisionCounter);
    }

    public void printHashTable() {
        for (int i = 0; i < hashTableSize; i++) {
        System.out.println("Index " + i + ": " + hashTableSlots[i]);
        }
    }
}