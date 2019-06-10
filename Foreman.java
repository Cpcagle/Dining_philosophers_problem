import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * 
 * The foreman should randomly pick two sets of supplies, send a signal for each type of supply
 * delivered to the docks. He will not deliver any more supplies until all the previously 
 * delivered supplies have been consumed. The Foreman has unlimited supply of bread, cheese,
 * and bologna.
 * @author Cameron Cagle
 * @version 4/22/19
 */

public class Foreman implements Runnable {
    /** shared memory among the program holds all semaphores */
    public Docks dock;
    /** number of ingredients to make a sandwich. */
    private final int NUMFOOD = 3;

    /**
     * Constructor for the foreman object that holds the shared memory.
     * @param dock shared memory in the program.
     */
    public Foreman (Docks dock){
        this.dock = dock;
    }

    /**
     * Delivers two ingredients to the dock. The ingredients are random every time and
     * are dependent on the miners. When a miner takes ingredients he will notify the foreman.
     */
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try{
                dock.getForeman().acquire();
                System.out.println("\n\nForeman is delivering food");
                System.out.flush();
            } catch (InterruptedException e) {
                System.out.println("Interrupt in Foreman");
                Thread.currentThread().interrupt();
            }
            Random randomGenerator = new Random();
            int foodItem = randomGenerator.nextInt(NUMFOOD);
            if (foodItem == 0) {
                this.dock.getBread().release();
                this.dock.getBologna().release();
                System.out.println("Bread and Bologna Delivered");
                System.out.flush();
            } else if (foodItem == 1) {
                this.dock.getCheese().release();
                this.dock.getBologna().release();
                System.out.println("Cheese and Bologna Delivered");
                System.out.flush();
            } else {
                this.dock.getCheese().release();
                this.dock.getBread().release();
                System.out.println("Bread and Cheese Delivered");
                System.out.flush();
            }
        }
    }

}
