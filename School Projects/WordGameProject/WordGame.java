import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGame {
    static ArrayList<String> correctWords = new ArrayList<String>();
    static ArrayList<String> wordArrayList = new ArrayList<String>();
    static ArrayList<String> validInitialWordsArray = new ArrayList<String>();

    public static void main(String[] args) throws Exception {       
        char[] randomizedWord;
        String userInput;
        Scanner scnr = new Scanner(System.in);
        Boolean game = true;
        int score = 0;
        Random random = new Random();

        // Reads txt file and updates variables
        readFromTXTFile(); 

        // Gets random int and gets the String at the index in ArrayList
        int num = random.nextInt(validInitialWordsArray.size());
        String lengthSevenWord = validInitialWordsArray.get(num);

        randomizedWord = randomizeLetters(lengthSevenWord);
        
        System.out.println("Commands: ls, mix, bye, replay");

        while(game) {

            for (char letter : randomizedWord) {
                System.out.print("   " + letter);
            }
            System.out.println("");

            userInput = scnr.next().toLowerCase();
            
            // Checks if userInput is a command and not a word
            switch (userInput) {
                case "mix":
                    randomizedWord = randomizeLetters(lengthSevenWord);
                    System.out.println("Score: " + score);
                    break;
                case "ls":
                    listCorrectWords();
                    System.out.println("Score: " + score);
                    break;
                case "bye":
                    scnr.close();
                    game = false;

                    System.out.println("Au revoir o/");
                    System.out.println("You ended with a score of " + score);
                    
                    break;
                case "replay":
                    System.out.println("You ended with a score of " + score);
                    System.out.println("\n");
                    
                    // Resets all session-specific game variables
                    num = random.nextInt(validInitialWordsArray.size());
                    lengthSevenWord = validInitialWordsArray.get(num);
                    correctWords.clear();
                    randomizedWord = randomizeLetters(lengthSevenWord);
                    score = 0;

                    break;
                default:
                    boolean repeatedInput = false;

                    // Iterares through correctWords array to check for repeated guesses
                    for (String string : correctWords) {
                        if(userInput.equalsIgnoreCase(string)) {
                            repeatedInput = true;
                            break;
                        }
                    }
                    
                    // Checks to see if the word is both correct and not already guessed
                    if(!repeatedInput && checkWord(randomizedWord, userInput)) {
                        score = calculateScore(userInput, score);
                    }
                    System.out.println("Score: " + score);
                    break;
            }
        }
    }

    /**
     * Iterates through ArrayList containing all possible words.
     * Checks if the word is in the ArrayList.
     * Checks if all of the letters in String are in char[]
     * @param letterList char[] of all valid letters
     * @param userInput String of input to be checked
     * @return true if all words in String are in char[] and String is in ArrayList, else false
     */
    public static boolean checkWord(char[] letterList, String userInput) {
        boolean valid = false;

        // Checks if word is in ArrayList
        for (String word : wordArrayList) {
            if(userInput.equalsIgnoreCase(word)) {
                valid = true;
                break;
            }
        }

        if(valid) {
            //Checks if each character is in the initial mixed word
            for(int i = 0; i < userInput.length(); i++) {
                for (char letter : letterList) {
                    if(letter == userInput.charAt(i)) {
                        valid = true;
                        break;
                    } else {
                        valid = false;
                    }
                }
            }
            if (valid) {
                correctWords.add(userInput);
                return true;
            }
        }
        return false;
    }

    /**
     * Takes in a String and returns a char[] in a random order
     * @param word String to be randomized
     * @return char[] of original String in a random order
     */
    public static char[] randomizeLetters(String word) {
        char[] charArray = word.toCharArray();
        Random random = new Random();

        for(int i = 0; i < charArray.length; i++) {
            int randomIndex = random.nextInt(charArray.length);

            // Swap characters at the current index and the random index
            char temp = charArray[i];
            charArray[i] = charArray[randomIndex];
            charArray[randomIndex] = temp;
        }

        return charArray;
    }

    /**
     * Reads through predetermined file and adds them to ArrayLists
     * @throws FileNotFoundException
     * @return N/A
     */
    public static void readFromTXTFile() throws FileNotFoundException {
        File file = new File("words.txt");
        FileReader fileIS = new FileReader(file);
        Scanner scnr = new Scanner(fileIS);

        while(scnr.hasNextLine()) {
            String word = scnr.nextLine();

            if(word.length() == 7) {
                char[] letterList = word.toCharArray();
                boolean valid = false;  // Checks if characteristics of word are valid for an initial word

                // Checks for duplicate letters in a word
                for (char letter : letterList) {
                    if(word.indexOf(letter) != word.lastIndexOf(letter)) {
                        valid = false;
                        break;
                    } else {
                        valid = true;
                    }
                }

                if(valid) {
                    validInitialWordsArray.add(word);
                }
            }
            wordArrayList.add(word);
        }
        scnr.close();
    }

    /**
     * Checks characteristics of String and performs different actions depending on the length.
     * If length = 4, adds 1 to score; If length > 4, adds length of String to input
     * @param input String of input
     * @param score Integer to be modified
     * @return Returns integer increased by 1 if length = 4 or length of String input
     */
    public static int calculateScore(String input, int score) {
        if(input.length() == 4) {
            return score += 1;
        } else if(input.length() > 4) {
            return score += input.length();
        }
        return score;
    }

    /**
     * Prints every word that was guessed and was correct
     * @return N/A
     */
    public static void listCorrectWords() {
        for (String string : correctWords) {
            System.out.println("\t" + string);
        }
    }
}
