//Software Engineers: Owen Davies
//Class: CS 145
//Date 5-24-2021
//Lab: Word Search
//Reference Materials: Ch. 1-17
//Purpose: Play the game of 20 questions with the computer using a file of questions and answers



import java.io.*;
import java.util.*;

public class OD_20QuestionsMain {
    public static final String QUESTION_FILE = "question2.txt";

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the game of 20 questions!");
        System.out.println();

        OD_20QuestionsTree questions = new OD_20QuestionsTree();
        if (questions.yesTo("Shall I recall our previous games?"))
            questions.read(new Scanner(new File(QUESTION_FILE)));
        System.out.println();

        do {
            System.out.println("Think of an item, and I will guess it.");
            questions.askQuestions();
            System.out.println();
        } while (questions.yesTo("Challenge me again?"));
      questions.write(new PrintStream(new File(QUESTION_FILE)));
    }
}