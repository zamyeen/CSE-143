// This program plays the question guessing game with a user.  It reads the
// old tree from a file if the user wants to.  It always writes its result
// to a file in case the user wants to use that tree the next time.

import java.io.*;
import java.util.*;

public class QuestionMain {
    public static final String QUESTION_FILE = "questions.txt";

    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.println("Welcome to the cse143 question program.");
        System.out.println();

        QuestionsGame questions = new QuestionsGame();
        if (promptUser("Do you want to read in the previous tree?")) {
            questions.read(new Scanner(new File(QUESTION_FILE)));
        }
        System.out.println();

        do {
            System.out.println("Please think of an object for me to guess.");
            questions.askQuestions();
            System.out.println();
        } while (promptUser("Do you want to go again?"));
        questions.write(new PrintStream(new File(QUESTION_FILE)));
    }

    private static boolean promptUser(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
