import java.util.concurrent.Semaphore;

class Fork extends Semaphore {
    //Creates a fork as a semaphore
    Fork() {
        super(1);
    }
}