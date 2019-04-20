public class Philosopher2 extends Philosopher {
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
                    forks[(leftFork)].acquire();
                    System.out.printf("Philosopher %s picked up fork: leftFork.\n", id + 1);
                    forks[(rightFork)].acquire();
                    System.out.printf("Philosopher %s picked up fork: rightFork.\n", id + 1);
                } else {
                    forks[rightFork].acquire();
                    System.out.printf("Philosopher %s picked up fork: rightFork.\n", id + 1);
                    forks[leftFork].acquire();
                    System.out.printf("Philosopher %s picked up fork: leftFork.\n", id + 1);
                }
                eat();
            } catch (InterruptedException ignored) {
            }
            forks[rightFork].release();
            forks[leftFork].release();
            ++i;
        }
    }
}
