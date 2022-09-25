public class Mystery{

   public static void mystery6(int n) {
      if (n <= 0) {
         System.out.print("*");
      } else if (n % 2 == 0) {
         System.out.print("(");
         mystery6(n - 1);
         System.out.print(")");
      } else {
         System.out.print("[");
         mystery6(n - 1);
         System.out.print("]");
      }
   }
   
   public static void main(String[] args){
     mystery6(5);
   }

}

