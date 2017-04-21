/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barmleczny;

/**
 *
 * @author edyta
 */
public class Klient extends Thread {

    private BarMleczny barmleczny;
    public int zjedzonePosilki;
    private boolean interrupt=false;

    Klient(BarMleczny bar) {
        barmleczny = bar;
        zjedzonePosilki = 0;
    }
    
    @Override
    public void run() {
        try{while(true){
            System.out.println("Klient "+Thread.currentThread().getName()+" jest głodny!");
            zjedzonePosilki+=barmleczny.WydajPosilek();  
            System.out.println("Klient "+Thread.currentThread().getName()+" zjadl posilek");
            
            yield();
        }
        }
        catch(Exception e){
            System.out.println("Spadam bo bar zamkniety");
            interrupt=true;
        }
    }
      public boolean Interrupt(){
        return interrupt;
}
     public int LiczbaZjedzonychPosilkow(){
    return zjedzonePosilki;
     }
}
