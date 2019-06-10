import java.util.Random;
import static java.lang.Thread.sleep;

/**
 * The miner class must wait until all needed suppplies are available, get them and call 
 * makeSandwiches method, then calls a eatSandwiches method. Other processes are able to gather
 * supplies and make and eat, while other groups of miners are making their sandwiches or eating.
 * @author Cameron Cagle
 * @version 4/22/19
 *
 */

public class Miners implements Runnable {
    /** this holds shared memory of the semaphores */
    public Docks dock;
    /** this is a string for type of food the miners are */
    public String foodType;

    /**
     * Constructor for the Miners object that holds the shared memory and type.
     * @param dock shared memory among the program, holds all semaphores.
     * @param foodType type of food the miners have infinite amount of.
     */
    public Miners (Docks dock, String foodType) {
        this.dock = dock;
        this.foodType = foodType;
    }

    /**
     * This function acquires and releases the proper types of ingredients. When the ingredients
     * are acquired they will notify the miners.
     */
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()) {
                switch (this.foodType) {
                    case "cheese":
                        this.dock.getCheeseMes().acquire();
                        this.dock.getForeman().release();
                        System.out.println("Cheese miners are making sandwhiches");
                        System.out.flush();
                        this.makeSandwiches();
                        System.out.println("Cheese miners are eating sandwhiches");
                        System.out.flush();
                        this.eatSandwiches();
                        break;
                    case "bread":
                        this.dock.getBreadMes().acquire();
                        this.dock.getForeman().release();
                        System.out.println("Bread miners are making sandwhiches");
                        System.out.flush();
                        this.makeSandwiches();
                        System.out.println("Bread miners are eating sandwhiches");
                        System.out.flush();
                        this.eatSandwiches();
                        break;
                    case "bologna":
                        this.dock.getBolognaMes().acquire();
                        this.dock.getForeman().release();
                        System.out.println("Bologna miners are making sandwhiches");
                        System.out.flush();
                        this.makeSandwiches();
                        System.out.println("Bologna miners are eating sandwhiches");
                        System.out.flush();
                        this.eatSandwiches();
                        break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupt in Miners");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This function makes sand whiches for a random length of time.
     */
    public void makeSandwiches() {  // this takes a random amount of time to complete
        Random randTime = new Random();
        try{
            Thread.sleep(randTime.nextInt(3)* 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupt in Sandwiches");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This function eats sand whiches for a random length of time.
     */
    public void eatSandwiches() {  // this takes a random amount of time to complete
        Random randTime = new Random();
        try{
            Thread.sleep(randTime.nextInt(3) * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupt in Sandwiches");
            Thread.currentThread().interrupt();
        }
    }

}

