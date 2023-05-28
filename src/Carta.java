package src;

/**
 * @author Vincenzo
 */
public class Carta implements Comparable {

    private int numero;   //il numero della carta
    private Seme seme;    //il seme della carta
    private float valorepunteggio = 0;  //il valore del punteggio della carta
    private int valoregioco = 0;  //il valore di gioco della carta

    /**
     * Genera una Carta con i parametri passati
     *
     * @param a numero della carta
     * @param b seme della carta
     * @param c valore del punteggio della carta
     * @param d valore di gioco della carta
     */
    public Carta(int a, Seme b, float c, int d) //costruttore della carta
    {
        this.numero = a;
        this.seme = b;
        this.valorepunteggio = c;
        this.valoregioco = d;
    }

    //metodi set
    /**
     * Modifica il numero della carta
     *
     * @param a numero della carta
     */
    private void setnumero(int a) {       //l'intero a diventa il nuovo numero
        numero = a;
    }

    /**
     * Modifica il seme della carta
     *
     * @param a seme della carta
     */
    private void setseme(Seme a) {         //il seme a diventa il nuovo seme
        seme = a;
    }

    /**
     * Modifica il valore del punteggio della carta
     *
     * @param a valore del punteggio della carta
     */
    private void setvalorepunteggio(float a) {    //il float a diventa il nuovo valorepunteggio
        valorepunteggio = a;
    }

    /**
     * Modifica il valore da gioco della carta
     *
     * @param a valore da gioco della carta
     */
    private void setvaloregioco(int a) {     //l'intero a diventa il nuovo valoregioco
        valoregioco = a;
    }

    //metodi get
    /**
     * Restituisce il numero della carta
     *
     * @return il numero della carta
     */
    public int getnumero() {  //restituisce il numero della carta.
        return numero;
    }

    /**
     * Restituisce il seme della carta
     *
     * @return il seme della carta
     */
    public Seme getseme() {   //restituisce il seme della carta.
        return seme;
    }

    /**
     * Restituisce il valore del punteggio della carta
     *
     * @return il valore del punteggio della carta
     */
    public float getvalorepunteggio() {
        return valorepunteggio;
    }

    /**
     * Restituisce il valore da gioco della carta
     *
     * @return il valore da gioco della carta
     */
    public int getvaloregioco() {
        return valoregioco;
    }

    /**
     * Stampa una carta con numero e seme
     *
     * @return la stampa del numero e del seme della carta
     */
    @Override
    public String toString() {
        return +numero + " di " + seme + "";
    }

    /**
     * Verifica se 2 carte hanno lo stesso seme
     *
     * @return true se le carte hanno lo stesso seme, altrimenti false
     */
    public boolean stessoseme(Carta a) {
        if (this.seme.compareTo(a.getseme()) == 0) { //Sto comparando le posizioni dell'enum {0 1 2 3}
            return true;  //le 2 carte hanno lo stesso seme
        } else {
            return false;  //le 2 carte non hanno lo stesso seme
        }
    }

    /**
     * Si ordina un oggetto passato per seme e numero
     *
     * @param o Object
     * @return 0 se Integer è uguale all'argomento, -1 se Integer è minore
     * dell'argomento, 1 se Integer è maggiore dell'argomento
     *
     */
    @Override
    public int compareTo(Object o) {
        Carta c = (Carta) o;
        if (this.seme.ordinal() < c.seme.ordinal()) {
            return -1;
        }
        if (this.seme.ordinal() > c.seme.ordinal()) {
            return 1;
        }
        if (this.numero < c.numero) {
            return -1;
        }
        if (this.numero > c.numero) {
            return 1;
        } else {
            return 0;
        }
    }
;
}