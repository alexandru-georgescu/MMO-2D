package componente;
/**
 * Functionalitate: este o clasa care abordeaza un spell si ii seteaza
 * anumuti parametrii in functie de parametrii primiti.
 * @author Alexandru Georgescu
 *
 */
public class Modificator {
     private float vsROGUE;
     private float vsPYRO;
     private float vsWIZARD;
     private float vsKNIGHT;

     /**
      * Seteaza parametrii initial contra fiecare rasa.
      * @param vsROGUE
      * @param vsPYRO
      * @param vsWIZARD
      * @param vsKNIGHT
      */
    public final void setModificatori(final float vsROGUE, final float vsPYRO,
                                 final float vsWIZARD, final float vsKNIGHT) {
        this.vsROGUE = vsROGUE;
        this.vsPYRO = vsPYRO;
        this.vsWIZARD = vsWIZARD;
        this.vsKNIGHT = vsKNIGHT;
      }
 
       /**
        * Functionalitate: se primeste un "spell" si se returneaza spellul *
        * bonusul contra rasa respectiva.
        * @param dmg initial al spellului
        * @param hero tipul eroului in care trebuie castat spellul
        * @return dmg + bonus
        */
      public final float rasaModificator(final float dmg, final char hero) {

        if (hero == 'R') {
           return (int) Math.round(dmg * vsROGUE);
        } else if (hero == 'P') {
           return (int) Math.round(dmg * vsPYRO);
        } else if (hero == 'W') {
           return (int) Math.round(dmg * vsWIZARD);
        } else if (hero == 'K') {
           return (int) Math.round(dmg * vsKNIGHT);
        }
      return 0;
      }

       /**
        * Se adauga un bonus daca jucatorul se afla pe tipul lui de harta.
        * @param mapCaract mapa in care jucatorul este specialist.
        * @param mapFight mapa unde are loc lupta.
        * @param dmg pe care trebuie sa-l primeasca in plus daca se afla
        * pe harta respectiva
        * @return dmg sau 1 in caz ca nu se afla in mapa.
        */
      public final float mapaModificator(final char mapCaract,
                   final char mapFight, final float dmg) {

       if (mapCaract == mapFight) {
         return dmg;
       }
     return 1;
     }
}
