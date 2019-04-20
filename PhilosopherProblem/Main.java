/**
 * Think and Eat v1
 * By Jecsan B  and Rachel V.
 */

import java.util.concurrent.Semaphore;

class Main {
    private static final int MIN_EAT_PER_PHIL = 100;
    private static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 5;

    private static final int THINK_DELAY = 100;
    private static final int EAT_DELAY = 100;

    public static void main(String[] args) throws InterruptedException {
        // assumes a parsable int is passed.
        int number_of_philosophers = args[0].equals("") ? DEFAULT_NUMBER_OF_PHILOSOPHERS : Integer.parseInt(args[0]);
        System.out.println("Starting...");

        //Data containers
        Philosopher[] philosophers = new Philosopher[number_of_philosophers];
        Fork[] forks = new Fork[philosophers.length];

        // Make the table, forks are constant
        for (int i = 0; i < philosophers.length; ++i) {
            forks[i] = new Fork();
        }
        // Seat the philosophers
        for (int id = 0; id < philosophers.length; ++id) {
            philosophers[id] = new Philosopher(id, forks);
            //let them do what they do
            philosophers[id].start();
        }
        // wait until all of them are done
        for (Philosopher philosopher : philosophers) {
            philosopher.join();
        }
        System.out.println("Done..");

    }

    public static class Fork extends Semaphore {
        //Creates a fork as a semaphore
        Fork() {
            super(1);
        }
    }

    public static class Philosopher extends Thread {
        private Fork[] forks;
        private int id;

        // Creates a philosopher  with an id and gives it access to the forks
        Philosopher(int id, Fork[] forks) {
            this.forks = forks;
            this.id = id;
        }

        //execution of algorithm 6.10
        @Override
        public void run() {
            int rightFork = id;
            int leftFork = (id + 1) % forks.length;
            int i = 0; // how many times to try and eat
            while (i < MIN_EAT_PER_PHIL) {
                try {
                    think();
                    forks[rightFork].acquire();
                    System.out.printf("Philosopher %s picked up fork: rightFork.\n", id);
                    forks[(leftFork)].acquire();
                    System.out.printf("Philosopher %s picked up fork: leftFork.\n", id);
                    eat();
                } catch (InterruptedException ignored) {
                }
                forks[rightFork].release();
                forks[leftFork].release();
                ++i;
            }
        }

        void think() throws InterruptedException {
            System.out.printf("Philosopher %s: Thinking.\n", id);
            sleep((int) (Math.random() * THINK_DELAY));
        }

        void eat() throws InterruptedException {
            System.out.printf("Philosopher %s: Eating.\n", id);
            sleep((int) (Math.random() * EAT_DELAY));
        }
    }

}
