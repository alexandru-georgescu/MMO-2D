package componente;

import interfete.CONST;
import interfete.MAGIC;
import interfete.Vraja;

/**
 * @author Alexandru Georgescu
 * --
 * Info: reprezinta incapsularea unui obiect de tip caracter care depinde
 * de mai multi factorii.
 */
public class Caracter implements CONST, MAGIC {
     private int hp;
     private int maxHp;
     private int xp;
     private int x, y;
     private int level;
     private int poison;
     private int poisonCount;
     private char clasa;
     private boolean stun;
     private int stunCount;
     Vraja[] magie;

     public Caracter() {

     }
     /**
      * @param clasa - tipul de campion R, W, K, P.
      * @param x - reprezinta pozitia pe harta pe axa inaltimii.
      * @param y - reprezinta pozitia pe harta pe axa latimii.
      */
     public Caracter(final char clasa, final int x, final int y) {
         this.clasa = clasa;
         this.poisonCount = 0;
         this.poison = 0;
         this.xp = 0;
         this.x = x;
         this.y = y;
         this.level = 0;
         this.hp = setupHp();
         this.maxHp = setupHp();
         this.setupSpell();
     }

     /**
      * getX() returneaza valoarea de din parametrul x.
      * getY() returneaza valoarea de din parametrul y.
      * getClasa() returneaza valoarea de din parametrul clasa.
      * getLevel() returneaza valoarea de din parametrul level.
      * getXp() returneaza valoarea de din parametrul xp.
      * getHp() returneaza valoarea de din parametrul hp.
      * getMaxHp() returneaza valoarea de din parametrul maxHp.
      */
     public int getX() {
        return this.x;
     }

     public final int getY() {
        return this.y;
     }

     public final char getClasa() {
        return this.clasa;
     }

     public final int getLevel() {
        return this.level;
     }

     public final int getXp() {
        return this.xp;
     }

     public final int getHp() {
        return this.hp;
     }

     public final int getMaxHp() {
        return this.maxHp;
     }

     /**
      * @param move - miscarea pe care caracterul trebuie sa o execute.
      * @param marime - reprezinta lungimea si latimea hartii.
      * --
      * Functionalitate: se verifica ce tip de miscare trebuie sa execute
      * un caracter si daca aceasta este posibila.
      */
     public void moveC(final char move, final int[] marime) {

         if (move == 'L' && (this.y - 1) >= 0) {
            this.y -= 1;
         } else if (move == 'U' && (this.x - 1) >= 0) {
            this.x -= 1;
         } else if (move == 'R' && (this.y + 1) < marime[1]) {
            this.y += 1;
         } else if (move == 'D' && (this.x + 1) < marime[0]) {
           this.x += 1;
         }
     }

     /**
      * @param dmg reprezinta valoare de pagube pe care o primeste caracterul
      * --
      * Functionalitate: se scade din viata caracterului dmg-ul primit si
      * se verifica daca viata devine negatica, daca se intampla acest
      * lucru valoarea hp-ului se v-a seta ca 0.
      */
     public void setDmg(final int dmg) {
        this.hp -= dmg;
        if (this.hp < 0) {
           this.hp = 0;
        }
     }

     /**
      * @param jucator care a pierdut confruntarea.
      * --
      * Functionalitate: se verifica valoarea de xp pe care caracterul
      * trebuie sa o primeasca iar daca aceasta este negativa(diferenta
      * mare de nivel) atunci ea se va seta pe 0 si nu v-a fi adaugat xp
      * daca este mai mare ca 0 atunci se va cumula cu restul de xp pe
      * care il detine.
      */
     public void addXp(final Caracter jucator) {
        int xp = (MINXP - (this.level - jucator.getLevel()) * MINXPCOUNT);
        if (xp > 0) {
           this.xp += xp;
        }
     }

     /**
      * Functionalitate: se verifica daca caracterul a trecut pragul
      * limita pentru a avansa in nivel se apeleaza metoda levelUp()
      * apoi se verifica daca caracterul este in continuare eligibil
      * pentru a creste in nivel.
      * --
      * @see levelUp()
      */
     public void checkLevelUp() {

        int xp = MINXPLVL + this.level * XPCOUNT;
        while (this.xp >= xp) {
           this.levelUp();
           xp += XPCOUNT;
        }
    }

     /**
      * Functionalitate: caracterul creste in nivel, hp-ul devine 100%,
      * se adauga un bonus de hp in functie de level, se reinitializeaza
      * valoarea maxima de hp pe care o poate avea si se efectueaza si
      * evoluarea varjilor.
      */
     public void levelUp() {

         this.level++;
         this.hp = setupHp() + this.level * bonusHp();
         this.maxHp = this.hp;
         this.magie[0].levelUpSpell();
         this.magie[1].levelUpSpell();
     }

      /**
      * @param poison - valoarea de dmg pe care un jucator o primeste
      * @param count  - numarul de runde in care trebuie sa primeasca
      *                 valoarea din poison
      * @param killer - adverasul care a aplicat efectul de poison
      * --
      * Functionalitate: se adauga un efect de tip overtime unui
      * caracter.
      */
     public void setPoison(final int poison, final int count) {
         this.poison = poison;
         this.poisonCount = count;
     }

     /**
      * Functionalitate:se verifica daca adversarul nu este deja mort
      * (pentru ca sa stim sigur daca trebuie sa primeasca xp sau nu
      * aversarul care l-a omorat) si daca efectul mai este valabil,
      * se aplica efectul daca este nevoie si se scade rundele de efect
      * iar apoi se verifica daca campionul a murit in urma acestui efect,
      * daca da atunci se incrementeaza xp-ul campionului care a pus acest
      * efect.
      * --
      * @see addXp()
      * @see checkLevelUp()
      */
     public void checkPoison() {

         if (this.poisonCount > 0 && !this.isDeath()) {
            this.hp -= poison;
            this.poisonCount -= 1;
         }
     }

     /**
      * Functionalitate: aplica un efect de tip impbobilizare asupra unui
      * jucator.
      */
     public void stun(final int count) {
         this.stun = true;
         this.stunCount = count;
     }

     /**
      * Functionalitate: aplica o verificare a efectului de stun.
      * @return true daca jucatorul este imobilizat si se inchide efectul de
      * imobilizare pentru tura urmatoare daca au trecut rundele de stat sau
      * false daca jucatorul nu este imobilizat.
      * --
      * @see stun()
      */
     public boolean checkStun() {
        if (this.stun == true && this.stunCount > 1) {
           this.stunCount--;
           return true;
        } else if (this.stun == true && this.stunCount == 1) {
           this.stun = false;
           this.stunCount--;
           return true;
        }
        return false;
     }

     /**
      * Functionalitate: se verifica starea caracterului.
      * --
      * @return true daca caracterul este mort sau false daca nu este.
      */
     public boolean isDeath() {
        if (this.hp <= 0) {
           return true;
        }
        return false;
     }

     /**
      * Functionalitate: se adauga un bonus de hp la hp-ul maxim.
      * --
      * @return bonsul de hp pe care-l primeste atunci cand face lvlup.
      * @see levelUp()
      */
     private int bonusHp() {
       if (this.clasa == 'K') {
          return HP_BONUS_K;
       } else if (this.clasa == 'P') {
          return HP_BONUS_P;
       } else if (this.clasa == 'R') {
          return HP_BONUS_R;
       } else if (this.clasa == 'W') {
          return HP_BONUS_W;
       }
       return 0;
     }

     /**
      * Functionalitate: se seteaza hp-ul in functie de tipul clasei.
      * --
      * @return valoarea numerica a hp-ului.
      */
     private int setupHp() {

       if (this.clasa == 'K') {
          return HP_KNI;
       } else if (this.clasa == 'P') {
          return HP_PYRO;
       } else if (this.clasa == 'R') {
          return HP_ROGUE;
       } else if (this.clasa == 'W') {
          return HP_WIZ;
       }
       return 0;
     }

     /**
      * Functionalitate: se adauga cele doua spelluri coresponente
      * unui campion cu un anumit tip de clasa.
      */
     private void setupSpell() {
        this.magie = new Vraja[2];

        if (this.clasa == 'K') {
            this.magie[0] = new Vraji.Execute();
            this.magie[1] = new Vraji.Slam();
        } else if (this.clasa == 'P') {
            this.magie[0] = new Vraji.Fireblast();
            this.magie[1] = new Vraji.Ignite();
        } else if (this.clasa == 'R') {
            this.magie[0] = new Vraji.Backstab();
            this.magie[1] = new Vraji.Paralysis();
        } else if (this.clasa == 'W') {
            this.magie[0] = new Vraji.Drain();
            this.magie[1] = new Vraji.Deflect();
        }
     }

     public final float returnDmg(final char mediu) {
        return (this.magie[0].dmg(mediu) + this.magie[1].dmg(mediu));
     }

}
