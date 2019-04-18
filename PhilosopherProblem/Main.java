/**
 * Think and Eat v1
 * By Jecsan B  and Rachel V.
 */
import java.util.concurrent.Semaphore;
class Main{
    private  static final int MIN_EAT_PER_PHIL = 1;
    private  static final int NUMBER_OF_PHILS = 5;

    private  static final int THINK_DELAY = 100;
    private  static final int EAT_DELAY = 100;
    public static void main(String[] args) throws InterruptedException{

         System.out.println("Starting..");
        //Data containers
         Philosopher[] phils = new Philosopher[NUMBER_OF_PHILS];
         Fork[]  forks = new Fork[phils.length];
         // Make the table
         for(int id = 0; id < phils.length; ++id){
            forks[id] = new Fork(id);
         }
         // Seat the phiosophers 
         for(int id = 0; id < phils.length; ++id){
            phils[id] =  new Philosopher(id,forks);
            //let them do what they do
            phils[id].start();
         }
         // let them go home
         for(int id = 0; id < phils.length; ++id){
            phils[id].join();
         }
         System.out.println("Done..");

    }
    public static class Fork extends Semaphore{
        private final int id;

        //Creates a fork as a semaphore with an id;
        public Fork(int id){
            super(1);
            this.id = id;
            System.out.printf("Fork %s added.\n",id);
        }
    }

    public static class Philosopher extends Thread{
        private Fork[] forks;
        private int id;
        // Creates a philospher  with an id and gives it access to the forks
        public Philosopher(int id,Fork[] forks){
               this.forks =forks;
               this.id = id;
               System.out.printf("Phil %s added.\n",id);
        }
        //execution of algorithm 6.10
        @Override
        public void run(){
                    int rightFork = id;
                    int leftFork = (id + 1)%forks.length;
                    int i = 0;
                    while(i < MIN_EAT_PER_PHIL){
                        try{
                            think();
                            forks[rightFork].acquire();
                            System.out.printf("Philosopher %s pickedup fork: rightFork.\n",id);
                            forks[(leftFork)].acquire();
                            System.out.printf("Philosopher %s pickedup fork: leftFork.\n",id);
                            eat();
                        }catch(InterruptedException e){}
                        forks[rightFork].release();
                        forks[leftFork].release();
                        ++i;
                    }
        }
        public void think() throws InterruptedException{
            System.out.printf("Philosopher %s: Thinking.\n",id);
            sleep((int)(Math.random() * THINK_DELAY));
        }
        public void eat() throws InterruptedException{
            System.out.printf("Philosopher %s: Eating.\n",id);
            sleep((int)(Math.random() * EAT_DELAY));
        }
    }

}
