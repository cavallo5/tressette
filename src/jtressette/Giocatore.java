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
public abstract class Giocatore {

    protected String nomegiocatore; //stringa del nome del giocatore
    protected MazzodiCarte mazzoinmano; //il mazzo di carte in mano del giocatore
    protected MazzodiCarte mazzopunteggi; //il mazzo di carte dei punteggi del giocatore
    protected boolean turno; //turno del giocatore
    protected float punteggio = 0; //punteggio del giocatore

    /**
     * Genera un giocatore e inizializza i suoi attributi
     */
    public Giocatore() {      //Costruttore
        Scanner in = new Scanner(System.in);
        System.out.print(" Immetti nome giocatore: ");
        String nome = in.next();
        this.nomegiocatore = nome;
        this.mazzoinmano = new MazzodiCarte();  //mazzoinmano è un mazzo di carte vuoto
        this.mazzopunteggi = new MazzodiCarte();   //mazzopunnteggi è un mazzo di carte vuoto
        this.turno = true; //il turno è inizializzato a true

    }


    //metodi set
    /**
     * Modifica il nome del giocaotre
     *
     * @param a nome del giocatore
     */
    public void setnomegiocatore(String a) {

        this.nomegiocatore = a;
    }

    /**
     * Rende true il turno del giocatore
     */
    public void settrueturnogiocatore() {
        this.turno = true;
    }

    /**
     * Rende false il turno del giocatore
     */
    public void setfalseturnogiocatore() {
        this.turno = false;
    }

    /**
     * Modifica il punteggio del giocatore
     *
     * @param a punteggio del giocatore
     */
    public void setpunteggio(float a) {
        this.punteggio = a;
    }

    //metodi get
    /**
     * Restituisce il nome del giocatore
     *
     * @return il nome del giocatore
     */
    public String getnomegiocatore() {
        return nomegiocatore;
    }

    /**
     * Restituisce una copia del MazzodiCarte mazzoinmano
     *
     * @return il mazzo di carte mazzoinmano
     */
    public MazzodiCarte getmazzoinmano() {
        return new MazzodiCarte(mazzoinmano);
    }

    /**
     * Restituisce il MazzodiCarte mazzopunteggi
     *
     * @return il mazzo di carte mazzopunteggi
     */
    public MazzodiCarte getmazzopunteggi() {
        return new MazzodiCarte(mazzopunteggi);
    }

    /**
     * Restituisce il punteggio del giocatore
     *
     * @return il punteggio del giocatore
     */
    public float getpunteggio() {
        return punteggio;
    }

    /**
     * Restituisce il boolean del turno
     *
     * @return true se turno è vero, false se turno è falso
     */
    public boolean getturno() {
        return turno;
    }

    /**
     * Stampa il giocatore con nomegiocatore e il mazzoinmano
     *
     * @return stringa con nomegiocatore e mazzoinmano
     */
    @Override
    public String toString() {
        return "il giocatore " + nomegiocatore + "  ha le seguenti carte: \n" + mazzoinmano.toString() + "";
    }

    /**
     * Controlla l'accuso di assi, di due e di tre e restituisce i punti
     *
     * @return punti dell'accuso di assi, di due e di tre
     */
    private int controllaaccusi() {
        int x = 0; //variabile contatore
        int punti = 0;
        for (int i = 1; i <= 3; i++) {
            for (Carta a : this.getmazzoinmano().getmazzo()) {  //ciclo generalizzato
                if (a.getnumero() == i) {
                    x++;
                }
            }
            if (x == 3) { //accuso
                if (i == 1) {
                    System.out.println("" + this.getnomegiocatore() + " ha accuso di 3 assi");
                }
                if (i == 2) {
                    System.out.println("" + this.getnomegiocatore() + " ha accuso di 3 due");
                }
                if (i == 3) {
                    System.out.println("" + this.getnomegiocatore() + " ha accuso di 3 tre");
                }
            } else if (x == 4) { //accuso
                if (i == 1) {
                    System.out.println("" + this.getnomegiocatore() + " ha accuso di 4 assi");
                }
                if (i == 2) {
                    System.out.println("" + this.getnomegiocatore() + " ha accuso di 4 due");
                }
                if (i == 3) {
                    System.out.println("" + this.getnomegiocatore() + " ha accuso di 4 tre");
                }
            } else {
                x = 0;
            }
            punti = punti + x;
            x = 0;
        }
        return punti; //punti dell'accuso
    }

    /**
     * Controlla l'accuso di napoletana con il seme s e restituisce i punti
     *
     * @param s seme della napoletana
     * @return punti dell'accuso di napoletana
     */
    private int controllanapoletana(Seme s) {

        int x = 0;
        for (Carta a : this.getmazzoinmano().getmazzo()) { //ciclo generalizzato

            if (a.getnumero() == 1 && a.getseme() == s) { //trova assi
                x++;
            }
            if (a.getnumero() == 2 && a.getseme() == s) { //trova due
                x++;
            }
            if (a.getnumero() == 3 && a.getseme() == s) { //trova tre

                x++;
            }
        }

        if (x == 3) {
            System.out.println("" + this.getnomegiocatore() + " ha accuso di napoli");
        } else {
            x = 0;
        }
        return x;
    }

    /**
     * Controlla tutti gli accusi possibili nel mazzo di carte e aggiorna il
     * punteggio
     */
    public void controllaaccusifinale() {
        int a = this.controllaaccusi(); //controlla accuso di 3 assi,3 due e 3 tre
        int b = 0;
        for (Seme s : Seme.values()) {   //ciclo generalizzato
            b = b + this.controllanapoletana(s);  //controlla accuso di napoli
        }
        float y = this.getpunteggio();
        y = y + a + b;
        this.setpunteggio(y); //viene aggiornato il punteggio con gli accusi
    }

    /**
     * Aggiorna il pounteggio con il punto bonus dell'ultima mano
     */
    public void sommabonusfinale() {
        if (this.getturno()) {
            float x = this.getpunteggio();
            this.setpunteggio(x + 1);
        }
    }

    /**
     * Stampa il mazzo di carte mazzopunteggi
     *
     * @return la stampa del mazzo
     */
    private String stampamazzopunteggi() {
        String s = "";
        int i = 0;
        for (Carta c : this.getmazzopunteggi().getmazzo()) {  //ciclo generalizzato
            s = s + c + "\n";
            i++;
        }
        return s;
    }

    /**
     * Stampa il mazzo di carte prese a fine partita dal giocatore
     */
    public void stampapunteggifinepartita() {     //Stampa il mazzo delle carte vinte

        int x = this.getmazzopunteggi().getmazzo().size(); //lunghezza del mazzo di carte mazzopunteggi
        if (x != 0) {
            System.out.println("" + this.getnomegiocatore() + " ha preso le seguenti carte ");
            System.out.println(this.stampamazzopunteggi());
            System.out.println(" e ha totalizzato " + this.getpunteggio() + " punti");

        } else {
            System.out.println(" Non hai preso nessuna carta");
            System.out.print(" e ha totalizzato " + this.getpunteggio() + " punti");

        }

    }

    /**
     * Il giocatore inizia il turno tirando una carta
     *
     * @param v livello di difficoltà del giocatore
     * @return la carta tirata dal giocatore che inizia il turno
     */
    public abstract Carta tira(int v);

    /**
     * Il giocatore risponde tirando una carta
     *
     * @param a carta giocata dal giocatore che inizia il turno
     * @param v livello di difficoltà del giocatore
     * @return la carta tirata dal giocatore che risponde
     */
    public abstract Carta rispondi(Carta a, int v);
}
