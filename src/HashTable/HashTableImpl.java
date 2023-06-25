package HashTable;

import static HashTable.UtilityFunctions.getNextPrime;
import static HashTable.UtilityFunctions.isPrime;

public class HashTableImpl {

    private int hashTableSize;
    private String[] hashTableSlots;

    private int currentHashTableSize;
    private int elementsCounterInHashTable = 0;
    int collisionCounter = 0;

    public HashTableImpl(int slotsNumber) {
        hashTableSize = slotsNumber;
        if(isPrime(slotsNumber)) {
            hashTableSlots = new String[slotsNumber];
            currentHashTableSize = slotsNumber;
        } else {
            int primeNumber = getNextPrime(slotsNumber);
            hashTableSlots = new String[primeNumber];
            currentHashTableSize = primeNumber;

            System.out.println("This hash table size: " + slotsNumber + " - is not a prime number");
            System.out.println("The size has been changed into: " + primeNumber);
        }
    }

    public void getCurrentAmountOfEntries() {
        System.out.println("The amount of elements in the table is: " +elementsCounterInHashTable);
    }

    private int firstHashingFunction(String entryString) {
        int hashCode = entryString.hashCode();
        hashCode = hashCode % currentHashTableSize;
        if(hashCode < 0) {
            hashCode += currentHashTableSize;
        }
        return hashCode;
    }

    private int secondHashingFunction(String entryString) {
        int hashCode = entryString.hashCode();
        hashCode = hashCode % currentHashTableSize;
        if(hashCode < 0){
            hashCode += currentHashTableSize;
        }
        return 7 - hashCode % 7; // step size
    }

    public void insert(String entryString) { //using here linear probing rto handle collissions
        if(elementsCounterInHashTable==hashTableSize) {
            System.out.println("Table is already full");
            return;
        }
        int hashCode = firstHashingFunction(entryString);
        int stepSize = secondHashingFunction(entryString);

        while (hashTableSlots[hashCode] != null) {
            hashCode = hashCode + stepSize;
            hashCode = hashCode % currentHashTableSize;
            collisionCounter++; // Increment collision counter
        }
        hashTableSlots[hashCode] = entryString;
        System.out.println("String: '"+ entryString + "' has been added successful");
        elementsCounterInHashTable++;
    }

    public String search(String searchedString) {
        int hashCode = firstHashingFunction(searchedString);
        int stepSize = secondHashingFunction(searchedString);
        while (hashTableSlots[hashCode] != null) {
            if(hashTableSlots[hashCode].equals(searchedString)) {
                return hashTableSlots[hashCode];
            }
            hashCode = hashCode + stepSize;
            hashCode = hashCode % currentHashTableSize;
        }
        return hashTableSlots[hashCode];
    }

    public void delete(String deletedString) {
        if (search(deletedString) != null) {
            int hashCode = firstHashingFunction(deletedString);
            int stepSize = secondHashingFunction(deletedString);
            while (hashTableSlots[hashCode] != null) {
                if (hashTableSlots[hashCode].equals(deletedString)) {
                    hashTableSlots[hashCode] = null;
                    System.out.println("String: '" + deletedString + "' has been deleted successfully");
                    elementsCounterInHashTable--;
                    return;
                }
                hashCode = hashCode + stepSize;
                hashCode = hashCode % currentHashTableSize;
            }
        } else {
            System.out.println("String: '" + deletedString + "' was not found in the hash table");
        }
    }

    public int getCollisionCount() {
        return collisionCounter;
    }

    public void printHashTable() {
        for (int i = 0; i < currentHashTableSize; i++) {
            System.out.println("Index " + i + ": " + hashTableSlots[i]);
        }
    }

}
