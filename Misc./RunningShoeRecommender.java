import java.util.*;

public class RunningShoeRecommender implements Problem<String>{
    private String shoeType;
    private final int numShoes;

    public RunningShoeRecommender(String shoeType, int numShoes) {
        this.shoeType = shoeType;
        this.numShoes = numShoes;
    }

    public boolean isSuccess(List<String> soFar) {
        return soFar.size() == numShoes;
    }

    public boolean isPartial(List<String> soFar) {
        return soFar.size() < numShoes;
    }

    public Iterable<String> options() {
        if (shoeType.equalsIgnoreCase("Daily Trainer")) {
            return List.of("Nike Air Zoom Pegasus 38", "Nike Air Zoom Invincible", "Hoka One One Mach 4",
            "Brooks Adrenaline GTS 21");
        } else if (shoeType.equalsIgnoreCase("Racing Spike")) {
            return List.of("Nike Zoom Victory Elite 2", "Nike Dragonfly", "Nike Maxfly");
        } else if (shoeType.equalsIgnoreCase("Super Shoe")){
            return List.of("Nike Zoom Next%", "Nike Zoom Alphafly Next%", "Asics Metaspeed Sky",
            "New Balance Fuelcell RC", "Brooks Hyperion Elite 3");
        } 
        return List.of("");
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("What sort of running shoe are you interested in? (Daily Trainer, Racing Spike, Super Shoe)");
        String shoeType = console.nextLine();

        System.out.println("How many shoes will you be ordering?");
        int numOrdered = console.nextInt();

        RunningShoeRecommender shoes = new RunningShoeRecommender(shoeType, numOrdered);
        shoes.enumerate();
    }
}

// a recursive enumeration problem composed of the given element type
interface Problem<E> {

    // returns true if the given list is a valid, successful combination
    public boolean isSuccess(List<E> soFar);

    // returns true if the given list is a valid, partial combination
    public boolean isPartial(List<E> soFar);

    // returns all the options that can be explored
    public Iterable<E> options();

    // prints-out all the valid, successful combinations of options
    public default void enumerate() {
        ArrayList<E> optionsAL = new ArrayList<E>();
        for (E option : options()){
           optionsAL.add(option);
        }
        enumerate(new ArrayList<E>(), optionsAL);
    }

    private void enumerate(List<E> soFar, List<E> optionsAL) {
        if (isSuccess(soFar)) {
            System.out.println(soFar);
        } else if (isPartial(soFar)) {
            for (int i = 0; i < optionsAL.size(); i++) {
                //soFar.add(option);
                //enumerate(soFar);
                //soFar.remove(soFar.size() - 1);
                soFar.add(optionsAL.remove(i));
                enumerate(soFar,optionsAL);
                optionsAL.add(i,soFar.remove(soFar.size()-1));
            }
        }
    }
}