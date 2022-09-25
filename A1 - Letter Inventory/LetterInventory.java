import java.util.*;

public class LetterInventory {
   // TODO: Your Code Here
   private int[] count;
   private int size;
   private static final int DEFAULT_SIZE = 26;

   public LetterInventory(){
      this("");
   }

   public LetterInventory(String data){
      //Throw Exception if not letter
      count = new int[DEFAULT_SIZE];
      for (int i = 0; i < data.length(); i++){
         if (Character.isLetter(data.charAt(i))){
            count[(data.toLowerCase().charAt(i) - 'a')]++;
         }
      }
      System.out.println(data);
      size = data.length();
   }

   public int get(char letter){
      //Throw exception if not letter
      if (!Character.isLetter(letter)){
         throw new IllegalArgumentException("Letter inputed is not a character!");
      }
      return count[Character.toLowerCase(letter) - 'a'];
   }

   public void set(char letter, int value){
      if (!Character.isLetter(letter)){
         throw new IllegalArgumentException("Letter inputed is not a character!");
      }
      size += value - count[Character.toLowerCase(letter)-'a'];
      count[Character.toLowerCase(letter)-'a'] = value;
   }

   public int size(){
      return size;
   }

   public boolean isEmpty(){
      return size == 0;
   }

   public String toString(){
      String retString = "[";
      for (int i = 0; i < count.length; i++){
         for (int j = 0; j < count[i]; j++){
            retString += (char)('a' + i);
         }
      }
      System.out.println(Arrays.toString(count));
      return retString + "]";
   }

   public LetterInventory add(LetterInventory other){
      LetterInventory l = new LetterInventory();
      for (int i = 0; i < count.length; i++){
         l.set((char)('a' + i), this.count[i] + other.count[i]);
      }
      return l;
   }

   public LetterInventory subtract(LetterInventory other){
      LetterInventory l = new LetterInventory();
      for (int i = 0; i < count.length; i++){
         int n = this.count[i] - other.count[i];
         if (n < 0){
            return null;
         }
         l.set((char)('a' + i),n);
      }
      return l;
   }

   public static void main(String[] args){
      LetterInventory l1 = new LetterInventory("Hello world!");
      LetterInventory l2 = new LetterInventory("owl");
      System.out.println(l1);
      System.out.println(l2);
      System.out.println(l2.subtract(l1));
   }
}