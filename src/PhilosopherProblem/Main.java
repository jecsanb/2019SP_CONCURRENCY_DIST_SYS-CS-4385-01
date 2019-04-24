import java.util.concurrent.Semaphore;
/**
 *
 * Project: Dining Philosophers Problem
 *
 * @author Jecsan B
 * @author Mary (Rachel) Van Pelt
 * * Ueses philosopher which  implements  algo 6.10
 */
class Main {
    public static void main(String[] args) throws InterruptedException {
        int count = args.length < 1 ? 5 : Integer.parseInt(args[0]);
        System.out.println("Starting..");
        //Data containers
        Philosopher[] philosophers = new Philosopher[count];
        Fork[] forks = new Fork[philosophers.length];
        // Make the table
        for (int id = 0; id < philosophers.length; ++id) {
            forks[id] = new Fork();
        }
        // Seat the philosophers
        for (int id = 0; id < philosophers.length; ++id) {
            philosophers[id] = new Philosopher(id, forks);
            //let them do what they do
            philosophers[id].start();
        }
        // let them go home
        for (Philosopher phil : philosophers) {
            phil.join();
        }
        System.out.println("Done..");

    }
    static class Fork extends Semaphore {
        //Creates a fork as a semaphore
        //for problem terminology
        Fork() {
            super(1);
        }
    }
    /**
     * Five philosophers either eat or think
     * They must have two forks to eat
     * Can only use forks on either side of their plate
     * Cannot forcefully obtain a fork (no preemption)
     */

    public static class Philosopher extends Thread {
        Fork[] forks;
        final int id;
        final int MIN_EAT;
        final int THINK_DELAY;
        final int EAT_DELAY;

        // Creates a philosopher  with an id and gives it access to the forks
        Philosopher(int id, Fork[] forks) {
            this.forks = forks;
            this.id = id;
            this.MIN_EAT = -1; //infinity
            this.EAT_DELAY = 1000;
            this.THINK_DELAY = 1000;
        }

        Philosopher(int id, Fork[] forks, int minEat, int thinkDelay, int eatDelay) {
            this.forks = forks;
            this.id = id;
            this.MIN_EAT = minEat;
            this.EAT_DELAY = eatDelay;
            this.THINK_DELAY = thinkDelay;
        }

        //implements execution of algorithm 6.10
        @Override
        public void run() {
            int rightFork = id;
            int leftFork = (id + 1) % forks.length;
            int i = 0; // how many times to try and eat
            while (i != MIN_EAT) { // to infinity if i is -1
                try {
                    think();
                    forks[(leftFork)].acquire();
                    pickedUp(id, "leftFork");
                    forks[rightFork].acquire();
                    pickedUp(id, "rightFork");
                    eat();
                } catch (InterruptedException ignored) {
                }
                forks[leftFork].release();
                setDown(id, "leftFork");
                forks[rightFork].release();
                setDown(id, "rightFork");
                ++i;
            }
        }

        synchronized void pickedUp(int id, String fork) {
            System.out.printf("Philosopher %s picked up %s\n", id, fork);
        }

        synchronized void setDown(int id, String fork) {
            System.out.printf("Philosopher %s set down  %s\n", id, fork);
        }

        synchronized void think() throws InterruptedException {
            System.out.printf("Philosopher %s: Thinking.\n", id);
            sleep((int) (Math.random() * THINK_DELAY));
        }

        synchronized void eat() throws InterruptedException {
            System.out.printf("Philosopher %s: Eating.\n", id);
            sleep((int) (Math.random() * EAT_DELAY));
        }

    }

}
