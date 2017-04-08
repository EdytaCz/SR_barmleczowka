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

    Klient(BarMleczny bar) {
        barmleczny = bar;
        zjedzonePosilki = 0;
    }
    
    @Override
    public void run() {
        try{
            System.out.println("Klient jest głodny!");
            barmleczny.WydajPosilek();            
        }
        catch(Exception e){
            System.out.println("Cos się zepsuło!");
        }
    }
}
