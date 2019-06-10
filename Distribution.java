import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * This is the driver of the FoodCraft program.
 */

public class Distribution {
    /** this is the amount of time before the driver ends the distribution operation. */
    private int time;
    /** this is the output of the distribution operation */
    private boolean output;

    /**
     * Runs the FoodCraft program that takes two arguments. It distributes three types of
     * ingredients bologna, bread, and cheese.
     * @param args the first argument is the amount of time the program can run.
     *             the second argument is the type of output the program will run to.
     */
    public static void main(String[] args){
        Distribution distributeDriver = new Distribution();
        if (args.length < 2) {
            System.out.println("Not enough command line arguments.");
            System.exit(0);
        } else if (args.length > 2) {
            System.out.println("To many command line arguments.");
            System.exit(1);
        } else {
            if (args[1] == "T") { 
                distributeDriver = new Distribution(Integer.parseInt(args[0]), true);
            } else {
                distributeDriver = new Distribution(Integer.parseInt(args[0]), false);
            }
        }
        distributeDriver.go();
    }

    /**
     * Default constructor for a Distribution object.
     */
    public Distribution () {
        this.time = 0;
        this.output = false;
    }

    /**
     * Constructor that creates a Distribution object based off a given time and
     * method of output.
     * @param time is a given amount of time for the program to run.
     * @param output a boolean that determines whether the output goes to a file or screen.
     */
    public Distribution (int time, boolean output) {
        this.time = time;
        try {
            PrintStream outFile = new PrintStream("log.txt");
            if (output = true) {
                if (this.time > 0) {
                    System.setOut(outFile);
                } else {
                    System.exit(2);
                }
            } else{
                System.out.println("Second argument is invalid please pass in a 'T' or 'F'");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(3);
        }
    }

    /**
     * This function will create several threads that run the program concurrently. It also creates
     * semaphores to manipulate the data.
     */
    public void go() {
        Docks dock = new Docks();
        Foreman foreman = new Foreman(dock);
        Messenger cheeseMes = new Messenger(dock, "cheese");
        Messenger breadMes = new Messenger(dock, "bread");
        Messenger bolognaMes = new Messenger(dock, "bologna");
        Miners cheeseMin = new Miners(dock, "cheese");
        Miners breadMin = new Miners(dock, "bread");
        Miners bolognaMin = new Miners(dock, "bologna");
        Thread foremanThread = new Thread(foreman);
        foremanThread.start();
        Thread cheeseMesThread = new Thread(cheeseMes);
        cheeseMesThread.start();
        Thread breadMesThread = new Thread(breadMes);
        breadMesThread.start();
        Thread bolognaMesThread = new Thread(bolognaMes);
        bolognaMesThread.start();
        Thread cheeseMinThread = new Thread(cheeseMin);
        cheeseMinThread.start();
        Thread breadMinThread = new Thread(breadMin);
        breadMinThread.start();
        Thread bolognaMinThread = new Thread(bolognaMin);
        bolognaMinThread.start();
        try{
            Thread.currentThread().join(this.time);
        } catch (InterruptedException e) {
            System.out.println("Interrupt in Distribution");
            Thread.currentThread().interrupt();
        }
    }

}
