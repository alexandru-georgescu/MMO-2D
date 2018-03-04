package componente;

import interfete.CONST;
import interfete.MAGIC;
import interfete.Vraja;

/**
 * Structura: O clasa externa cu alte 8 clase interne statice.
 * Am ales aceasta structura deoarece ajuta la incapsularea tuturor
 * spellurilor.
 * Fiecare spell dispune de:
 *  - o metoda castSpell() care returneaza dmg
 * care trebuie dat intr-un erou +- bonusul de rase sau tren.
 *  - o metoda dmg() care calculeaza dmg-ul fara modificari de rasa.
 *  - o metoda lvlUpSpell() creste levelul fiecarui spell.
 * @author Alexandru Georgescu
 *
 */
public class Vraji {

   //Pyro
   static class Fireblast extends Modificator implements Vraja, CONST, MAGIC {
       private float spell =  FIREBLEAST;

       public int castSpell(final Caracter camp, final char mediu,
                            final Caracter me) {
           float cast = spell;
           char hero = camp.getClasa();
           super.setModificatori(PRS1, PPS1, PWS1, PKS1);
           cast = super.rasaModificator(cast, hero);
           cast *= super.mapaModificator('V', mediu, VOLCANIC);
           return (int) Math.round(cast);
       }

       public void levelUpSpell() {
           this.spell += LVLUPFIREBLEAST;
       }


        public float dmg(final char mediu) {
          if (mediu == 'V') {
             return Math.round(this.spell * VOLCANIC);
          } else {
            return this.spell;
            }
        }
    }
    static class Ignite extends Modificator implements Vraja, CONST, MAGIC {

        private float spell = IGNITE;
        private float bonusDmg = IGNITEBONUS;

        public int castSpell(final Caracter camp, final char mediu,
                final Caracter me) {

            float cast = spell;
            float bonus = bonusDmg;
            char hero = camp.getClasa();

            super.setModificatori(PRS2, PPS2, PWS2, PKS2);
            cast = super.rasaModificator(cast, hero);
            cast *= super.mapaModificator('V', mediu, VOLCANIC);

            bonus = super.rasaModificator(bonus, hero);
            bonus *= super.mapaModificator('V', mediu, VOLCANIC);
            camp.setPoison((int) Math.round(bonus), 2);
            return (int) Math.round(cast);
        }

        public void levelUpSpell() {
            this.spell += LVLUPIGN;
            this.bonusDmg += LVLUPIGNBONUS;
        }


        public float dmg(final char mediu) {
            if (mediu == 'V') {
               return Math.round(this.spell * VOLCANIC);
            } else {
                return this.spell;
            }
        }
    }

    //Knight
    static class Execute extends Modificator implements Vraja, CONST, MAGIC {
        private float spell = EXECUTE;
        private float execute = EXECUTEWHEN;

        public int castSpell(final Caracter camp, final char mediu,
                final Caracter me) {

            float cast = spell;
            char hero = camp.getClasa();

            if (camp.getHp() < execute * camp.getMaxHp()) {
                camp.setDmg(camp.getHp());
            }
            super.setModificatori(KRS1, KPS1, KWS1, KKS1);
            cast = super.rasaModificator(cast, hero);
            cast *= super.mapaModificator('L', mediu, LAND);
            return (int) Math.round(cast);
        }

        public void levelUpSpell() {
            this.spell += LVLUPEXECUTE;
            if (this.execute < PROCENTEXECUTEW) {
                this.execute += LVLUPEXECUTEW;
            }
        }

        public float dmg(final char mediu) {
            if (mediu == 'L') {
               return (float) (this.spell * LAND);
            } else {
               return this.spell;
            }
        }
    }
    static class Slam extends Modificator implements Vraja, CONST, MAGIC {
        private float spell = SLAM;

        public int castSpell(final Caracter camp, final char mediu,
                final Caracter me) {
            float cast = spell;

            char hero = camp.getClasa();
            super.setModificatori(KRS2, KPS2, KWS2, KKS2);
            cast = super.rasaModificator(cast, hero);
            cast *= super.mapaModificator('L', mediu, LAND);
            camp.stun(1);
            return (int) Math.round(cast);
        }

        public void levelUpSpell() {
            this.spell += LVLUPSLAM;
        }

        public float dmg(final char mediu) {
            if (mediu == 'L') {
               return (float) (this.spell * LAND);
            } else {
                return this.spell;
            }
        }
    }

    //Wizard
    static class Drain extends Modificator implements Vraja, CONST, MAGIC {
        private float spell = DRAIN;

        public int castSpell(final Caracter camp, final char mediu,
                final Caracter me) {

            float cast = spell;
            char hero = camp.getClasa();

            super.setModificatori(WRS1, WPS1, WWS1, WKS1);
            cast = super.rasaModificator(cast, hero);
            cast *= super.mapaModificator('D', mediu, DESERT);
            cast /= PROCENT;

            if ((HPPROC * camp.getMaxHp()) < camp.getHp()) {
               cast *= HPPROC * camp.getMaxHp();
            } else {
               cast *= camp.getHp();
            }
            return (int) Math.round(cast);
         }


        public void levelUpSpell() {
            this.spell += LVLUPDRAIN;
        }

        public float dmg(final char mediu) {
           return this.spell;
        }
    }
    static class Deflect extends Modificator implements Vraja, CONST, MAGIC {

        private float spell = DEFLECT;

        public int castSpell(final Caracter camp, final char mediu,
                final Caracter me) {

            float cast = spell;
            char hero = camp.getClasa();

            cast *= (float) (camp.returnDmg(mediu));
            cast *= super.mapaModificator('D', mediu, DESERT);
            super.setModificatori(WRS2, WPS2, WWS2, WKS2);
            cast = super.rasaModificator(cast, hero);

            return (int) Math.round(cast);
        }

        public void levelUpSpell() {
            if (spell < DEFLECTMAX) {
               this.spell += LVLUPDEFLECT;
            }
        }

        public float dmg(final char mediu) {
           return 0;
        }
    }

    //Rogue
    static class Backstab extends Modificator implements Vraja, CONST, MAGIC {

        private float spell = BACKSTAB;
        private int count = 0;

        public int castSpell(final Caracter camp, final char mediu,
                             final Caracter me) {

            float cast = spell;
            char hero = camp.getClasa();

            super.setModificatori(RRS1, RPS1, RWS1, RKS1);
            cast = super.rasaModificator(cast, hero);

            if (mediu == 'W' && this.count % MOD3 == 0) {
                cast *= CRIT;
                cast *= WOODS;
            } else if (mediu == 'W') {
                cast *= WOODS;
            }
            this.count++;
            return (int) Math.round(cast);
         }

         public void levelUpSpell() {
            this.spell += LVLUPBACKSTAB;
         }

        public float dmg(final char mediu) {
            if (mediu == 'W' && (this.count - 1) % MOD3 <= 0) {
               return Math.round(this.spell * WOODS * CRIT);
            }
            if (mediu == 'W') {
               return Math.round(this.spell * WOODS);
            } else {
               return this.spell;
            }
        }
    }

   static class Paralysis extends Modificator implements Vraja, CONST, MAGIC {

        private float spell = PARALYSIS;

        public int castSpell(final Caracter camp, final char mediu,
                final Caracter me) {

            float cast = spell;
            char hero = camp.getClasa();
            super.setModificatori(RRS2, RPS2, RWS2, RKS2);
            cast = super.rasaModificator(cast, hero);
            cast *= super.mapaModificator('W', mediu, WOODS);

            if (mediu == 'W') {
               camp.setPoison((int) Math.round(cast), WITHWOODS);
               camp.stun(WITHWOODS);
            } else {
               camp.setPoison((int) Math.round(cast), WITHOUTWOODS);
               camp.stun(WITHOUTWOODS);
            }

            return (int) Math.round(cast);
        }

        public void levelUpSpell() {
            this.spell += LVLUPPARALYSIS;
        }

        public float dmg(final char mediu) {
            if (mediu == 'W') {
               return (float) (this.spell * WOODS);
            } else {
               return this.spell;
            }
        }
    }
}
