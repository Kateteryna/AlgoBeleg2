package HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilityFunctions {

    public static int countUniqueEntiesinCSV(String csvFile) {
        Set<String> uniqueStrings = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                uniqueStrings.addAll(List.of(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueStrings.size();
    }


    //Quelle: https://www.geeksforgeeks.org/java-program-to-check-if-a-number-is-prime-or-not/
    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    public static int getSmallerPrime(int initialNumber) {
        for(int i = initialNumber-1; true; i--) {
            if(isPrime(i)) {
                return i;
            }
        }
    }
}
