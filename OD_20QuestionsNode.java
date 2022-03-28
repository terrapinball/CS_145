//Software Engineers: Owen Davies
//Class: CS 145
//Date 5-24-2021
//Lab: Word Search
//Reference Materials: Ch. 1-17
//Purpose: Play the game of 20 questions with the computer using a file of questions and answers



public class OD_20QuestionsNode {
   
   public String data;
   public OD_20QuestionsNode yesNode;
   public OD_20QuestionsNode noNode;
   
   public OD_20QuestionsNode(String data) {
      this(data,null, null); 
   }
   
   public OD_20QuestionsNode(String data, OD_20QuestionsNode yesNode, OD_20QuestionsNode noNode) {
      this.data = data;
      this.yesNode = yesNode;
      this.noNode = noNode; 
   }
}