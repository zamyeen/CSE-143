import java.util.*;
import java.io.*;

public class QuestionsGame {
    // TODO: Your Code Here
    private QuestionNode root;
    private Scanner console;

    public QuestionsGame(){
        root = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    public void read(Scanner input){
        root = readTree(input);
    }

    private QuestionNode readTree(Scanner input){
        String type = input.nextLine();
        String data = input.nextLine();
        QuestionNode root = new QuestionNode(data);
        if (type.equals("Q:")){
            root.left = readTree(input);
            root.right = readTree(input);
        } 
        return root;
    }

    public void write(PrintStream output){
        writeTree(root, output);
    }

    private void writeTree(QuestionNode root, PrintStream out){
        if (root != null){
            if (root.left == null && root.right == null){
                /*System.*/out.println("A:");
            } else {
                /*System.*/out.println("Q:");
            }
            /*System.*/out.println(root.data);
            writeTree(root.left, out);
            writeTree(root.right, out);
        }
    }

    public void askQuestions(){
       root = askQuestions(root);
    }
    
    private QuestionNode askQuestions(QuestionNode root){
       if (root != null){
         if (root.left == null && root.right == null){
            if (yesTo("Would your object happen to be " + root.data + "?")){
               System.out.println("Great, I got it right!");
            } else {
               System.out.print("What is the name of your object? ");
               QuestionNode object = new QuestionNode(console.nextLine());
               System.out.println("Please give me a yes/no question that");
               System.out.println("distinguishes between your object");
               System.out.print("and mine--> ");
               QuestionNode question = new QuestionNode(console.nextLine());
               boolean answer = yesTo("And what is the answer for your object?");
               root = changeTree(answer, root, object, question);
            }  
         } else if (yesTo(root.data)){
            root.left = askQuestions(root.left);
         } else {
            root.right = askQuestions(root.right);
         }
       }
       return root;
    }
    
    private QuestionNode changeTree(boolean answer, QuestionNode current, QuestionNode newObj, QuestionNode question){
      if (answer){
         question.left = newObj;
         question.right = current;
      } else {
         question.left = current;
         question.right = newObj;
      }  
      return question;
    }
    
    // Do not modify this method in any way
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    private boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    private static class QuestionNode {
        public final String data;
        public QuestionNode left;
        public QuestionNode right;

        public QuestionNode(String data){
            this(data, null, null);
        }

        public QuestionNode(String data, QuestionNode left, QuestionNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    /*public static void main(String[] args) throws IOException{
      PrintStream ps = new PrintStream(new File("out.txt"));
      Scanner input = new Scanner(new File("questions.txt"));
      QuestionsGame qg = new QuestionsGame();
      qg.write(ps);
    }*/
}