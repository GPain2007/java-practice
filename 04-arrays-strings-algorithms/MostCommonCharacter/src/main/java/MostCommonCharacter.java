import java.util.*;

public class MostCommonCharacter {
    /**
     * Find the most common character in str.
     * You could use a HashMap that maps a Character key to an Int value to represent how many times a Character has
     * been spotted.
     * @param str A String.
     * @return the most common character within str.
     */
    public char recurringChar(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        char mostCommonChar = ' ';
        int maxOccurrences = 0;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() > maxOccurrences) {
                mostCommonChar = entry.getKey();
                maxOccurrences = entry.getValue();
            }
        }

        return mostCommonChar;
    }
    
}
