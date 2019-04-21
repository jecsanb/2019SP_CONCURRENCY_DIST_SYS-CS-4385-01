import java.util.concurrent.Semaphore;

class Fork extends Semaphore {
    //Creates a fork as a semaphore
    //for problem terminology
    Fork() {
        super(1);
    }
}