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
public class Kucharz extends Thread {
public int liczbaWydanychposilkow;
private BarMleczny barmleczny;
public Kucharz (BarMleczny Bar){
    barmleczny=Bar;
    liczbaWydanychposilkow=0;
    
}

   @Override
    public void run() {
        try{
            barmleczny.PrzygotujPosilek();
            System.out.println("Posiłek wydany!");
        }
        catch(Exception e){
            System.out.println("Cos się zepsuło!");
        }
    }
    
    
}
