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
    public static void main(String[] args) {

        // TODO code application logic here
    }

    public void OtworzBar() {
        praca = true;
        kucharz.start();
        for (Klient klienci : klienci) {
            klienci.interrupt();
        }
    }

    public synchronized void PrzygotujPosilek(Posilek item) throws InterruptedException {
        czekajNaWydanie();
        posilek.wsadz(item);
        notifyAll();
    }

    public synchronized int WydajPosilek() throws InterruptedException {

        czekajNaPosilek();
        posilek.Zdejmij();
        notifyAll();
        return 1;
    }

    private synchronized void czekajNaWydanie() throws InterruptedException {
        while (!praca) {
            System.out.println(Thread.currentThread().getName() + " Czeka na posilek");
            wait();
        }
    }

    private synchronized void czekajNaPosilek() throws InterruptedException {
while (!posilek.CzyPusty())
        {
            System.out.println(Thread.currentThread().getName()+" czeka na odbior klienta");
            wait();
}
    }

}
