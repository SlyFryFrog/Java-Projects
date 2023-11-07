import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Duplicates {
    public static void main(String[] args) throws Exception {
        File file = new File("src/names.txt");
        readFileAndCountDuplicates(file);
    }

    /**
     * Takes in a File and prints all unique words and how many times the occur
     * @param file
     * @throws IOException
     */
    public static void readFileAndCountDuplicates(File file) throws IOException {
        FileInputStream fileIS = new FileInputStream(file);
        Scanner scnr = new Scanner(fileIS);
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>(); 

        while (scnr.hasNext()) {
            String str = scnr.next();
            Integer duplicates;

            str = toTitleCase(str);

            // Checks if the String is already in the HashMap
            if(hashmap.containsKey(str)) {
                duplicates = hashmap.get(str) + 1;
                hashmap.put(str, duplicates);
            } else {
                hashmap.put(str, 1);
            }
        }
        fileIS.close();
        scnr.close();

        printArrayBySet(System.out, hashmap);
    }

    /**
     * Iterates through each key, value pair in the HashMap and outputs it to the PrintStream
     * @param prints
     * @param hashmap
     */
    public static void printArrayBySet(PrintStream printStream, HashMap<String, Integer> hashmap) {
        // Prints each key, value
        for (String key : hashmap.keySet()) {
            printStream.println(key + " " + hashmap.get(key));
        }
    }

    /**
     * Capitalizes and letter starting after a ' '
     * @param inputString String to be edited
     * @return Edited String in titlecase form
     */
    public static String toTitleCase(String inputString) {
        char[] charArray = inputString.toCharArray();
        String editedString = "";

        for(int i = 0; i < charArray.length; i++) {
            int asciiNum;

            if((int)charArray[i] >= 97 && (int)charArray[i] <= 122) {
                if(charArray[i] != ' ' && i > 0) {
                    asciiNum = (int)charArray[i] - 32;

                    if(charArray[i - 1] == ' ') {
                        asciiNum = (int)charArray[i] - 32;
                        charArray[i] = (char)asciiNum;
                    }

                } else if (i == 0 && charArray[i] != ' ') {
                    asciiNum = (int)charArray[i] - 32;
                    charArray[i] = (char)asciiNum;
                }
            } else if((int)charArray[i] >= 65 && (int)charArray[i] <= 90 && i > 0) {
                if(charArray[i - 1] != ' '){
                    asciiNum = (int)charArray[i] + 32;
                    charArray[i] = (char)asciiNum;
                }
            }

            editedString += charArray[i];
        }
        return editedString;
    }
}
