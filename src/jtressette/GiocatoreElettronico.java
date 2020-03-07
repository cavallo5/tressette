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
 *
 */
public class GiocatoreElettronico extends Giocatore {

    /**
     * Viene chiesto all'utente di scegliere la difficoltà del
     * GiocatoreElettronico
     *
     * @return 1 se Facile, 2 se Normale, 3 se Difficile
     */
    public int sceltalivellodidifficoltà() {
        System.out.print("Facile, Normale o Difficile?    dove 1=Facile o 2=Normale o 3=Difficile ");
        Scanner in = new Scanner(System.in);
        String Sceltalivello = in.next();
        boolean x = false;
        int y = 0;
        do {

            if ("1".equals(Sceltalivello)) { //Scelto il livello di difficoltà facile
                x = true;
                y = 1;
            }
            if ("2".equals(Sceltalivello)) { //Scelto il livello di difficoltà normale
                x = true;
                y = 2;
            }
            if ("3".equals(Sceltalivello)) { //Scelto il livello di difficoltà difficile
                x = true;
                y = 3;
            }

            if (x == false) { //non viene scelto un livello di difficoltà tra quelli possibili
                System.out.println("Puoi scegliere 1 o 2 o 3 dove 1=Facile o 2=Normale o 3=Difficile ");
                Sceltalivello = in.next();
            }

        } while (x == false);  //finchè non viene scelto un livello di difficoltà

        return y;

    }

    /**
     * Il giocatore Elettronico risponde alla carta giocata dal giocatore umano
     * con difficoltà semplice
     *
     * @param a carta giocata dal giocatore umano
     * @return la carta che gioca il giocatore elettronico
     */
    private Carta rispondisemefacile(Carta a) {   //so già che nel mazzo ho una carta dello stesso seme di a
        boolean x = false;
        Seme b = a.getseme(); //in b viene salvato il seme della carta a
        Carta estratta = null;
        int i = 0;
        while ((i < this.getmazzoinmano().getmazzo().size()) && !x) {

            estratta = mazzoinmano.getmazzoindex(i);  //rimuove dal mazzoinmano la carta i
            if (estratta.stessoseme(a)) { //se la carta estratta ha lo stesso seme della carta a
                estratta = mazzoinmano.remove(i);
                x = true;

            }
            i++;
        }
        return estratta; //la carta giocata
    }

    /**
     * Il giocatore Elettronico risponde alla carta giocata dal giocatore umano
     * con difficoltà normale
     *
     * @param a carta giocata dal giocatore umano
     * @return la carta che gioca il giocatore elettronico
     */
    private Carta rispondisemenormale(Carta a) {
        boolean x = false;
        Seme b = a.getseme(); //seme della carta a
        int c = a.getvaloregioco(); //valoregioco della carta a
        float d = a.getvalorepunteggio(); //valorepunteggio della carta a
        Carta estratta = null;
        int i = 0;
        while ((i < this.getmazzoinmano().getmazzo().size()) && !x) {
            estratta = mazzoinmano.getmazzoindex(i); //rimuove dal mazzoinmano la carta i 
            if (estratta.stessoseme(a) && d == 0 && estratta.getnumero() == 1 && !x) {//condizioni per buttare l'asso
                x = true; //la carta estratta sarà buttata
            }
            if (estratta.stessoseme(a) && d > 0 && estratta.getvaloregioco() > c && !x) { //condizioni per buttare una carta per fare punti
                x = true; //la carta estratta sarà buttata
            }
            if (x) { //se è stata trovata la carta estratta da buttare
                estratta = mazzoinmano.remove(i); //la carta estratta viene rimossa dal mazzoinmano
                mazzoinmano.ordinapernumeroeseme(); //il mazzoinmano viene ordinato
            }
            i++;
        }
        if (!x) { //se non è stata trovata una carta che verifica le condizioni
            estratta = this.tirafacile();
        }
        return estratta; //carta giocata dal giocatore elettronico
    }

    /**
     * Il giocatore Elettronico risponde alla carta giocata dal giocatore umano
     * con difficoltà difficile
     *
     * @param a carta giocata dal giocatore umano
     * @return la carta che gioca il giocatore elettronico
     */
    private Carta rispondisemedifficile(Carta a) {   //togliere remove e fare il controllo come tira
        boolean x = false;
        Seme b = a.getseme(); //seme della carta a
        int c = a.getvaloregioco(); //valoregioco della carta a
        float d = a.getvalorepunteggio(); //valorepunteggio della carta a
        Carta estratta = null;
        int f = a.getnumero(); //numero della carta a

        int indice = 0;
        int i = 0; //indice per il ciclo while

//gli indici sono in ordine di importanza
        int indice1 = -1;
        int indice2 = 0;
        int indice3 = 0;
        int indice4 = 0;
        int indice5 = 0;

        //indici non contatori, di supporto
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;

        while (i < this.getmazzoinmano().getmazzo().size() && indice1 == -1) {
            estratta = mazzoinmano.getmazzoindex(i);

            if (estratta.stessoseme(a)) { //se estratta ha lo stesso seme di a

                if (f == 1) { //se f è un asso
                    if (estratta.getvaloregioco() > c) { //se ho un 2 o un 3, lo tiro per prendere l'asso
                        indice1 = i;
                    } else { //se non ho un 2 o un 3
                        if (estratta.getvalorepunteggio() == 0) { //tira una carta che non vale punti
                            indice2 = i;
                        } else { //se non ho una carta che non vale punti
                            if (estratta.getvalorepunteggio() == (float) 1 / 3 && indice2 == 0) { //tira una figura
                                indice3 = i;
                            }
                        }
                    }
                }

                if (d == (float) 1 / 3 && c < 8) {// se f è un donna,cavallo o re (e non è un 2 o un 3)
                    if (estratta.getnumero() == 1) { // se ho un asso
                        indice1 = i;
                    } else { //se non ho un asso
                        if (estratta.getvaloregioco() > d && estratta.getvaloregioco() < 8) { //tira una figura maggiore della carta (tranne il 2 e il 3)
                            indice2 = i;
                        } else { //se non ho una figura(tranne il 2 e il 3) per vincere la carta a
                            
                            //Controllo quante carte ho dello stesso seme e vedo se ho il 2 e il 3
                            for (int j = 0; j < this.getmazzoinmano().getmazzo().size(); j++) { //ciclo tutte le carte del mazzoinmano. E' importante l'indice j
                                Carta estratta1 = mazzoinmano.getmazzoindex(j); //estratta1 è la carta estratta dal mazzoinmano dalla posizione j
                               if (estratta1.stessoseme(a)) { //se estratta1 ha lo stesso seme di a
                                    if (estratta1.getnumero() == 2) { //se estratta1 è un due
                                        i2 = j;  //indice di supporto 
                                    }
                                    if (estratta1.getnumero() == 3) { //se estratta1 è un tre
                                        i3 = j;   //indice di supporto
                                    }
                                    i1++; //indice di supporto usato per contare quante carte ho dello stesso seme di a
                                }
                            }

                            if (i1 > 3 && i2 != 0 && i3 != 0 && indice2 == 0) { //se ho più di 3 carte con lo stesso seme e contemporaneamente il 2 e 3 
                                indice3 = i2; //tira il 2
                            } else { //se non ho più di 3 carte con lo stesso seme compreso contemporaneamente il 2 e 3
                                if (estratta.getvalorepunteggio() == 0 && indice3 == 0) { //il valore punteggio di estratta è 0 e la condizione precedente non è verificata
                                    indice4 = i;  //tira una scartina
                                } else {
                                    if ((estratta.getnumero() == 2 || estratta.getnumero() == 3) && indice4 == 0) { //se ho il 2 o il 3 e le condizioni precedenti non sono verificate
                                        indice5 = i; //tira il 2 o il 3
                                    }
                                }
                            }
                        }
                    }
                }

                if (f == 2) { //se f è un due


                    //Controlli
                    for (int j = 0; j < this.getmazzoinmano().getmazzo().size(); j++) {
                        Carta estratta1 = mazzoinmano.getmazzoindex(j); //estratta1 è la carta estratta dal mazzoinmano dalla posizione j
                        if (estratta1.stessoseme(a) && estratta1.getnumero() == 1) { //se in mano ho l'asso dello stesso seme di a
                            x = true;
                        }
                        if (estratta1.stessoseme(a) && estratta1.getnumero() == 3) { //se in mano ho un 3 con lo stesso seme di a
                            i2 = j; //butterò il 3
                        }
                    }
                    
                    
                    for (Carta carta : this.getmazzopunteggi().getmazzo()) { //ciclo generalizzato
                        if (carta.getnumero() == 1) {  //controllo se nel mazzopunteggi del giocatoreelettronico c'è un asso
                            x = true;
                        }
                    }

                    //Fine controlli


                    if (x == true && i2 != 0) { //se l'asso è nel mazzopunteggi o nel mazzoinmano del giocatoreelettronico e ho un 3
                        indice1 = i2; //butto il 3
                    } else { //se non ho buttato il 3 perchè l'asso non è ancora uscito
                        if (estratta.getvalorepunteggio() == 0) {
                            indice2 = i; //tiro liscio
                        } else {
                            if (estratta.getvalorepunteggio() == (float) 1 / 3 && estratta.getvaloregioco() < 8 && indice2 == 0) {
                                indice3 = i; //tiro punti
                            } else {
                                if (estratta.getnumero() == 3 && indice3 == 0) {
                                    indice4 = i; //butto il 3
                                } else {
                                    if (estratta.getnumero() == 1 && indice4 == 0) {
                                        indice5 = i; //butto l'asso
                                    }
                                }
                            }
                        }
                    }
                }
                if (f == 3) { //se f è un tre
                    if (estratta.getvalorepunteggio() == 0) {
                        indice1 = i; //tiro liscio
                    } else {
                        if (estratta.getvalorepunteggio() == (float) 1 / 3 && estratta.getvaloregioco() < 8) {
                            indice2 = i; //tira otto, nove e dieci
                        } else {
                            if (estratta.getnumero() == 2 && indice2 == 0) {
                                indice3 = i; //tira il 2
                            } else {
                                if (estratta.getnumero() == 1 && indice3 == 0) {
                                    indice4 = i; //tira l'asso
                                }
                            }
                        }
                    }
                }


                if (d == 0) { //se f è una carta che ha punteggio 0 
                    if (estratta.getnumero() == 1) {
                        indice1 = i; //tiro asso
                    } else {
                        if (estratta.getvalorepunteggio() == (float) 1 / 3 && estratta.getvaloregioco() < 8) {
                            indice2 = i; //butto donna,cavallo o re
                        } else {
                            if (estratta.getvalorepunteggio() == 0 && indice2 == 0) {
                                indice3 = i; //butto una scartina
                            } else {
                                if ((estratta.getnumero() == 2 || estratta.getnumero() == 3) && indice3 == 0) {
                                    indice4 = i; //butto un due o un 3
                                }
                            }
                        }
                    }
                }
            }
            i++;
        }//finisce while


        if (indice1 != -1) {
            indice = indice1;
        } else {
            if (indice2 != 0) {
                indice = indice2;
            } else {
                if (indice3 != 0) {
                    indice = indice3;
                } else {
                    if (indice4 != 0) {
                        indice = indice4;
                    } else {
                        if (indice5 != 0) {
                            indice = indice5;
                        }
                    }
                }
            }
        }

        estratta = mazzoinmano.remove(indice); //rimuove la carta dal mazzoinmano del giocatoreelettronico dalla posizione indice
        mazzoinmano.ordinapernumeroeseme(); //ordina il mazzoinmano
        return estratta;
    }

    /**
     * In base alla difficoltà, il Giocatore Elettronico risponde al Giocatore
     * Umano
     *
     * @param a carta giocata dal Giocatore Umano
     * @param v livello di difficoltà della CPU (utilizzato solo se si gioca
     * contro un GiocatoreElettronico)
     * @return
     */
    @Override
    public Carta rispondi(Carta a, int v) //a è la carta tirata dal giocatore umano
    {
        Carta dagiocare = null;
        boolean x = false;
        for (int i = 0; (i < this.getmazzoinmano().getmazzo().size()) && (!x); i++) {
            Carta verifica = this.getmazzoinmano().getmazzoindex(i); //verifica è la carta del mazzoinmano in posizione i
            if (verifica.stessoseme(a)) { //se nel mazzo trovo una carta con lo stesso seme di a
                x = true;
            }
        }
        if (x) {  //il GiocatoreElettronico ha in mano una carta con lo stesso seme di a
            System.out.println("Tocca al giocatore " + this.getnomegiocatore() + "");
            if (v == 1) { //livello di difficoltà facile
                dagiocare = rispondisemefacile(a);
            }
            if (v == 2) { //livello di difficoltà normale
                dagiocare = rispondisemenormale(a);
            }
            if (v == 3) { //livello di difficoltà difficile
                dagiocare = rispondisemedifficile(a);
            }
        } else //il GiocatoreElettronico non ha in mano una carta con lo stesso seme di a
        {
            if (v == 1) { //livello di difficoltà facile
                dagiocare = this.tirafacile();
            }
            //metodo in comune per livello di difficoltà normale e difficile quando il GiocatoreElettronico non ha una carta dello stesso seme di a
            boolean zeta = false;
            if (v == 2 || v == 3) { //livello di difficoltà normale o difficile
                for (int j = 0; j < this.getmazzoinmano().getmazzo().size() && !zeta; j++) {
                    dagiocare = mazzoinmano.getmazzoindex(j);
                    if (dagiocare.getvaloregioco() < 8) { //tira una carta qualsiasi tranne l'asso, il due e il tre
                        dagiocare = this.getmazzoinmano().remove(j); //rimuove la carta dal mazzoinmano
                        zeta = true;
                    }
                }


                if (zeta != true) {
                    dagiocare = this.tirafacile();
                }
            }
        }
        return dagiocare;
    }

    /**
     * In base alla difficoltà, il Giocatore Elettronico tira la carta
     *
     * @param v livello di difficoltà della CPU (utilizzato solo se si gioca
     * contro un GiocatoreElettronico);
     * @return la carta giocata dal Giocatore Elettronico
     */
    @Override
    public Carta tira(int v) {
        Carta dagiocare = null;
        if (v == 1) {  //se il livello di difficoltà è facile
            dagiocare = this.tirafacile();  //tira con livello di difficoltà facile
        }
        if (v == 2 || v == 3) { //se il livello di difficoltà è normale o difficile
            dagiocare = this.tiranormale(); //tira con livello di difficoltà normale
        }
        return dagiocare;
    }

    /**
     * Il Giocatore Elettronico tira la carta con difficoltà facile
     *
     * @return la carta giocata dal Giocatore Elettronico
     */
    private Carta tirafacile() {

        int f = (int) (Math.random() * (this.getmazzoinmano().getmazzo().size())); //valore compreso tra 0 e il numero delle carte in mano
        Carta dagiocare = this.getmazzoinmano().remove(f); //rimuove la carta dal mazzoinmano
        return dagiocare;
    }

    /**
     * Il Giocatore Elettronico tira la carta con difficoltà normale
     *
     * @return la carta giocata dal Giocatore Elettronico
     */
    public Carta tiranormale() {
        boolean x = false;
        Carta dagiocare = null;
        for (int i = 0; (i < this.getmazzoinmano().getmazzo().size()) && !x; i++) {
            dagiocare = this.mazzoinmano.getmazzoindex(i); //dagiocare è la carta in posizione i
            if (dagiocare.getvaloregioco() < 8) { //se dagiocare non è un asso, un 2 o un 3
                dagiocare=mazzoinmano.remove(i);
                x = true;
              }
        }
        if (x == false) { //nessuna carta è stata tirata
            dagiocare = this.tirafacile();
        }
        return dagiocare;
    }
}

