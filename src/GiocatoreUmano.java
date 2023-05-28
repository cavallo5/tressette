package src;

import java.util.Scanner;

/**
 *
 * @author Vincenzo Cavallo
 * @author Enrico Mauceri
 */
public class GiocatoreUmano extends Giocatore {

    /**
     * Restituisce e rimuove la carta indicata dal mazzo di carte mazzoinmano
     *
     * @return la carta scelta
     */
    private Carta getcartamano() {      //indicare la posizione della carta da scegliere e la restituisce e la rimuove
        int a = 0;
        int b = this.getmazzoinmano().carterimanenti(); //numero di carte in mano
        boolean x = false;
        Scanner num = new Scanner(System.in);
        System.out.println("Inserire numero posizione carta da giocare ");
        String posizioneCarta = num.next(); //posizione della carta da giocare
        do {
            if ("0".equals(posizioneCarta)) {   //posizione 0
                x = true;
            }
            if ("1".equals(posizioneCarta)) {  //posizione 1
                x = true;
            }
            if ("2".equals(posizioneCarta)) {  //posizione 2
                x = true;
            }
            if ("3".equals(posizioneCarta)) {  //posizione 3
                x = true;
            }
            if ("4".equals(posizioneCarta)) {  //posizione 4
                x = true;
            }
            if ("5".equals(posizioneCarta)) {  //posizione 5
                x = true;
            }
            if ("6".equals(posizioneCarta)) {  //posizione 6
                x = true;
            }
            if ("7".equals(posizioneCarta)) {  //posizione 7
                x = true;
            }
            if ("8".equals(posizioneCarta)) {  //posizione 8
                x = true;
            }
            if ("9".equals(posizioneCarta)) {  //posizione 9
                x = true;
            }
            if (x == true) {  //l'utente ha scelto una carta tra le posizioni
                a = Integer.parseInt(posizioneCarta);  //trasforma il carattere posizioneCarta in intero
                if (a >= b) { //serve per i turni finali in cui le carte in mano diminuiscono
                    x = false; //l'utente ha scelto una carta nel mazzoinmano dove non ci sono più carte
                }
            }
            if (x == false) { //l'utente ha scelto una posizione della carta fuori dal mazzoinmano
                System.out.println("------------------------------------------------- ");
                System.out.println("ERRORE: ");
                System.out.println("Non esiste la posizione " + posizioneCarta + " indicata");
                System.out.println("------------------------------------------------- ");
                System.out.println("Inserire nuovo numero posizione carta da giocare ");
                posizioneCarta = num.next(); //richiede una nuova posizione della carta da giocare
            }
        } while (x == false); //finchè non viene scelta una carta giusta
        Carta estratta = mazzoinmano.remove(a); //estrae la carta dalla posizione a dal mazzoinmano
        return estratta; //ritorna la carta scelta
    }

    /**
     * Il giocatoreumano inizia il turno tirando una carta
     *
     * @param v livello di difficoltà del giocatoreumano (è 0 per il giocatore
     * umano poichè sceglie l'utente stesso)
     * @return la carta tirata dal giocatoreumano che inizia il turno
     */
    @Override
    public Carta tira(int v) { //viene chiamato dal giocatore che inizia il turno
        System.out.println("Tocca al giocatore " + this.getnomegiocatore() + "");
        Carta e = this.getcartamano();   //Carta giocata dal giocatore che inizia il turno e viene rimossa dalla sua mano
        this.getmazzoinmano().ordinapernumeroeseme();  //riordina le 9 carte in mano del giocatoreumano che ha giocato
        System.out.println("" + this.getnomegiocatore() + " ha buttato la carta " + e + ""); //stampa della carta giocata
        return e; //la carta giocata dal giocatoreumano
    }

    /**
     * Il giocatoreumano risponde con una carta al giocatoreumano che ha
     * iniziato il turno
     *
     * @param a carta giocata dal giocatoreumano che ha iniziato il turno
     * @param v livello di difficoltà del giocatoreumano (è 0 per il giocatore
     * umano poichè sceglie l'utente stesso)
     * @return la carta tirata dal giocatoreumano che risponde
     */
    @Override
    public Carta rispondi(Carta a, int v) //a è la carta tirata dal giocatore umano che ha giocato per primo
    {
        boolean x = false;
        Carta dagiocare = null;
        for (int i = 0; (i < this.getmazzoinmano().getmazzo().size()) && (!x); i++) {  //controllo se ho almeno una carta con lo stesso seme
            Carta verifica = this.getmazzoinmano().getmazzoindex(i); //prende la carta verifica dalla posizione i
            if (verifica.stessoseme(a)) {
                x = true;  //se il giocatoreumano ha in mano una carta dello stesso seme di a
            }
        }
        if (x) {   //il giocatoreumano ha una carta dello stesso seme di a
            System.out.println("Tocca al giocatore " + this.getnomegiocatore() + "");
            Carta g = this.getcartamano(); //il giocatoreumano butta la carta
            this.getmazzoinmano().ordinapernumeroeseme(); //riordino il suo mazzoinmano di 9 carte
            if (g.stessoseme(a)) { //se ha risposto con lo stesso seme
                dagiocare = g; //il giocatoreumano risponderà con la carta g
            } else { //non ha risposto con lo stesso seme
                do {
                    this.getmazzoinmano().aggiungicartaeordina(g); //rimetto la carta g nel suo mazzoinmano e lo riordino
                    System.out.println("" + this.getnomegiocatore() + " Errore: devi rispondere con lo stesso seme");
                    Carta q = this.getcartamano(); //il giocatoreumano butta la carta
                    g = q;
                } while (!g.stessoseme(a));
                dagiocare = g; //il giocatoreimano risponderà con la carta g
            }
        } else { //il giocatoreumano non ha una carta dello stesso seme di a 
            Carta f = this.getcartamano(); //il giocatoreumano butta la carta
            this.getmazzoinmano().ordinapernumeroeseme(); //riordino il suo mazzoinmano di 9 carte
            dagiocare = f; //il giocatoreimano risponderà con la carta f
        }
        return dagiocare; //la carta giocata dal giocatoreumano che risponde
    }
}
