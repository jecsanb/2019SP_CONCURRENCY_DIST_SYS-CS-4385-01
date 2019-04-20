/**
 * Project: Dining Philosophers Problem v2
 * @author  Jecsan Blanco
 * @author  Mary (Rachel) Van Pelt
 * Ueses philosopher2 which  implements  algo 6.11
 */
public class MainV2 {
    public static void main(String[] args) throws InterruptedException {
       int count = args.length == 0 ? 5 : Integer.parseInt(args[0]);
        System.out.println("Starting..");
        //Data containers
        Philosopher2[] philosophers = new Philosopher2[count];
        Fork[] forks = new Fork[philosophers.length];
        // Make the table
        for (int id = 0; id < philosophers.length; ++id) {
            forks[id] = new Fork();
        }
        // Seat the philosophers
        for (int id = 0; id < philosophers.length; ++id) {
            philosophers[id] = new Philosopher2(id, forks);
            //let them do what they do
            philosophers[id].start();
        }
        // let them go home
        for (Philosopher2 phil : philosophers) {
            phil.join();
        }
        System.out.println("Done..");

    }
}

