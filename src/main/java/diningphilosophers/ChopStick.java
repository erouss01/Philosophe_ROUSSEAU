package diningphilosophers;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    // ...

    synchronized void take() throws InterruptedException {
        if(stickCount%2==0) {

            while (this.iAmFree == false) {
                wait();
            }
            stickCount=stickCount-1;
            assert (this.iAmFree == true);
            this.iAmFree = false;
            System.out.printf("Le philosophe prend la deuxième baguette"+"\n");
            notifyAll();
        }
        else{
            while (this.iAmFree == false) {
                wait();
            }
            stickCount=stickCount-1;
            assert (this.iAmFree == true);
            this.iAmFree = false;
            System.out.printf("Le philosophe prend la première baguette"+"\n");
            notifyAll();
        }


    }

    synchronized void release() throws InterruptedException {
        stickCount=stickCount+1;
        if(stickCount%2!=0) {

            assert (this.iAmFree == false);
            this.iAmFree = true;
            System.out.printf("Le philosophe repose la deuxième baguette"+"\n");
            notifyAll();
        }
        else{

            assert (this.iAmFree == false);
            this.iAmFree = true;
            System.out.printf("Le philosophe repose la première baguette"+"\n");
            notifyAll();
        }


    }
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
