//Software Engineers: Owen Davies
//Class: CS 145
//Date 5-24-2021
//Lab: Word Search
//Reference Materials: Ch. 1-8
//Purpose: Take input words and dimensions and create a word search puzzle.



import java.util.*;

public class OD_WordSearch {
    
    public static String[][] wordSearch;
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        printIntro(console);

    }

    public static void printIntro(Scanner console) {            // PrintIntro() runs the welcome block and menu

        System.out.println("Welcome to the word search generator!");
        System.out.println("Create a word search of your own dimensions with your own words");
        
        while (true) {
            
            System.out.println("Please select an option:");
            System.out.println("Generate a new word search (g)");
            System.out.println("Print out your word search (p)");
            System.out.println("Show the solution to your word search (s)");
            System.out.println("Quit the program (q)");
            String response = console.next();

            if (response.equalsIgnoreCase("g")) {               // Three methods for each generate, print, show
                wordSearch = generate(console);
            } else if (response.equalsIgnoreCase("p")) {
                printWordSearch(wordSearch);
            } else if (response.equalsIgnoreCase("s")) {
                showSolution(wordSearch);
            } else if (response.equalsIgnoreCase("q")) {
                return;
            } else {
                System.out.println("Please select an option");
            }
        }
    }

    public static String[][] generate(Scanner console) {

        ArrayList<String> wordList = new ArrayList<String>();    
        String word = "";                                         // Initialize arraylist for user input words
        //String [][] wordSearch = new String[][];
        while (true) {
            System.out.println("Enter a word to add to your puzzle or type 'x' to quit: ");
            word = console.next();
            if (word.equals("x")) {
                break;
            } else if (word.length() < 2) {                                 // Catch for only one letter entered
                System.out.println("One-letter words are not permitted");
                System.out.println("Please enter a word to add or 'x' to quit: ");
            } else {
                wordList.add(word);
            }
        }
        
        String longestWord = getLongest(wordList);                          // Run method to return longest word
        int size = longestWord.length();                                    //for puzzle dimensions.
        if (size < 5 || size < wordList.size()) {                           // If dimension variable 'size' is too
         size = 5;                                                          //small, manually set 'size' to 5
        }                                                                   //minimum.
        

        String[][] wordSearch = new String[size][size];                     // Initialize two-dimensional array 
        
        Random r = new Random();                                            //to puzzle dimensions.
         
        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                    wordSearch[i][j] = "-";
                
            }
        }

        for (String str : wordList) {                                       
            word = str;                                                     
            int count = 0;
            boolean s = false;
            while (s == false && count <= 100) {
                int direction = r.nextInt(3);                               // Randomly select 0 = horizontal, 
                if (true) {                                                 //1 = vertical, 2 = diagonal.
                    boolean shouldDrawWord = true;
                    if (direction == 0) {
                        int startY = r.nextInt(size);                       // Variables startY and startX designated
                        int startX = 0;                                     //for random starting point, minus word
                        if (size - str.length() > 0) {                      //length so word will fit in the puzzle.
                            startX = r.nextInt(size - str.length());
                        }
                        for (int j = 0; j < str.length(); j++) {            // If any of the required spaces are
                            String letter = wordSearch[startY][startX + j] ;//another letter or not the same letter,
                            if (!letter.equals("-") || letter.equals(str.substring(j, j + 1))) {
                                shouldDrawWord = false;                     //then try again with new starting point.
                                count++;
                                break;
                            }
                        }
                        if (shouldDrawWord) {                               // Otherwise, traverse the word
                            for (int j = 0; j < word.length(); j++) {
                                wordSearch[startY][startX + j] = str.substring(j, j + 1);
                                
                            }
                            s = true;
                        }
                    }
                    else if (direction == 1) {                                      //If direction == vertical
                        int startY = 0;
                        int startX = r.nextInt(size);
                        if (size - str.length() > 0) {
                            startY = r.nextInt(size - str.length());
                        }
                        for (int j = 0; j < str.length(); j++) {
                            String letter = wordSearch[startY + j][startX] ;               
                            if (!letter.equals("-") || letter.equals(str.substring(j, j + 1))) {
                                shouldDrawWord = false;
                                count++;
                                break;
                            }
                        }
                        if (shouldDrawWord) {
                            for (int j = 0; j < str.length(); j++) {
                                wordSearch[startY + j][startX] = str.substring(j, j + 1);
                                
                            }
                            s = true;
                        }
                    }
                    else {                                                          //If direction == diagonal
                        int startY = 0;
                        int startX = 0;
                        if (size - str.length() > 0) {
                            startY = r.nextInt(size - str.length());
                            startX = r.nextInt(size - str.length());
                        }
                        for (int j = 0; j < word.length(); j++) {
                            String letter = wordSearch[startY + j][startX + j] ;               
                            if (!letter.equals("-") || letter.equals(str.substring(j, j + 1))) { //
                                shouldDrawWord = false;
                                count++;
                                break;
                            }
                        }
                        if (shouldDrawWord) {
                            for (int j = 0; j < word.length(); j++) {
                                wordSearch[startY + j][startX + j] = str.substring(j, j + 1);
                                
                            }
                            s = true;
                        }
                    }
                }
            }
        }
        return wordSearch;
    }

    public static String getLongest(ArrayList<String> wordList) {                   // Find the longest word in the 
        String longestWord = "";                                                    //arraylist to determine the 
        for ( String word : wordList ) {                                            //dimensions of the puzzle.
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    public static void printWordSearch(String[][] wordSearch) {
        String[][] wordSearchPuzzle = wordSearch;                                   // Create a copy of the two-dimesional
        Random r = new Random();                                                    //array to add random letters to
        for (int i = 0; i < wordSearchPuzzle.length; i++) {                         //in order to print the puzzle
            for (int j = 0; j < wordSearchPuzzle[i].length; j++) {
                if (wordSearchPuzzle[i][j].equals("-")) {
                    char c = (char) (r.nextInt(26) + 'a');
                    String letter = String.valueOf(c);
                    System.out.print(letter);
                } else {
                    System.out.print(wordSearchPuzzle[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void showSolution(String[][] wordSearch) {     // Create a copy of the two-dimensional
                                                                 //array in order to print the solution
        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                System.out.print(wordSearch[i][j]);
            }
            System.out.println();
        }
    }
}
