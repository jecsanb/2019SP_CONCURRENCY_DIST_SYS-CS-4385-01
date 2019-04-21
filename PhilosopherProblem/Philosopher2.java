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
                    pickedUp(id,"leftFork");
                    forks[(rightFork)].acquire();
                    pickedUp(id,"rightFork");
                } else {
                    forks[rightFork].acquire();
                    pickedUp(id,"rightFork");
                    forks[leftFork].acquire();
                    pickedUp(id,"leftFork");
                }
                eat();
            } catch (InterruptedException ignored) {
            }
            forks[leftFork].release();
            setDown(id,"leftFork");
            forks[rightFork].release();
            setDown(id,"rightFork");
            ++i;
        }
    }
}
