import java.util.*;

public class Convert {
    public static Map<String, Set<String>> convert(Set<String> numbers) {
        // TODO: Your code here
        Map<String,Set<String>> m = new HashMap<>();
        for (String number : numbers){
            String areacode = getAreaCode(number);
            //System.out.println(number + " " + areacode);
            if (!m.containsKey(areacode)){
                m.put(areacode,new HashSet<>());
            }
            m.get(areacode).add(getNumberCode(number));
            System.out.println(areacode + " " +getNumberCode(number));
        }
        //printMap(m);
        return m;
    }

    public static void printMap(Map<String,Set<String>> m){
        for (String val : m.keySet()){
            System.out.println("[" + val + ", " + m.get(val) + "]");
        }
    }

    public static String getAreaCode(String num){
         String[] splitted = num.split("-");
         return splitted[0];
    }
    
    
    public static String getNumberCode(String num){
         String[] splitted = num.split("-");
         return splitted[1];
    }

    public static void main (String[] args){
      Set<String> s = Set.of("493-3923", "723-9278", "384-1917", "555-1795", "384-4923", "555-4923", "555-1212", "723-9823");
      convert(s);
    }
}