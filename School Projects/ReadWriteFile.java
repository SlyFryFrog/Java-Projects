import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;


public class ReadWriteFile {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        File file;
        String writeString;
        String fileName;
        int numberOfWords;

        fileName = promptForFileName(scnr);
        writeString = promptForText(scnr);

        scnr.close();

        file = writeText(fileName, writeString);
        numberOfWords = countWords(file);

        System.out.println("There are " + numberOfWords + " words in the file.");
    }

    /**
     * Prompts user with text and returns their input
     * @param scnr Takes in a Scanner object for input
     * @return String for valid file name
     */
    public static String promptForFileName(Scanner scnr) {
        String fileName;
        
        System.out.println("Enter a file name (For example, example.txt)");
        fileName = scnr.nextLine().strip();

        return fileName;
    }

    /**
     * Returns a String of the user's input
     * @param scnr Takes in a Scanner object for input
     * @return String of text to be written to the file
     */
    public static String promptForText(Scanner scnr) {
        String writeString;
        
        System.out.println("Enter what you would like to writte to the file.");
        writeString = scnr.nextLine();

        return writeString;
    }

    /**
    * Creates a file, writes provided string to file, returns File object
    * @param fileName takes a String for the desired file name
    * @param writeString takes a String for input to be written to the file
    * @throws IOException
    * @return Returns a File object
    */
    public static File writeText(String filename, String writeString) throws IOException {
        File file = new File(filename);
        FileOutputStream fileOS;

        // Checks if file doesn't exists and creates the file
        if(!file.isFile()) {
            file.createNewFile();
        }

        fileOS = new FileOutputStream(file);

        // Writes each char in writeString to the file
        for(int i = 0; i < writeString.length(); i++) {
            fileOS.write(writeString.charAt(i));
        }
        fileOS.close();

        return file;
    }

    /**
    * Counts number of words in file
    * A word is any text separated by a whitespace
    * @param file takes in a File variable as file to count words in
    * @throws FileNotFoundException
    * @return int numberOfWords
    */
    public static int countWords(File file) throws FileNotFoundException {
        int numberOfWords = 0;
        Scanner scnr = new Scanner(file);

        while(scnr.hasNext()) {
            scnr.next();
            numberOfWords++;
        }
        scnr.close();

        return numberOfWords;
    }
}
