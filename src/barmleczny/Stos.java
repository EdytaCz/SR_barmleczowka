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
public class Stos<Typ> {

    private class Wezel {

        public Typ Obiekt;
        public Wezel Nastepnik;

        public Wezel(Typ obiekt, Wezel nastepnik) {
            Obiekt = obiekt;
            Nastepnik = nastepnik;
        }
    }
    private Wezel goraStosu = null;

    public void wsadz(Typ item) {
        goraStosu = new Wezel(item, goraStosu);
    }

    public Typ Zdejmij() {
        Typ item = Dobierz();
        goraStosu = goraStosu.Nastepnik;
        return item;
    }

    private Typ Dobierz() {
        if (goraStosu == null) {
            return null;
        }
        return goraStosu.Obiekt;
    }

    public Typ Sprawdz() {
        if (goraStosu == null) {
            return null;
        }
        return goraStosu.Obiekt;
    }

    public int Rozmiar() {
        int licznik = 0;
        Wezel wezel = goraStosu;
        while (wezel != null) {
            licznik++;
            wezel = wezel.Nastepnik;
        }
        return licznik;
    }

    public boolean CzyPusty() {
        return goraStosu == null;
    }

}
