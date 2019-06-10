import java.util.concurrent.Semaphore;

/**
 * This class represents the shared memory of your program. All semaphores should be located here.
 * @author Cameron Cagle
 * @version 4/22/19
 */

public class Docks {
    /** bread messenger semaphore */
    private final Semaphore breadMes;
    /** cheese messenger semaphore */
    private final Semaphore cheeseMes;
    /** bologna messenger semaphore */
    private final Semaphore bolognaMes;
    /** foreman semaphore */
    private final Semaphore foreman;
    /** bread semaphore */
    private final Semaphore bread;
    /** cheese semaphore */
    private final Semaphore cheese;
    /** bologna semaphore */
    private final Semaphore bologna;

    /**
     * Constructor for the dock that creates all the semaphores needed.
     */
    public Docks () {
        breadMes = new Semaphore(0);
        cheeseMes = new Semaphore(0);
        bolognaMes = new Semaphore(0);
        foreman = new Semaphore(1);
        bread = new Semaphore(0);
        cheese = new Semaphore(0);
        bologna = new Semaphore(0);
    }
    /**
     * Gets the bread messenger semaphore
     * @return semaphore of the bread messenger
     */
    public Semaphore getBreadMes() {
        return breadMes;
    }

    /**
     * Gets the cheese messenger semaphore
     * @return semaphore of the cheese messenger
     */
    public Semaphore getCheeseMes() {
        return cheeseMes;
    }

    /**
     * Gets the bologna messenger semaphore
     * @return semapohre of the bologna messenger
     */
    public Semaphore getBolognaMes() {
        return bolognaMes;
    }

    /**
     * Gets the foreman semaphore
     * @return semaphore of the foreman
     */
    public Semaphore getForeman() {
        return foreman;
    }

    /**
     * Gets the bread semaphore
     * @return semaphore of the bread
     */
    public Semaphore getBread() {
        return bread;
    }

    /**
     * Gets the cheese semaphore
     * @return semaphore of the cheese
     */
    public Semaphore getCheese() {
        return cheese;
    }

    /**
     * Gets the bologna semaphore
     * @return semaphore of the bologna
     */
    public Semaphore getBologna() {
        return bologna;
    }
}

