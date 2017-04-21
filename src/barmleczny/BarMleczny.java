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
public class BarMleczny {

    private Kucharz kucharz;
    private Klient[] klienci;
    private Stos<Posilek> posilek;
    private boolean praca;

    public BarMleczny(int liczbaKlientow) {
        posilek = new Stos<Posilek>();
        kucharz = new Kucharz(this);
        kucharz.setName("kucharz");

        klienci = new Klient[liczbaKlientow];
        for (int i = 0; i < liczbaKlientow; i++) {
            Klient nowyKlient = new Klient(this);
            nowyKlient.setName("klient" + i);
            klienci[i] = nowyKlient;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        BarMleczny bar = new BarMleczny(10);
        bar.OtworzBar();
        Thread.sleep(100);        
        bar.SkonczPrace();
         if (bar.IsReadyToSummarry())bar.Podsumowanie();
        //if (bar.IsReadyToSummarry())bar.WriteSummary();
        
        

    }
       public boolean IsReadyToSummarry(){
    
    boolean hasProgramFinished = false;
        while (!hasProgramFinished) {
            int counter = 0;
            for (Klient klient : klienci) {
                if (klient.Interrupt()) {
                    counter++;
                }
            }
            if (counter==klienci.length) {
                hasProgramFinished=true;
            }
        }
        return hasProgramFinished;
}
 public void Podsumowanie() {

        
        System.out.println("================================ \n");
        for (Klient klient: klienci) {
            int zjedzonePosilki = klient.LiczbaZjedzonychPosilkow();
            System.out.println("Klient" + klient.getName() + " zjadł " + zjedzonePosilki + "\n");
        }
       int wydanePosilki = kucharz.LiczbaWydanychPosilkow();
        System.out.println("Kucharz wydał "+wydanePosilki);
    }

    public void OtworzBar() {
        praca = true;
        kucharz.start();
        for (Klient klient : klienci) {
            klient.start();
        }
    }
    
    public void SkonczPrace(){
        praca=false;
        kucharz.interrupt();
        for (Klient klienci : klienci) {
            klienci.interrupt();
        }
    }
    
    public synchronized void PrzygotujPosilek() throws InterruptedException {
        czekajNaWydanie();
        posilek.wsadz(new Posilek());
        notifyAll();
    }

    public synchronized int WydajPosilek() throws InterruptedException {

        czekajNaPosilek();
        posilek.Zdejmij();
        notifyAll();
        return 1;
    }
       private synchronized void CzekajNaPusty() throws InterruptedException {
        while (!posilek.CzyPusty()) {
            System.out.println(Thread.currentThread().getName() + " Czeka na posilek");
            wait();
        }
}
    private synchronized void czekajNaWydanie() throws InterruptedException {
        while (!praca) {
            System.out.println(Thread.currentThread().getName() + " Czeka na posilek");
            wait();
        }
    }

    private synchronized void czekajNaPosilek() throws InterruptedException {
        while (posilek.CzyPusty())
        {
            System.out.println(Thread.currentThread().getName()+"Nie dostal posilku");
            wait();
}
    }

}
