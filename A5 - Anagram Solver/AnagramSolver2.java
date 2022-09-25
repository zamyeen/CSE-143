// Zareef Amyeen
// 5.19.2022
// CSE 143 Section BR
// TA: Jin Terada White
// Assessment 4: Anagram Solver
// Anagram Solver is a class that takes in a list of words (dictionary)
// and creates all permutations of possible words that match a specific
// target phrase

import java.util.*;

public class AnagramSolver2 {
    private Map<String,LetterInventory> wordBank;
    private List<String> dictionary;

    /**
   * Constructs an Anagram Solver from a list of words (dictionary)
   * @param List<String> dictionary - List of words that are valid words for the target word
            to be created out of
   */
    public AnagramSolver(List<String> dictionary){
        this.dictionary = dictionary;
        wordBank = new HashMap<String, LetterInventory>();
        for (String word : dictionary){
            wordBank.put(word,new LetterInventory(word));
        }
    }

    /**
   * Prints all permutations of "text" that 
   * @param String text - text that the anagram will add up to 
   * @param int max - maximum number of words that the anagram can be made out of
   *    max can also be 0 which means unlimited words to make anagram
   * @throws IllegalArgumentException if max is less than 0
   */
    public void print(String text, int max){
        if (max < 0){
            throw new IllegalArgumentException("Max cannot be less than 0");
        }
        //Get anagram representation of string s
        LetterInventory target = new LetterInventory(text);
        List<String> smallDictionary = new ArrayList<>();
        for (String word : dictionary){
            if (target.subtract(wordBank.get(word)) != null){
                //System.out.println(word);
                smallDictionary.add(word);
            }
        }
        //System.out.println(smallDictionary.size());
        explore(smallDictionary, target, new ArrayList<String>(),max,0);
    }

    // Recursive method that takes in pruned dictionary, target LI, and created a list of words
    // that are permutations for the word
    private void explore(List<String> smallDictionary, LetterInventory target, 
                    List<String> currentWords, int max, int count){
        if (target.isEmpty()){
            System.out.println(currentWords);
        } else {
            for (String word : smallDictionary){
                LetterInventory wordLI = new LetterInventory(word);
                LetterInventory subtracted = target.subtract(wordLI);
                if (subtracted != null){
                    currentWords.add(word);
                    //target = target.subtract(wordLI);
                    //System.out.println(currentWords);
                    if (count < max){
                       explore(smallDictionary, subtracted, currentWords,max,count+1);
                    } else if (max == 0){
                       explore(smallDictionary, subtracted, currentWords,max,count);
                    }
                    //target = target.add(wordLI);
                    currentWords.remove(currentWords.size()-1);
                }
            }
        }
    }
}

//r