/**
 * Project: Dining Philosophers Problem
 * @author  Jecsan B
 * @author  Rachel v.
 *  * Ueses philosopher2 which  implements  algo 6.10
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
}
