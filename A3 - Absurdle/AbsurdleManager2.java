import java.util.*;

public class AbsurdleManager2 {
    private Set<String> words;
    private int length;

    public AbsurdleManager(Collection<String> dictionary, int length){
        if (length < 1){
            throw new IllegalArgumentException("Length cannot be less than 1");
        }
        words = new TreeSet<String>();
        this.length = length;
        for (String word : dictionary){
            if (word.length() == length){
                words.add(word);
            }
        }
    }
    
    public Set<String> words(){
        return words;
    }

    public String record(String guess){
        return "";
    }
    // The comment for this method is provided. Do not modify this comment:
    // Params:
    //  String word -- the secret word trying to be guessed. Assumes word is made up of only
    //                 lower case letters and is the same length as guess.
    //  String guess -- the guess for the word. Assumes guess is made up of only
    //                  lower case letters and is the same length as word.
    // Exceptions:
    //   none
    // Returns:
    //   returns a string, made up of gray, yellow, or green squares, representing a
    //   standard wordle clue for the provided guess made against the provided secret word.
    public static String patternFor(String word, String guess) {
        String[] pattern = new String[word.length()];
        for (int i = 0; i < pattern.length; i++){
         pattern[i] = "R";
        }
        //Assigning green
        word = assignGreen(pattern,word,guess);
        //System.out.println(word);
        //Assign yellow
        word = assignYellow(pattern, word, guess);
        //System.out.println(word);
        String patternString = "";
        for (String patternLetter : pattern){
           patternString += patternLetter; 
        }
        System.out.println(patternString);
        return patternString;
    }

    //Returns string of the new word, with the green spots "1"d out
    private static String assignGreen(String[] pattern, String word, String guess){
        for (int i = 0; i < guess.length(); i++){
            if (guess.charAt(i) == word.charAt(i)){
                pattern[i] = "G";
                word = word.substring(0,i) + "1" + word.substring(i+1);
            }
        }
        return word;
    }
    
    private static String assignYellow(String[] pattern, String word, String guess){
      for (int i = 0; i < guess.length(); i++){
            if (word.contains(guess.charAt(i) + "") && pattern[i] != "G"){
                pattern[i] = "Y";
                word = word.substring(0,word.indexOf(guess.charAt(i) + "")) + "2" 
                    + word.substring(word.indexOf(guess.charAt(i) + "") + 1);
            }
        }
        return word;
    }
    public static void main(String[] args){
        List<String> dictionary = List.of("ally", "beta", "cool", "deal",
            "else", "flew", "good", "hope", "ibex");
        AbsurdleManager am = new AbsurdleManager(dictionary,4);
        System.out.println(am.words);
        am.patternFor("abbey", "bebop");
    }
}