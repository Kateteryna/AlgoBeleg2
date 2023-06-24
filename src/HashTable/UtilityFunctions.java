package HashTable;

public class UtilityFunctions {
    public static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    public static int getNextPrime(int initialNumber) {
        for(int i = initialNumber; true; i++) {
            if(isPrime(i)) {
                return i;
            }
        }
    }
}
