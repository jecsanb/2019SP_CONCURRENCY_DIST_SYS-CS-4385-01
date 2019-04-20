public class Philosopher2 extends  Philosopher {
    Philosopher2(int id, Fork[] forks) {
        super(id, forks);
    }
    @Override
    public void run(){
        int rightFork = id;
        int leftFork = (id + 1)%forks.length;
        int i = 0;
        while(i != MIN_EAT){
            try{
                think();
                if(id == 4){
                    forks[(leftFork)].acquire();
                    System.out.printf("Philosopher %s picked up fork: leftFork.\n",id+1);
                    forks[(rightFork)].acquire();
                    System.out.printf("Philosopher %s picked up fork: rightFork.\n",id+1);
                }else{
                    forks[rightFork].acquire();
                    System.out.printf("Philosopher %s picked up fork: rightFork.\n",id+1);
                    forks[leftFork].acquire();
                    System.out.printf("Philosopher %s picked up fork: leftFork.\n",id+1);
                }
                eat();
            }catch(InterruptedException ignored){}
            forks[rightFork].release();
            forks[leftFork].release();
            ++i;
        }
    }
}
