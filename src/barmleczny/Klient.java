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
    Klient(BarMleczny bar){
    barmleczny=bar;
    zjedzonePosilki=0;
        
            }
}
