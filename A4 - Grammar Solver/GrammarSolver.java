import java.util.*;
import java.io.*;

public class GrammarSolver {
    private SortedMap<String,String[]> grammar;
    
   public GrammarSolver(List<String> rules){
      if (rules.size() == 0){
         throw new IllegalArgumentException("List is empty");
      }
      grammar = new TreeMap<>();
      //sorted map between non terminal and more non terminals/terminals
      //for each rule in the grammar
      //split each rule on "::=" between non terminal and rule
      for (String line : rules){
         String[] pieces = line.split("::=");
         String[] r = pieces[1].split("\\|");
         if (grammarContains(pieces[0])){
            throw new IllegalArgumentException("2 or more entries for same non-terminal");
         }
         grammar.put(pieces[0],r);
      }
   }
    
   public boolean grammarContains(String symbol){
      for (String rule : grammar.keySet()){
         if (rule.equals(symbol)){
            return true;
         }
      }
      return false;
   }
    
   public String getSymbols(){
      return grammar.keySet().toString();
   }
    
   public String[] generate (String symbol, int times){
      if (times < 0){
         throw new IllegalArgumentException("Times cannot be negative");
      } else if (!grammarContains(symbol)){
         throw new IllegalArgumentException("There is no symbol in non terminal");
      }
      //generate inputted 'times' number of random sentences
      String[] sentences = new String[times];
      for (int i = 0; i < times; i++){
         sentences[i] = getRandom(symbol).trim();
         //System.out.println(sentences[i]);
      }
      return sentences;
   }

   //recursive method
   private String getRandom(String input){
      //base case (we can't recurse anymore meaning we reached a terminal)
      if (!grammarContains(input)){
         //return the terminal formatted correctly
         return input + " ";
      } else {
         Random r = new Random();
         int choice = r.nextInt(grammar.get(input).length);
         String[] parts = grammar.get(input)[choice].trim().split("\\s+");
         String retString = "";
         for (int i = 0; i < parts.length; i++){
            retString += getRandom(parts[i]);
         }
         return retString;
      }
   }

   private String[] getRules (String nonterminal){
      return grammar.get(nonterminal);
   }
  
   public static void main(String[] args) throws IOException{
      List<String> rules = new ArrayList<String>();
      Scanner fileScanner = new Scanner(new File("sentence.txt"));
      while (fileScanner.hasNextLine()){
         String token = fileScanner.nextLine();
         //System.out.println(token);
         rules.add(token);
      }
      //System.out.println(rules.toArray());
      GrammarSolver gs = new GrammarSolver(rules);
      System.out.println(gs.getSymbols());
      gs.generate("<sentence>",5);
   }
   
}