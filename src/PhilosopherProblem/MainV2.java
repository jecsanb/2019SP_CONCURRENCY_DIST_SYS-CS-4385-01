/**
 * Project: Dining Philosophers Problem v2
 *
 * @author Jecsan Blanco
 * @author Mary (Rachel) Van Pelt
 * Ueses philosopher2 which  implements  algo 6.11
 */
public class MainV2  extends  Main {
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
    public static class Philosopher2 extends Philosopher {
        Philosopher2(int id, Fork[] forks) {
            super(id, forks);
        }

        //implements execution of algorithm 6.12
        @Override
        public void run() {
            int rightFork = id;
            int leftFork = (id + 1) % forks.length;
            int i = 0;
            while (i != MIN_EAT) { //to infinity if i is -1
                try {
                    think();
                    if (id == forks.length - 1) {
                        forks[rightFork].acquire();
                        pickedUp(id, "rightFork");
                        forks[leftFork].acquire();
                        pickedUp(id, "leftFork");
                    } else {
                        forks[(leftFork)].acquire();
                        pickedUp(id, "leftFork");
                        forks[(rightFork)].acquire();
                        pickedUp(id, "rightFork");
                    }
                    eat();
                } catch (InterruptedException ignored) {
                }
                if(id == forks.length -1){
                    forks[rightFork].release();
                    setDown(id, "rightFork");
                    forks[leftFork].release();
                    setDown(id, "leftFork");

                }else{
                    forks[leftFork].release();
                    setDown(id, "leftFork");
                    forks[rightFork].release();
                    setDown(id, "rightFork");

                }
                ++i;
            }
        }
    }
}

