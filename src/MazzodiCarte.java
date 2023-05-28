package src;


import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vincenzo Cavallo
 * @author Enrico Mauceri
 */
public class MazzodiCarte {

    private ArrayList<Carta> mazzo=new ArrayList<Carta>(); //mazzo è un ArrayList di carte vuoto
    private int index;    //indice

    /**
     * Inizializza l'indice a 0
     */
    public MazzodiCarte() {                   //costruttore di un MazzodiCarte
           index = 0; //indice
  }

    /**
     * Inizializza MazzodiCarte
     * @param a un MazzodiCarte
     */
    public MazzodiCarte(MazzodiCarte a)
    {
        mazzo = a.mazzo;
        index=a.index;
    }
    /**
     * Riempie il mazzo di 40 carte (carte classiche napoletane)
     */
    public void mazzodi40carte() {
        for (Seme s : Seme.values()) {   //ciclo generalizzato
            for (int i = 1; i <= 10; i++) {
                switch (i) {
                    case 1:
                        mazzo.add(new Carta(i, s, 1, 8));
                        break;
                    case 2:
                        mazzo.add(new Carta(i, s, (float) 1/3, 9));
                        break;
                    case 3:
                        mazzo.add(new Carta(i, s, (float) 1/3, 10));
                        break;
                    case 4:
                        mazzo.add(new Carta(i, s, 0, 1));
                        break;
                    case 5:
                        mazzo.add(new Carta(i, s, 0, 2));
                        break;
                    case 6:
                        mazzo.add(new Carta(i, s, 0, 3));
                        break;
                    case 7:
                        mazzo.add(new Carta(i, s, 0, 4));
                        break;
                    case 8:
                        mazzo.add(new Carta(i, s, (float) 1/3 , 5));
                        break;
                    case 9:
                        mazzo.add(new Carta(i, s, (float) 1/3, 6));
                        break;
                    case 10:
                        mazzo.add(new Carta(i, s, (float) 1/3, 7));
                        break;

                    default:
                        break;

                }
            }
        }

    }

    //metodi set
    /**
     * Modifica il mazzo di carte
     *
     * @param a ArrayList di carte
     */
    private void setmazzo(ArrayList<Carta> a) {   //l'ArrayList di carte a diventa il nuovo mazzo
        mazzo = a;
    }

    /**
     * Modifica l'indice
     *
     * @param a numero dell'indice
     */
    private void setindex(int a) {   //l'intero a diventa il nuovo indice
        index = a;
    }

    //metodi get
    /**
     * Restituisce l'ArrayList di carte mazzo
     *
     * @return l'ArrayList di carte
     */
    public ArrayList<Carta> getmazzo() {
        return mazzo;
    }

    /**
     * Restituisce l'indice
     *
     * @return l'indice
     */
    public int getindex() {
        return index;
    }

    /**
     * Stampa un mazzo di carte
     *
     * @return la stampa del mazzo da giocare con l'indice della posizione
     */
    @Override
    public String toString() {
        String s = "";
        int i = 0;
        for (Carta c : mazzo) {      //ciclo generalizzato
            s = s + c + "\tpremere " + i + " per giocare questa carta\n";
            i++;
        }
        return s;
    }

    /**
     * Mischia il mazzo di carte
     */
    public void mischia() {
        Collections.shuffle(mazzo);
    }

    /**
     * Ordina per numero e seme il mazzo di carte
     */
    public void ordinapernumeroeseme() {
        Collections.sort(this.getmazzo());

    }

    /**
     * Rimuove e restituisce la carta dalla posizione index del mazzo di carte
     *
     * @return la prima carta dal mazzo di carte
     */
    public Carta daicarta() {     //Toglie la prima carta dal mazzo di carta e me la restituisce
        Carta estratta = this.mazzo.remove(index);  //rimuove dal mazzo la carta dalla posizione index che è inizializzato a 0
        index = index++; //index=index+1
        return estratta;

    }

    /**
     * Aggiunge la carta al mazzo e il mazzo viene ordinato per numero e seme
     *
     * @param a carta
     */
    public void aggiungicartaeordina(Carta a) {    //aggiunge la carta a alla fine del mazzo di carte

        mazzo.add(a);  //la carta a viene aggiunta alla fine del mazzo di carte
        this.ordinapernumeroeseme(); //ordina per seme e numero il mazzo di carte

    }

    /**
     * Restituisce il numero delle carte del mazzo di carte
     *
     * @return il numero delle carte rimanenti nel mazzo di carte
     */
    public int carterimanenti() {
        return mazzo.size();
    }

    /**
     * Restituisce la carta indicata dal mazzo di carte
     *
     * @param x numero della posizione della carta da estrarre
     * @return la carta indicata
     */
    public Carta getmazzoindex(int x) {
        return mazzo.get(x);
    }

    /**
     * Somma i valori dei punteggi delle carte del mazzo di carte
     *
     * @return il punteggio delle carte del mazzo di carte
     */
    public int sommavaloripunteggimazzo() {
        int a = 0;
        float valorepunteggiocarta = 0;
        float somma = 0;
        int numerocarte = this.getmazzo().size(); //numero delle carte nel mazzo di carte
        for (int i = 0; i < numerocarte; i++) {

            valorepunteggiocarta = this.getmazzo().get(i).getvalorepunteggio();  //punteggio della carta 
            somma = somma + valorepunteggiocarta;

        }
        a = (int) somma; //casting da float in int
        return a; //punteggio finale delle carte del mazzo

    }

    /**
     * Rimuove e restituisce la carta dalla posizione indicata nel mazzo di
     * carte
     *
     * @param x numero che rappresenta la posizione della carta da rimuovere
     * @return la carta rimossa dalla posizione indicata nel mazzo di carte
     */
    public Carta remove(int x) {
        return mazzo.remove(x);
    }

    /**
     * Confronta le prime 2 carte di un mazzo di carte per seme e valore di
     * gioco e restituisce la carta vincente(secondo le regole del tresette)
     *
     * @return la carta vincente
     */
    public Carta confrontacartepervaloregioco() {
        Carta a = this.getmazzo().get(0);  //Copia in a la carta in posizione 0 del mazzo di carte
        Carta b = this.getmazzo().get(1);  //Copia in b la carta in posizione 1 del mazzo di carte

        if (a.stessoseme(b)) {   //se a e b hanno lo stesso seme
            if (a.getvaloregioco() > b.getvaloregioco()) { //se il valore di gioco di a è maggiore del valore di gioco di b
                return a;  //la carta a vince sulla carta b
            } else { //il valore di gioco di b è maggiore del valore di gioco di a
                return b; //la carta b vince sulla carta a
            }
        } else { //se a e b non hanno lo stesso seme
            return a; //la carta a vince sulla carta b poichè è in posizione 0
        }
    }
}
