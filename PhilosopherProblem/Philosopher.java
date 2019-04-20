public class Philosopher extends Thread {
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
        this.EAT_DELAY = 100;
        this.THINK_DELAY = 100;
    }
    Philosopher(int id, Fork[] forks,int minEat,int thinkDelay,int eatDelay){
        this.forks = forks;
        this.id = id;
        this.MIN_EAT = minEat;
        this.EAT_DELAY = eatDelay;
        this.THINK_DELAY = thinkDelay;
    }

    //execution of algorithm 6.10
    @Override
    public void run() {
        int rightFork = id;
        int leftFork = (id + 1) % forks.length;
        int i = 0; // how many times to try and eat
        while (i != MIN_EAT) { // to infinity if i is -1
            try {
                think();
                forks[rightFork].acquire();
                System.out.printf("Philosopher %s picked up fork: rightFork.\n", id + 1);
                forks[(leftFork)].acquire();
                System.out.printf("Philosopher %s picked up fork: leftFork.\n", id + 1 );
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
