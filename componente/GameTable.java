package componente;

import java.io.IOException;

import FileIO.FileSystem;

 /**
 * @author Alexandru Georgescu
 * --
 * Incapsulare de forma matriceala, care include marimea tablei, tipul,
 * personajele si numarul de runde.
 */
public class GameTable {

    private final int[] size;
    private final char[][] tip;
    private final Caracter[] personaje;
    private final int nrPersonaje;
    private int runde;

    /**
     * @param inaltime = axa OX
     * @param lungime = axa OY
     * @param tip = tipul de teren
     * @param nrP = numarul de persoane
     * @param runde = numarul de runde
     * --
     * Functionalitate: se initializeaza valorile specifice obiectului.
     */
   public GameTable(final int inaltime, final int lungime, final char[][] tip,
                     final int nrP, final int runde) {

        this.size = new int[2];
        this.size[0] = inaltime;
        this.size[1] = lungime;
        this.tip = tip;
        this.nrPersonaje = nrP;
        this.personaje = new Caracter[this.nrPersonaje];
        this.runde = runde;
    }

    /**
     * @param tipP vector cu tipul de teren
     * @param x vector cu coordonatele x.
     * @param y vector cu coordonatele y.
     * --
     * Functionalitate: adauga persoane in joc.
     */
    public void adaugaPersonaje(final char[] tipP, final int[] x,
                                final int[] y) {

        for (int i = 0; i < this.nrPersonaje; i++) {
            this.personaje[i] = new Caracter(tipP[i], x[i], y[i]);
        }
    }

    /**
     * Functionalitate: se parcurge "matricea" de pozitii si se verifica
     * daca exista jucatori pe aceasi pozitie, daca da atunci o sa se ape-
     * leze metoda fight pentru a calcula rezultatele confruntarii dintre
     * cei doi.
     */
    private void getColizion() {
       for (int i = 0; i < this.nrPersonaje; i++) {
        for (int j = i + 1; j < this.nrPersonaje; j++) {
          if (this.personaje[i].getX() == this.personaje[j].getX()
             && this.personaje[i].getY() == this.personaje[j].getY()
             && !this.personaje[i].isDeath()
             && !this.personaje[j].isDeath()) {
                int x = this.personaje[i].getX();
                int y = this.personaje[i].getY();
                this.fight(this.personaje[i], this.personaje[j], tip[x][y]);
          }
        }
       }
    }

    /**
     * @param miscari reprezinta directia in care trebuie sa se deplaseze un
     * jucator.
     * @param write reprezinta functia de scriere in fisier.
     * @throws IOException este un specificator de erori sau cauze impus de
     * "libraria" FileIO.jar.
     * --
     * Functionalitate: se initializeaza o runda noua care trimite un vector
     * de tip miscare care reprezinta directia fiecarui jucator, se executa
     * un numar fix de runde iar apoi se scrie rezultatele obtinute in urma
     * acestora.
     * --
     * @see rundaNoua(char[] miscari)
     * @see showResult
     */
    public void startJoc(final char[][] miscari, final FileSystem write)
                         throws IOException {
      for (int i = 0; i < this.runde; i++) {
          rundaNoua(miscari[i]);
       }
       this.showResult(write);
    }

    /**
     * @param miscari reprezinta vectorul de miscari.
     * --
     * Functionalitate: fiecare jucator are cate o miscare de executat,
     * inainte ca miscarea sa fie executata se aplica parametrii overtime
     * daca acestia exista iar apoi se verifica daca nu cumva jucatorul
     * este mort sau daca este imobilizat, dupa aceste verificari fiecare
     * jucator isi efectueaza miscarea specifica, apoi se verifica daca
     * exista coliziuni intre jucatori iar la final se parcurge fiecare
     * caracter si se verifica daca acesta este eligibil sa faca lvlup.
     * --
     * @see getColizion()
     * @see checkPoison()
     * @see checkStun()
     */
    public void rundaNoua(final char[] miscari) {

        for (int i = 0; i < this.nrPersonaje; i++) {

            this.personaje[i].checkPoison();
            if (!this.personaje[i].checkStun()
             && !this.personaje[i].isDeath()) {

               this.personaje[i].moveC(miscari[i], size);
            }
        }
        this.getColizion();

        for (int i = 0; i < this.nrPersonaje; i++) {
           if (!this.personaje[i].isDeath()) {
              this.personaje[i].checkLevelUp();
           }
        }
    }

    /**
     * Prin executarea acestei metode programul v-a scrie rezultatele
     * optinute in urma rularii metodei StartJoc in fisierul trimis ca
     * parametru in args[1].
     * --
     * Functionalitate: se parcurge vectorul de personaje, daca perso-
     * najele sunt moarte atunci se v-a afisa mesajul specific acestora
     * altfel se v-a afisa o line de forma: clasa level xp viata x y.
     */
    private void showResult(final FileSystem write) throws IOException {

       for (int i = 0; i < this.nrPersonaje; i++) {
            if (this.personaje[i].isDeath()) {
               write.writeWord(this.personaje[i].getClasa() + " dead");
               write.writeNewLine();
            } else {
               write.writeWord(this.personaje[i].getClasa() + " "
                             + this.personaje[i].getLevel() + " "
                             + this.personaje[i].getXp() + " "
                             + this.personaje[i].getHp() + " "
                             + this.personaje[i].getX() + " "
                             + this.personaje[i].getY());
               write.writeNewLine();
             }
       }
     }

    /**
     * @param pers1 - parametrii reprezentand doi jucatori care se afla
     * @param pers2 - pe aceasi pozitie.
     * @param mediu mediul in care se desfasoara lupta.
     * --
     * Functionalitate: fiecare jucator isi calculeaza dmg-ul pe care il
     * poate da in functie de mediu si tipul adversarului iar apoi se
     * verifica daca exista un castigator si atunci si se updateaza xp-ul
     * altfel nu se intampla nimic.
     */
    private void fight(final Caracter pers1, final Caracter pers2,
                       final char mediu) {

        int x1 = pers1.magie[0].castSpell(pers2, mediu, pers1)
               + pers1.magie[1].castSpell(pers2, mediu, pers1);

        int x2 = pers2.magie[0].castSpell(pers1, mediu, pers2)
               + pers2.magie[1].castSpell(pers1, mediu, pers2);

        pers2.setDmg(x1);
        pers1.setDmg(x2);

        if (!pers1.isDeath() && pers2.isDeath()) {
           pers1.addXp(pers2);
        } else if (pers1.isDeath() && !pers2.isDeath()) {
           pers2.addXp(pers1);
        }

    }
}


