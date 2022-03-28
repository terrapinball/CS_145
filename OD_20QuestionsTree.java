//Software Engineers: Owen Davies
//Class: CS 145
//Date 5-24-2021
//Lab: Word Search
//Reference Materials: Ch. 1-17
//Purpose: Play the game of 20 questions with the computer using a file of questions and answers



import java.util.*;
import java.io.*;

public class OD_20QuestionsTree {
   
   private OD_20QuestionsNode rootOfTree;             // Initialize field for rootOfTree, implementing
   private Scanner console;                           //QuestionNode, and a Scanner.
   
   
   public OD_20QuestionsTree() {
      rootOfTree = new OD_20QuestionsNode("computer");// Starting node will be the computer.
      console = new Scanner(System.in);
   }
   
   public void read(Scanner input) {                  // Read the input file to make a new tree.
      while(input.hasNextLine()) {                    //Recursive code not public.
         rootOfTree = readHelper(input);
      }
   }

   private OD_20QuestionsNode readHelper(Scanner input) {
      String line = input.nextLine();
      String type = line.substring(0, 2);             // Set the type to either 'Q:' or 'A:'
      String data = line.substring(2);                //and the rest of the line to the data.
      OD_20QuestionsNode root = new OD_20QuestionsNode(data);  
  
      if (type.contains("Q:")) {                      // If the line is a question, set the 
         root.yesNode = readHelper(input);            //'yes' and 'no' nodes.
         root.noNode = readHelper(input);   
      }
      return root;                                    // Return the main node.
   }
   
   public void write(PrintStream output) {            // Able to write the tree to the file with
      if (output == null) {                           //new data.
         throw new IllegalArgumentException(); 
      }
      writeTree(rootOfTree, output);
   }
   
   private void writeTree(OD_20QuestionsNode rootOfTree, PrintStream output) {
      if (isAnswerNode(rootOfTree)) {                 // If the node is a leaf node, with no 'yes'
         output.print("A:");                          //or 'no' nodes, then write it to the file
         output.println(rootOfTree.data);
      } else {
         output.print("Q:");                          // If the node is a question node with data for 
         output.println(rootOfTree.data);             //'yes' and 'no' nodes, then write them as such.
         writeTree(rootOfTree.yesNode, output);
         writeTree(rootOfTree.noNode, output); 
      }   
   }
   
   public void askQuestions() {
      rootOfTree = askQuestions(rootOfTree); 
   }

   private OD_20QuestionsNode askQuestions(OD_20QuestionsNode current) {      // This works to play the game.
      if (isAnswerNode(current)) {                                            //Questions are asked, inserting
         if (yesTo("Would your object happen to be " + current.data +"?")) {  //the data and returning answers
            System.out.println("Great, I got it right!");                     //accordingly.
         } else {
            System.out.print("What is the name of your object? ");
            OD_20QuestionsNode answer = new OD_20QuestionsNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine: "); 
            String question = console.nextLine(); 
            if (yesTo("And what is the answer for your object?")) {
               current = new OD_20QuestionsNode(question, answer, current); 
            } else {
               current = new OD_20QuestionsNode(question, current, answer); 
            }   
         }
     
      } else {
         if (yesTo(current.data)) {
            current.yesNode = askQuestions(current.yesNode);
         } else {
            current.noNode = askQuestions(current.noNode); 
         }   
      } 
      return current;
   }

   public boolean yesTo(String prompt) {                                      // Test if the user answers 'yes'
      System.out.print(prompt + " (y/n)? ");                                  //or 'no'.
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
          System.out.println("Please answer y or n.");
          System.out.print(prompt + " (y/n)? ");
          response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }   

   private boolean isAnswerNode(OD_20QuestionsNode node) {                    // Private method for testing if the
      return (node.yesNode == null || node.noNode == null);                   //node has no additional branches.
   }
}