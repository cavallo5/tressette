/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtressette;

import java.util.Scanner;

/**
 *
 * @author Vincenzo Cavallo
 * @author Enrico Mauceri
 */
public class Partita {

    private GiocatoreUmano giocatore1; //giocatoreumano
    private GiocatoreUmano giocatore2; //giocatoreumano
    private GiocatoreElettronico giocatore3; //giocatoreelettronico
    private MazzodiCarte mazzocentrale;   //mazzo di 40 carte napoletane
    private MazzodiCarte cartavisibile;   //mazzo vuoto che conterrà {0,1,2} carte
    private int finepartita = 0; //punti necessari per finire la partita
    private int tipo; //tipo di partita 1 se 1vs1 oppure 2 se 1vsCPU

    /**
     * Genera una partita
     */
    public Partita() {           //costruttore

        System.out.println("GIOCO TRESETTE");
        this.mazzocentrale = new MazzodiCarte();   //mazzocentrale è un mazzo di carte vuoto
        this.mazzocentrale.mazzodi40carte();     //metto 40 carte nel mazzocentrale
        this.mazzocentrale.mischia(); //mischio il mazzocentrale
        this.cartavisibile = new MazzodiCarte();   //cartavisibile è un mazzo di carte vuoto
        this.tipo = sceltaavversario();  //scelta del tipo di partita
    }

    /**
     * Sceglie il tipo di partita
     *
     * @return 1 se GiocatoreUmanovsGiocatoreUmano 2 se
     * GiocatoreUmanovsGiocatoreElettronico
     */
    private int sceltaavversario() {
        System.out.print("1-2?    dove 1=(1vs1) o 2=(1vsCPU) ");
        Scanner in = new Scanner(System.in);
        String Sceltatipopartita = in.next();
        boolean x = false;
        int y = 0;
        do {

            if ("1".equals(Sceltatipopartita)) { //GiocatoreUmanovsGiocatoreUmano
                x = true;
                y = 1;
            }
            if ("2".equals(Sceltatipopartita)) { //GiocatoreUmanovsGiocatoreElettronico
                x = true;
                y = 2;
            }

            if (x == false) { //l'utente non ha scelto il tipo di partita
                System.out.println("Puoi scegliere 1 o 2 dove 1=(1vs1) o 2=(1vsCPU) ");
                Sceltatipopartita = in.next();
            }

        } while (x == false); //finchè non sceglie il tipo della partita
        return y; //il tipo di partita

    }

    /**
     * Vengono creati i giocatori in base alla scelta del tipo della partita e si da inizo al gioco
     */
    public void avanzamentosceltaegioco() {
        if (this.gettipo() == 1) { //GiocatoreUmano vs GiocatoreUmano
            this.giocatore1 = new GiocatoreUmano(); //giocatore1 è un giocatoreumano
            this.giocatore2 = new GiocatoreUmano(); //giocatore1 è un giocatoreumano
            this.getgiocatore1().settrueturnogiocatore(); //rende true il turno del giocatore 1 
            this.getgiocatore2().settrueturnogiocatore(); //rende false il turno del giocatore 2
            this.gestionepartita(giocatore1, giocatore2); //inizia la partita
        }
        if (this.gettipo() == 2) //GiocatoreUmano vs GiocatoreElettronico
        {
            this.giocatore1 = new GiocatoreUmano(); //giocatore1 è un giocatoreumano
            this.giocatore3 = new GiocatoreElettronico(); //giocatore3 è un giocatoreelettronico
            this.getgiocatore1().settrueturnogiocatore();   //rende true il turno del giocatore 1 
            this.getgiocatore3().settrueturnogiocatore();  //rende false il turno del giocatore 2 
            this.gestionepartita(giocatore1, giocatore3); //inizia la partita
        }
    }

    //metodi get
    /**
     * Restituisce il giocatore1
     *
     * @return il GiocatoreUmano 1
     */
    public GiocatoreUmano getgiocatore1() {
        return giocatore1;
    }

    /**
     * Restituisce il giocatore2
     *
     * @return il GiocatoreUmano 2
     */
    public GiocatoreUmano getgiocatore2() {
        return giocatore2;
    }

    /**
     * Restituisce il giocatore3
     *
     * @return il GiocatoreUmano 3
     */
    public GiocatoreElettronico getgiocatore3() {
        return giocatore3;
    }

    /**
     * Restituisce il mazzocentrale
     *
     * @return il MazzodiCarte mazzocentrale
     */
    public MazzodiCarte getmazzocentrale() {    //restituisce il mazzocentrale
        return new MazzodiCarte(mazzocentrale);
    }

    /**
     * Restituisce la cartavisibile
     *
     * @return il MazzodiCarte cartavisibile
     */
    public MazzodiCarte getcartavisibile() {    //resituisce il mazzodellecartegiocate ogni turno
        return new MazzodiCarte(cartavisibile);
    }

    /**
     * Restituisce il numero di finepartita per terminare la partita
     *
     * @return il valore di finepartita
     */
    public int getfinepartita() {
        return finepartita;
    }

    /**
     * Restituisce il tipo di partita
     *
     * @return 1 se 1vs1 oppure 2 se 1vsCPU
     */
    public int gettipo() {
        return tipo;

    }

    //metodi set
    /**
     * Modifica il giocatore1
     *
     * @param a un GiocatoreUmano
     */
    private void setgiocatore1(GiocatoreUmano a) {       //Il giocatore a diventa il nuovo giocatore1
        giocatore1 = a;
    }

    /**
     * Modifica il giocatore2
     *
     * @param a un GiocatoreUmano
     */
    private void setgiocatore2(GiocatoreUmano a) {      //Il giocatore a diventa il nuovo giocatore2
        giocatore2 = a;
    }

    /**
     * Modifica il giocatore2
     *
     * @param a un GiocatoreElettronico
     */
    private void setgiocatore3(GiocatoreElettronico a) {      //Il giocatore a diventa il nuovo giocatore2
        giocatore3 = a;
    }

    /**
     * Modifica il mazzocentrale
     *
     * @param a MazzodiCarte
     */
    private void setmazzocentrale(MazzodiCarte a) {     //Il mazzo di carte a diventa il mazzocentrale del tavolo
        mazzocentrale = new MazzodiCarte(a);
    }

    /**
     * Modifica la cartavisibile
     *
     * @param a MazzodiCarte
     */
    private void setcartavisibile(MazzodiCarte a) {    //Il mazzo di carte a diventa il mazzo delle cartevisibili
        cartavisibile = new MazzodiCarte(a);
    }

    /**
     * Modifica il valore di finepartita
     *
     * @param a i punti per finire la partita
     */
    private void setfinepartita(int a) {
        finepartita = a;
    }

    /**
     * Distrubuisce le carte ai giocatori della partita al primo turno
     *
     * @param a Giocatore
     * @param b Giocatore
     */
    public void distribuiscicarteaigiocatorialprimoturno(Giocatore a, Giocatore b) { //da 10 carte ad entrambi i giocatori 
        for (int i = 0; i < 10; i++) {
            Carta c = mazzocentrale.daicarta();              //Prende una carta dal mazzocentrale
            a.getmazzoinmano().aggiungicartaeordina(c);    //La carta pescata la mette nel mazzo in mano del giocatore a
            Carta d = mazzocentrale.daicarta();             //Prende la carta successiva dal mazzocentrale 
            b.getmazzoinmano().aggiungicartaeordina(d);   //La carta pescata la metto nel mazzo in mano del giocatore b
        }

    }

    /**
     * Distribuisce le carte ai giocatori della partita nei turni successivi al
     * primo
     *
     * @param a Giocatore
     * @param b Giocatore
     */
    public void distrubuiscicarteaigiocatoriaiturnisuccessivi(Giocatore a, Giocatore b) {
        if (a.getturno() == true) { //se il turno del giocatore a è true
            Carta c = mazzocentrale.daicarta();              //Prende una carta dal mazzocentrale
            a.getmazzoinmano().aggiungicartaeordina(c);    //La carta pescata la metto nel mazzo in mano del giocatore 1 e lo ordina
            System.out.println("" + a.getnomegiocatore() + " ha pescato la carta " + c);
            Carta d = mazzocentrale.daicarta();             //Prende una carta dal mazzocentrale
            b.getmazzoinmano().aggiungicartaeordina(d);     //La carta pescata la metto nel mazzo in mano del giocatore 2 e lo ordina
            System.out.println("" + b.getnomegiocatore() + " ha pescato la carta " + d);

        } else { //se il turno del giocatore a non è true (di conseguenza sarà true il turno del giocatore b)
            Carta c = mazzocentrale.daicarta();              //Prende una carta dal mazzocentrale
            b.getmazzoinmano().aggiungicartaeordina(c);    //La carta pescata la metto nel mazzo in mano del giocatore 2 e lo ordina
            System.out.println("" + b.getnomegiocatore() + " ha pescato la carta " + c);
            Carta d = mazzocentrale.daicarta();             //Prende una carta dal mazzocentrale
            a.getmazzoinmano().aggiungicartaeordina(d);     //La carta pescata la metto nel mazzo in mano del giocatore 1 e lo ordina
            System.out.println("" + a.getnomegiocatore() + " ha pescato la carta " + d);

        }

    }

    /**
     * Ordina i mazziinmano dei 2 giocatori durante il turno e li stampa
     *
     * @param a Giocatore
     * @param b Giocatore
     */
    public void stampamazziturnoconordinapersemeevalore(Giocatore a, Giocatore b) {
        a.getmazzoinmano().ordinapernumeroeseme(); //ordina il mazzoinmano del giocatore a
        b.getmazzoinmano().ordinapernumeroeseme(); //ordina il mazzoinmano del giocatore b
        System.out.println("" + a + "");
        System.out.println("" + b + "");
    }

    /**
     * Ordina i mazziinmano del giocatore durante il turno e lo stampa
     *
     * @param a Giocatore
     */
    public void stampamazziturnoconordinapersemeevalore(Giocatore a) {
        a.getmazzoinmano().ordinapernumeroeseme(); //ordina il mazzoinmano del giocatore a
        System.out.println("" + a + "");
    }

    /**
     * Stampa le regole del gioco Tresette
     */
    private void stamparegole() {
        System.out.println(
                "Vuoi una descrizione del gioco e vuoi leggere le regole del gioco? SI/PREMI QUALSIASI ALTRO TASTO ");
        Scanner in = new Scanner(System.in);
        String regole = in.next();

        if ("SI".equals(regole)) { //se l'utente sceglie di leggere le regole del gioco
            System.out.println("Questo gioco è progettato per una partita a 2 giocatori (1 vs 1 oppure 1 vs cpu)\n"
                    + "La distribuzione delle carte porta 10 carte a testa lasciando il mazzo al centro del tavolo (ovviamente coperto). Colui che vince la singola mano pesca la carta in cima al mazzo. \n"
                    + "Nel tresette a due quando si prende una carta dal mazzo la si mostra all'avversario prima di inserirla tra le proprie carte e lo stesso farà l'altro giocatore quando prenderà la sua carta.\n"
                    + "Valore in punti delle carte\n"
                    + "\n"
                    + "Per la definizione del vincitore della mano, le carte non posseggono un valore numerico, ma solo un valore ordinale (il tre ad esempio vince sul re che nel mazzo italiano ha valore dieci), determinato dalla posizione all'interno della seguente scala (ordinata per valore decrescente):\n"
                    + "Tre\n"
                    + "Due\n"
                    + "Asso\n"
                    + "Re (Dieci)\n"
                    + "Cavallo (Nove)\n"
                    + "Donna (Otto)\n"
                    + "Sette\n"
                    + "Sei\n"
                    + "Cinque\n"
                    + "Quattro\n"
                    + "Ciascuna carta vince su tutte quelle di valore ordinale inferiore. \n"
                    + "Per il fatto che la presa è solo per le carte appartenenti al seme della prima carta giocata (palo) e che non esistono due carte di medesimo valore in uno stesso seme, si deduce che una mano non potrà mai concludersi in condizione di parità.\n"
                    + "Come si calcola il punteggio:\n"
                    + "\n"
                    + "Per il calcolo del punteggio di ciascun giocatore alla fine di una mano, a ogni carta viene attribuito un valore numerico secondo il seguente schema: \n"
                    + "\n"
                    + "L'asso equivale 1 punto. \n"
                    + "Le figure (fante, cavallo e re) e gli altri \"carichi\" (due e tre) valgono 1/3 di punto; Le scartine (quattro, cinque, sei e sette) non hanno valore. "
                    + "\n"
                    + "Come si può facilmente intuire, la differenza di punteggio tra le carte non ricalca strettamente la scala dei valori che aggiudicano la presa; questo permette di avere un gioco concettualmente più interessante dal punto di vista della strategia e della tattica, in quanto la carta più \"potente\" al fine della presa (il tre) non è quella che rende il punteggio maggiore (che nel tresette invece è l'asso). \n"
                    + "\n"
                    + "È facile calcolare che in un mazzo di carte ci sono esattamente 10 punti e 2/3. \n"
                    + "\n"
                    + "Per evitare di concludere il round con punteggi non interi, si eliminano dal totale le frazioni di punto e si assegna un punto aggiuntivo alla squadra che ha effettuato la presa della decima e ultima mano (denominata in alcune varianti \"rete\"). Così facendo, alla fine del round saranno distrubuiti 11 punti. \n"
                    + "\n"
                    + "Non rarissimo è cosiddetto il \"cappotto\", ossia quando tutti gli 11 punti di un round vengono realizzati da una sola delle due squadre. Questo non presuppone necessariamente che la squadra che \"fa cappotto\", cioè quella che vince, "
                    + "\n"
                    + "abbia preso tutte le mani; è possibile infatti che il team che subisce il cappotto abbia effettuato una o più prese composte solamente da scartine o da un massimo di due figure senza conquistare il punto della presa dell'ultima passata. Si ritiene cappotto anche il caso in cui la squadra che lo subisce abbia in carico punti extra legati all'accusato di napoli o di bongiochi (qui di seguito descritti).\n"
                    + "L'accuso (o accusa) del bongioco e della napoli:\n"
                    + "\n"
                    + "Nel gioco del tressette non è assolutamente permesso comunicare le proprie carte, neanche mediante segni. Si racconta infatti che il gioco del tressette fu inventato da due muti. Nel gioco a quattro, queste comunicazioni rispettano delle regole molto precise, "
                    + "\n"
                    + "nel senso che possono essere usate delle parole che sono standard e da tutti riconosciute: mentre si comunica al proprio compagno come giocare la mano, di fatto usando il gergo standard del tresette da informazioni anche agli avversari. \n"
                    + "\n"
                    + "Il tressette con \"accusa\" (o accuso) prevede la possibilità di vincere dei punti extra a fine round rispetto agli 11 del mazzo. Questa situazione si verifica nel caso in cui un giocatore tiene in mano una particolare combinazione di carte. In particolare: \n"
                    + "\n"
                    + "La \"napoli\" (o \"napoletana\") è la combinazione di asso, due e tre dello stesso palo (seme), e vale tre punti in più per la squadra che la detiene. "
                    + "\n"
                    + "Il \"bongioco\" (o \"buon gioco\") è un tris di assi, di due o di tre. La quarta carta mancante viene chiamata \"fagliante\". Questa combinazione vale tre punti; nel caso di quattro carte uguali il bongioco diventa superbongioco e vale quattro punti e, in alcune varianti regionali, si chiama \"stella\". \n"
                    + "\n"
                    + "L' accusa è multipla, nel senso che si può dichiarare contemporaneamente più bongiochi o napoli insieme. In questo caso i punti vinti sono pari alla somma dei punti delle singole accuse più i punti vinti durante il gioco.");
            System.out.println("\n\n\n\n");
        } else { //se l'utente sceglie di non leggere le regole del gioco
            System.out.println("Bene, procediamo");
            System.out.println("\n\n");
        }
    }

    /**
     * Si sceglie la modalità di partita tra singolapartita, partita a 11 pt,
     * partita a 21 pt, partita a 31pt
     */
    public void modalitapartita() {
        System.out.println("Scegli tra 1(singola partita), 2(chi arriva prima a 11 pt), 3(fino a 21 pt), 4(fino a 31 pt)");
        Scanner in = new Scanner(System.in);
        String puntifinepartite = in.next();
        boolean x = false;
        do {
            if ("1".equals(puntifinepartite)) { //l'utente sceglie di giocare una singola partita

                x = true;
            }
            if ("2".equals(puntifinepartite)) { //l'utente sceglie di giocare una partita a 11pt
                this.setfinepartita(11);
                x = true;
            }
            if ("3".equals(puntifinepartite)) { //l'utente sceglie di giocare una partita a 21pt
                this.setfinepartita(21);
                x = true;
            }
            if ("4".equals(puntifinepartite)) { //l'utente sceglie di giocare una partita a 31pt
                this.setfinepartita(31);
                x = true;
            }
            if (x == false) { //se non inserisce la modalità partita
                System.out.println("Errore, puoi scegliere tra 1,2,3,4 dove:");
                System.out.println("1=partita singola");
                System.out.println("2=chi arriva prima a 11 punti vince");
                System.out.println("3=chi arriva prima a 21 punti vince");
                System.out.println("4=chi arriva prima a 31 punti vince");
                puntifinepartite = in.next();
            }
        } while (x == false); //finchè non sceglie la modalità partita
    }

    /**
     * Confronta le carte giocate e restituisce la carta che vince
     *
     * @param a la carta giocata dal giocatore che gioca per primo
     * @param b la carta giocata dal giocatore che gioca per secondo
     * @return la carta vincente
     */
    private Carta vinceturno(Carta a, Carta b) {
        this.getcartavisibile().getmazzo().clear(); //rimuove gli elementi nel mazzo di carte cartavisibile
        this.getcartavisibile().getmazzo().add(a); //aggiunge la carta a nel mazzo cartavisibile
        this.getcartavisibile().getmazzo().add(b); //aggiunge la carta b nel mazzo cartavisibile
        Carta vincente = this.getcartavisibile().confrontacartepervaloregioco(); //vincente è la carta che vince tra a e b secondo le regole del gioco Tresette
        return vincente;

    }

    /**
     * Calcola il punteggio del giocatore a
     *
     * @param a Giocatore
     *
     */
    public void calcolopunteggi(Giocatore a) {
        int punteggiogiocatorea = a.getmazzopunteggi().sommavaloripunteggimazzo(); //punteggio del giocatore a
        a.setpunteggio(punteggiogiocatorea); //modifica il punteggio del giocatore a
        a.sommabonusfinale(); //controlla se a avrà il punto bonus
        a.stampapunteggifinepartita(); //stampa il punteggio finale del giocatore a
    }

    /**
     * Gestisce i turni dei 2 giocatori della partita
     *
     * @param x Giocatore
     * @param y Giocatore
     * @param v livello di difficoltà della CPU (utilizzato solo se si gioca
     * contro un GiocatoreElettronico)
     */
    public void gestioneturni(Giocatore x, Giocatore y, int v) {
        Carta a;
        Carta b;
        if (x.getturno()) { //se il turno del giocatore x è true
            if (x != this.getgiocatore3()) { //se il giocatore x non è un giocatore elettronico
                stampamazziturnoconordinapersemeevalore(x); //stampa il mazzo del giocatore x(fatto solo se x è un giocatore umano
            }
            a = x.tira(v); //in a viene salvata la carta tirata dal giocatore x
            System.out.println("Tocca al giocatore " + y.getnomegiocatore() + "");
            if (y != this.getgiocatore3()) { //se il giocaotre y non è un giocatore elettronico
                this.stop(); //ferma il turno
                stampamazziturnoconordinapersemeevalore(y); //stampa il mazzo del giocatore y(fatto solo se y è un giocatore umano
                System.out.println("Il giocatore " + x.getnomegiocatore() + " ha tirato la carta " + a + ""); //viene mostrata la carta giocata dal giocatore x
            }
            b = y.rispondi(a, v); //in b viene salvata la carta tirata dal giocatore y
            System.out.println("Il giocatore " + y.getnomegiocatore() + " risponde con la carta " + b + "");
            for (int i = 0; i < 50; i++) { //ciclo di 50 caratteri
                System.out.println();
            }
            Carta vincente = vinceturno(a, b); //vincente è la carta che vince tra le 2 carte giocate
            if (vincente == a) {  //ha vinto la carta giocata da chi ha tirato per primo (giocatore x)
                System.out.println("Il giocatore " + x.getnomegiocatore() + " vince la mano con la carta " + vincente + " contro la carta " + b + "");
                x.settrueturnogiocatore(); //il turno di x diventa true
                y.setfalseturnogiocatore(); //il turno di y diventa false
                x.getmazzopunteggi().aggiungicartaeordina(a); //la carta a viene aggiunta al mazzodeipunteggi del giocatore x
                x.getmazzopunteggi().aggiungicartaeordina(b);  //la carta b viene aggiunta al mazzodeipunteggi del giocatore x

            } else { //ha vinto la carta giocata da chi ha tirato per secondo (giocatore y)

                System.out.println("Il giocatore " + y.getnomegiocatore() + " vince la mano con la carta " + vincente + " contro la carta " + a + "");
                y.settrueturnogiocatore(); //il turno di y diventa true
                x.setfalseturnogiocatore(); //il turno di x diventa false
                y.getmazzopunteggi().aggiungicartaeordina(a); //la carta a viene aggiunta al mazzodeipunteggi del giocatore y
                y.getmazzopunteggi().aggiungicartaeordina(b); //la carta b viene aggiunta al mazzodeipunteggi del giocatore y
            }
        } else { //se il turno del giocatore x è false (di conseguenza il turno del giocatore y è true)
            if (y != this.getgiocatore3()) { //se y non è un giocatoreelettronico
                stampamazziturnoconordinapersemeevalore(y); //stampa il mazzo del giocatore y(fatto solo se y è un giocatore umano
            }
            a = y.tira(v); //in a viene salvata la carta tirata dal giocatore y
            System.out.println("Tocca al giocatore " + x.getnomegiocatore() + "");
            if (x != this.getgiocatore3()) { //se x non è un giocatoreelettronico
                this.stop();  //ferma il turno
                stampamazziturnoconordinapersemeevalore(x); //stampa il mazzo del giocatore x(fatto solo se x è un giocatore umano
                System.out.println("Il giocatore " + y.getnomegiocatore() + " ha tirato la carta " + a + "");
            }
            b = x.rispondi(a, v); //in b viene salvata la carta tirata dal giocatore x
            System.out.println("Il giocatore " + x.getnomegiocatore() + " risponde con la carta " + b + "");
            for (int i = 0; i < 50; i++) { //ciclo di 50 caratteri
                System.out.println();
            }
            Carta vincente = vinceturno(a, b); //vincente è la carta che vince tra le 2 carte giocate
            if (vincente == a) {  //ha vinto la carta giocata da chi ha tirato per primo
                System.out.println("Il giocatore " + y.getnomegiocatore() + " vince la mano con la carta " + vincente + " contro la carta " + b + "");
                y.settrueturnogiocatore();
                x.setfalseturnogiocatore();
                y.getmazzopunteggi().aggiungicartaeordina(a);
                y.getmazzopunteggi().aggiungicartaeordina(b);

            } else {

                System.out.println("Il giocatore " + x.getnomegiocatore() + " vince la mano con la carta " + vincente + " contro la carta " + a + "");
                x.settrueturnogiocatore();
                y.setfalseturnogiocatore();
                x.getmazzopunteggi().aggiungicartaeordina(a);
                x.getmazzopunteggi().aggiungicartaeordina(b);
            }
        }

    }

    /**
     * Ferma la partita per la stampa dei mazzi attendendo l'imput del giocatore
     */
    private void stop() {
        System.out.println("Premere un tasto e il tasto invia per continuare ");
        Scanner in = new Scanner(System.in);
        String cheats = in.next(); //trucco
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        this.cheats(cheats);

    }

    /**
     * Gestisce l'intera partita
     *
     * @param a Giocatore
     * @param b Giocatore
     */
    public void gestionepartita(Giocatore a, Giocatore b) {

        
        boolean fine = false;
        int v = 0;
        this.stamparegole(); //chiede se stampare le regole del gioco
        this.modalitapartita(); //scelta della modalità partita
        int z = this.getfinepartita(); //in z viene salvato il punteggio per finire la partita
        if (this.gettipo() == 2) { //se si sceglie una partita con il GiocatoreElettronico
            v = this.getgiocatore3().sceltalivellodidifficoltà(); //scelta del livello di difficoltà del GiocatoreElettronico
        }
        do {
            distribuiscicarteaigiocatorialprimoturno(a, b); //Distribuisce le carte ai 2 giocatori al primo turno
            a.controllaaccusifinale(); //controlla gli accusi del giocatore a
            b.controllaaccusifinale(); //controlla gli accusi del giocatore b

            System.out.println("Inizia la partita");
            this.stop(); //stop della partita
            int n = this.getmazzocentrale().getmazzo().size();
            for (int i = 0; i < (n / 2); i++) { //Le 20 carte rimaste nel mazzocentrale vengono distribuite ai 2 giocatori in 10 turni
                System.out.println("TURNO " + (i + 1) + "");
                this.gestioneturni(a, b, v); //gestione dei turni dei giocatori 
                this.distrubuiscicarteaigiocatoriaiturnisuccessivi(a, b); //distribuisce una carta ai 2 giocatori ogni fine turno
                if (b != this.getgiocatore3()) { //se b non è un giocatore elettronico
                    this.stop(); //stop della partita
                }
            }
            int m = a.getmazzoinmano().getmazzo().size();
            for (int j = 0; j < m; j++) {
                a.getmazzoinmano().ordinapernumeroeseme(); //ordina per seme e numero il mazzoinmano del giocatore a
                b.getmazzoinmano().ordinapernumeroeseme(); //ordina per seme e numero il mazzoinmano del giocatore b

                System.out.println("TURNO " + (j + 11) + "");
                this.gestioneturni(a, b, v); //gestione dei turni dei giocatori 
                if (b != this.getgiocatore3()) { //se b non è un giocatore elettronico
                    this.stop(); //stop della partita
                }
            }
            this.calcolopunteggi(a); //calcolo del punteggio del giocatore a
            this.calcolopunteggi(b); //calcolo del punteggio del giocatore b

            int c = (int) a.getpunteggio(); //cast in int del punteggio del giocatore a
            int d = (int) b.getpunteggio(); //cast in int del punteggio del giocatore b

            if ((c >= z) || (d >= z)) { //se uno dei 2 giocatori supera il punteggio di fine partita
                if (c > d) { //se il punteggio del giocatore a è maggiore del punteggio del giocatore b
                    System.out.println(" Complimenti il giocatore " + a.nomegiocatore + " ha vinto la partita");
                } else {
                    if (c < d) { //se il punteggio del giocatore a è minore del punteggio del giocatore b
                        System.out.println(" Complimenti il  giocatore " + b.nomegiocatore + "   ha vinto la partita");
                    } else {
                        System.out.println(" La partita è patta ");
                    }
                }
                fine = true;
            }
            if (!fine) { //se uno dei giocatori non ha ancora vinto la partita
                this.mazzocentrale = new MazzodiCarte();   //Creo un nuovo mazzo mazzocentrale
                this.mazzocentrale.mazzodi40carte();     //Mette 40 carte nel mazzocentrale
                this.mazzocentrale.mischia(); //Mischia il mazzocentrale
                this.cartavisibile = new MazzodiCarte();   //Crea mazzo vuoto
                a.settrueturnogiocatore();   //rende true il turno del giocatore 1
                b.settrueturnogiocatore();   //rende false il turno del giocatore 2
            }
        } while (fine == false); //finchè la partita non è terminata 
    }

    
    
    
    /**
     * Stampa i mazzi di entrambi i giocatori se viene scritto sonoforte, stampa
     * il mazzocentrale se viene scritto future
     *
     * @param a l'input durante lo stop della partita
     */
    private void cheats(String a) {
        if ("sonoforte".equals(a)) {   //se il carattere è uguale al cheats
            System.out.println("Trucco attivato. Questi sono i mazzi di entrambi i giocatori");
            if (this.getgiocatore3() == null) {
                this.stampamazziturnoconordinapersemeevalore(this.giocatore1, this.giocatore2); //stampa i mazzi dei 2 giocatori
            } else {
                this.stampamazziturnoconordinapersemeevalore(this.giocatore1, this.giocatore3); //stampa i mazzi dei 2 giocatori
            }
            System.out.println("---------------------------------------");
        }
        if ("future".equals(a)) {
            System.out.println("Trucco attivato. Questo è il mazzo di carte al centro");
            System.out.println(this.getmazzocentrale());
            System.out.println("--------------------------------------");
        }
    }
}
