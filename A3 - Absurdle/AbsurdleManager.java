// Zareef Amyeen
// 4.28.2022
// CSE 143 Section BR
// TA: Jin Terada White
// Assessment 3: Absurdle
// Absurdle is a class that works similarly to the game of wordle
// Initially the class is created by passing in a list of possible words (all lowercase)
// As the player guesses different words, the game absurdle tries to keep
// the game going for as long as possible by choosing the pattern that has
// the most number of words still possible to guess

import java.util.*;

public class AbsurdleManager {
    private Set<String> words;
    private int length;

    /**
   * Constructs a Absurdle Manager from a "dictionary of words"
   * @param Collection<String> dictionary - List of words that the word bank is created out of
   *    All words are assumed to be lowercase, all duplicates and any words that aren't proper length
   *    are eliminated in the word bank
   * @param int length - Length of each word that is supposed to be in the word bank
   * throws IllegalArgumentException if length is less than 1
   */
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
    
    /*
    * @returns Set<String> words - the current word bank
    */
    public Set<String> words(){
        return words;
    }

    /*
    * This method finds the pattern that is associated with the most words from the given guess
    *   and returns that pattern. It also changes the word bank to the now new word bank
    *   associate with the new pattern. 
    * @param String guess - user's guess for the word, assumes guess is all lowercase
    * throws IllegalStateException if there are no words left in the word bank
    * throws IllegalArgumentException if guess is not the same length as the words in the wordbank
    * @return pattern that is associated with the most number of words.
    */
    public String record(String guess){
        if (words.size() == 0){
            throw new IllegalStateException("Word bank is empty");
        } else if (guess.length() != length){
            throw new IllegalArgumentException("Guess is not the correct length");
        }
        Map<String,Set<String>> possibleWordBank = new TreeMap<>();
        for (String word : words){
            String pattern = this.patternFor(word,guess);
            if (!possibleWordBank.containsKey(pattern)){
                possibleWordBank.put(pattern,new TreeSet<>());
            }
            possibleWordBank.get(pattern).add(word);
        }
        //Holds the number of words in the largest wordbank
        int maxPatterns = Integer.MIN_VALUE;
        //Holds the string of the key of the word bank that has the most
        String maxPattern = "";
        for (String pattern : possibleWordBank.keySet()){
            int setSize = possibleWordBank.get(pattern).size();
            if (setSize > maxPatterns){
                maxPatterns = setSize;
                maxPattern = pattern;
            }
        }
        words = possibleWordBank.get(maxPattern);
        return maxPattern;
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
        Map<Character,Integer> counts = new TreeMap<>();
        for (int i = 0; i < word.length(); i++){
            char letter = word.charAt(i);
            if (!counts.containsKey(letter)){
                counts.put(letter,0);
            }
            counts.put(letter,counts.get(letter)+1);
        }
        //Assigning green
        word = assignGreen(pattern,counts, word, guess);
        System.out.println(pattern.toString());
        //System.out.println(word);
        //Assign yellow
        word = assignYellow(pattern, counts, word, guess);
        System.out.println(pattern.toString());
        //System.out.println(word);
        String patternString = "";
        for (String patternLetter : pattern){
           patternString += patternLetter; 
        }
        System.out.println(patternString);
        return patternString;
    }

    //Returns string of the new word, with the green spots "1"d out
    private static String assignGreen(String[] pattern, Map<Character,Integer> counts, 
                                        String word, String guess){
        for (int i = 0; i < guess.length(); i++){
            char letter = guess.charAt(i);
            if (guess.charAt(i) == word.charAt(i)){
                pattern[i] = "G";
                counts.put(letter,counts.get(letter)-1);
                //word = word.substring(0,i) + "1" + word.substring(i+1);
            }
        }
        return word;
    }
    
    //Returns string of the new word, with the green spots "1"d out
    private static String assignYellow(String[] pattern, Map<Character,Integer> counts,
                                        String word, String guess){
        for (int i = 0; i < guess.length(); i++){
            char letter = guess.charAt(i);
            if (word.contains(letter + "") && pattern[i] != "G" && counts.get(letter) != 0){
                pattern[i] = "Y";
                counts.put(letter,counts.get(letter)-1);
                //word = word.substring(0,word.indexOf(letter + "")) + "2" 
                //    + word.substring(word.indexOf(letter + "") + 1);
            }
        }
        return word;
    }

    
    public static void main(String[] args){
        List<String> dictionary = List.of("ally", "beta", "cool", "deal",
            "else", "flew", "good", "hope", "ibex");
        AbsurdleManager am = new AbsurdleManager(dictionary,4);
        System.out.println(am.words);
        am.patternFor("abbey", "keeps");
    }

}