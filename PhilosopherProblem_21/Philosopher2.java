public class Philosopher2 extends Philosopher {
    Philosopher2(int id, Fork[] forks) {
        super(id, forks);
    }

    //implements execution of algorithm 6.12
    @Override
    public void run() {
        int leftFork = id;
        int rightFork = (id + 1) % forks.length;
        int i = 0;
        while (i != MIN_EAT) { //to infinity if i is -1
            try {
                think();
                if (id == forks.length - 1) {
                    forks[rightFork].acquire();
                    System.out.printf("Philosopher %s picked up right Fork.\n", id);
                    forks[leftFork].acquire();
                    System.out.printf("Philosopher %s picked up left Fork.\n", id);
                } else {
                    forks[(leftFork)].acquire();
                    System.out.printf("Philosopher %s picked up left Fork.\n", id);
                    forks[(rightFork)].acquire();
                    System.out.printf("Philosopher %s picked up right Fork.\n", id);
                }
                eat();
            } catch (InterruptedException ignored) {
            }
            if (id == forks.length - 1) {
                forks[rightFork].release();
                    System.out.printf("Philosopher %s dropped right Fork.\n", id);
                forks[leftFork].release();
                    System.out.printf("Philosopher %s dropped left Fork.\n", id);
            }
            else{
                forks[leftFork].release();
                    System.out.printf("Philosopher %s dropped left Fork.\n", id);
                forks[rightFork].release();
                    System.out.printf("Philosopher %s dropped right Fork.\n", id);
            }
            ++i;
        }
    }
}
