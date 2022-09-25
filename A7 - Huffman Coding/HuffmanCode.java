import java.util.*;
import java.io.*;

public class HuffmanCode {
   private HuffmanNode root;
   
   public static void main (String[] args) throws IOException{
      int[] frequency = new int[130];
      frequency[97] = 4;
      frequency[98] = 5;
      frequency[32] = 2;
      frequency[99] = 1;
      PrintStream ps = new PrintStream(new File("output2.txt"));
      Scanner fileScanner = new Scanner(new File("output.txt"));
      HuffmanCode hc = new HuffmanCode(frequency);
      hc.save(ps);
      PrintStream psTranslate = new PrintStream(new File("output3.txt"));
      Scanner translateScanner = new Scanner("1 1 0 1 0 1 1 1 0 1 0 1 1 0 0");
      hc.translate(translateScanner,psTranslate);
   }   
   
    /*
    * Constructs a Huffman Code object from an array of frequencies
    * @param int[] frequencies - array of frequencies that have the count of every ascii value
    *       the count of an ascii value is at the index of the ascii value

    */
   public HuffmanCode(int[] frequencies){
      Queue<HuffmanNode> pq = new PriorityQueue<>();
      for (int i = 0; i < frequencies.length; i++){
         if (frequencies[i] != 0){
            pq.add(new HuffmanNode(i,frequencies[i]));
         }
      }
      while (pq.size() > 1){
         HuffmanNode left = pq.remove();
         HuffmanNode right = pq.remove();
         HuffmanNode nonLeaf = new HuffmanNode(0,left.frequency + right.frequency,left,right);
         pq.add(nonLeaf);
         //pq = printPriorityQueue(pq);
      }
      root = pq.remove();
      //print(root);
   }

   private void print(HuffmanNode root){
      if (root != null){
         System.out.print(root);
         print(root.left);
         print(root.right);
      }
   }
    
   private Queue<HuffmanNode> printPriorityQueue(Queue<HuffmanNode> pq){
      int size = pq.size();
      Queue<HuffmanNode> mirrorpq = new PriorityQueue<>();
      for (int i = 0; i < size; i++){
         HuffmanNode hn = pq.remove();
         print(hn);
         System.out.println();
         mirrorpq.add(hn);
      }
      return mirrorpq;
   }
      
      
    /*
    * Constructs a Huffman Code from a scanner that contains encoded data in standard format
    * @param Scanner input - Non null scanner that contains the encoded data
    *
    */
   public HuffmanCode(Scanner input){
      root = new HuffmanNode();
        while (input.hasNextLine()){
           int asciiValue = Integer.parseInt(input.nextLine());
           String code = input.nextLine();
           writeNode(root, code, asciiValue);
        }
   }

   private void writeNode(HuffmanNode root, String code, int asciiValue) {
      if(!code.equals("")) {
         //System.out.println(code);
         if(code.charAt(0) == '0') {
            if(root.left == null){
               root.left = new HuffmanNode();
            }
            writeNode(root.left, code.substring(1), asciiValue);
         }  
         else {
            if(root.right == null){
               root.right = new HuffmanNode();
            }
            writeNode(root.right, code.substring(1), asciiValue);
         }
      }
      root.ascii = asciiValue;
   }
   
    /*
    * Writes the current Huffman code to a specific file using the standard format
    * @param PrintStream output - Print stream that outputs to a file
    */
   public void save (PrintStream output){
      writeTree(root, "", output);
   }
    
   private void writeTree(HuffmanNode root, String soFar, PrintStream out){
      if (root != null){
         if (root.left == null && root.right == null){
            /*System.*/out.println(root.ascii);
            /*System.*/out.println(soFar);
         }
         writeTree(root.left, soFar + "0",out);
         writeTree(root.right, soFar + "1", out);
      }
   }

    /*
    * @param Scanner input - Scanner that reads input
    * @param PrintStream output - Print stream that outputs characters to file
    */
   public void translate (Scanner input, PrintStream output){
      while (input.hasNext()){
         HuffmanNode current = root;
         while (!(current.left == null && current.right == null)){
            char direction = input.next().charAt(0);
            System.out.println(direction);
            if (direction == '1'){
               current = current.right;
            } else {
               current = current.left;
            }
         }
         output.write(current.ascii);
      }
   }

   /*public void translate(Scanner input, PrintStream output) {
      int currentChar = findAscii(input, root);
      while(!input.hasNext()) {
         output.write(currentChar);
         currentChar = findAscii(input, root);
      }
   }

   private int findAscii(Scanner input, HuffmanNode current){
      if (current.left == null && current.right == null){
         return current.ascii;
      }
      if (input.next().charAt(0) == '0'){
         return findAscii(input,current.left);
      } else {
         return findAscii(input,current.right);
      }
   }*/
    
   private static class HuffmanNode implements Comparable<HuffmanNode>{
      public int ascii;
      public int frequency;
      public HuffmanNode left;
      public HuffmanNode right;
   
        // post: Constructs a leaf node with given data
      public HuffmanNode(int ascii, int frequency) {
         this(ascii, frequency, null, null);
      }

      public HuffmanNode(){
         this(0,0,null,null);
      }
   
        // post: Constructs a leaf or branch node with given data and links
      public HuffmanNode(int ascii, int frequency, HuffmanNode left, HuffmanNode right) {
         this.ascii = ascii;
         this.frequency = frequency;
         this.left = left;
         this.right = right;
      }
   
      public int compareTo(HuffmanNode other){
         return Integer.compare(this.frequency,other.frequency);
      }
        
      public String toString(){
         return "(f:" + frequency + ",a:" + ascii + ",c:" + (char)ascii + ")";
      }
   }
}