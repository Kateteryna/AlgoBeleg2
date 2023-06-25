import HashTable.HashTableImpl;

public class MainTest {
    public static void main(String[] args) {
        HashTableImpl hashTable = new HashTableImpl(6);
        hashTable.insert("ff");
        hashTable.insert("fgh");
        hashTable.insert("dhhg");
        hashTable.printHashTable();
        hashTable.insert("dgg");
        hashTable.insert("dg");
        hashTable.insert("dh");
        hashTable.insert("dzh");
        hashTable.getCurrentAmountOfEntries();
        hashTable.getCollisionCount();
    }
}
