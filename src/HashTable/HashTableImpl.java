package HashTable;

import static HashTable.UtilityFunctions.getNextPrime;
import static HashTable.UtilityFunctions.isPrime;

public class HashTableImpl {

    String[] hashTableSlots;
    int hashTableSlotsSize;
    int elementsCounterInHashTable = 0;

    public HashTableImpl(int slotsNumber) {
        if(isPrime(slotsNumber)) {
            hashTableSlots = new String[slotsNumber];
            hashTableSlotsSize = slotsNumber;
        } else {
            int primeNumber = getNextPrime(slotsNumber);
            hashTableSlots = new String[primeNumber];
            hashTableSlotsSize = primeNumber;

            System.out.println("This hash table size: " + slotsNumber + " - is not a prime number");
            System.out.println("The size has been changed into: " + primeNumber);
        }
    }

    private int firstHashingFunction(String entryString) {
        int hashCode = entryString.hashCode();
        hashCode = hashCode % hashTableSlotsSize;
        if(hashCode < 0) {
            hashCode += hashTableSlotsSize;
        }
        return hashCode;
    }

    private int secondHashingFunction(String entryString) {
        int hashCode = entryString.hashCode();
        hashCode = hashCode % hashTableSlotsSize;
        if(hashCode < 0){
            hashCode += hashTableSlotsSize;
        }
        return 7 - hashCode % 7; // step size
    }

    public void insert(String entryString) { //using here linear probing rto handle collissions
        int hashCode = firstHashingFunction(entryString);
        int stepSize = secondHashingFunction(entryString);

        while (hashTableSlots[hashCode] != null) {
            hashCode = hashCode + stepSize;
            hashCode = hashCode % hashTableSlotsSize;
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
            hashCode = hashCode % hashTableSlotsSize;
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
                hashCode = hashCode % hashTableSlotsSize;
            }
        } else {
            System.out.println("String: '" + deletedString + "' was not found in the hash table");
        }
    }

}
