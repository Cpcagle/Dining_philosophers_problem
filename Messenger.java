import java.util.concurrent.Semaphore;

/**
 * The messenger class listens for signals and sends signals, carrying supplies if needed. If 
 * cannot send messages or supplies to the foreman.
 * @author Cameron Cagle
 * @version 4/22/19
 */

public class Messenger implements Runnable{
    /** the shared memory among the program */
    public Docks dock;
    /** the type of food the messenger delivers too */
    public String foodType;

    /**
     * Constructor for the Messenger object that holds shared memory and the type of food the
     * messenger communicates with.
     * @param dock shared memory that holds semaphores
     * @param type type of food the messenger communicates with.
     */
    public Messenger (Docks dock, String type) {
        this.dock = dock;
        this.foodType = type;
    }

    /**
     * This function will determine if the ingredients given are able to be delivered to the miner.
     * Cheese needs bread and bologna, bread needs cheese and bologna, and bologna needs bread and
     * cheese.
     */
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            boolean results = false;
            try{
                switch(this.foodType){
                    case "cheese" :
                        this.dock.getBologna().acquire();
                        results = this.dock.getBread().tryAcquire();
                        if (!results) {
                            this.dock.getBologna().release();
                        } else {
                            this.dock.getCheeseMes().release();
                            System.out.println("Cheese miners obtained supplies");
                            System.out.flush();
                        }
                        break;
                    case "bread" :
                        this.dock.getCheese().acquire();
                        results = this.dock.getBologna().tryAcquire();
                        if (!results) {
                            this.dock.getCheese().release();
                        } else {
                            this.dock.getBreadMes().release();
                            System.out.println("Bread miners obtained supplies");
                            System.out.flush();
                        }
                        break;
                    case "bologna" :
                        this.dock.getBread().acquire();
                        results = this.dock.getCheese().tryAcquire();
                        if (!results) {
                            this.dock.getBread().release();
                        } else {
                            this.dock.getBolognaMes().release();
                            System.out.println("Bologna miners obtained supplies");
                            System.out.flush();
                        }
                        break;
                }
                System.out.flush();
            } catch (InterruptedException e){
                System.out.println("Interrupt in Messenger");
                Thread.currentThread().interrupt();
            }
        }
    }
}


