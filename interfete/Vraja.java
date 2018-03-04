package interfete;

import componente.Caracter;

/**
 * Interfata care ajuta la ajustarea unui spell.
 * Fiecare spell poate fi implementat doar cu metodele initializate
 * in aceasta interfata, fapt ce ajuta la incapsularea mult mai buna
 * a acestora.
 * @author Alexandru Georgescu
 *
 */

public interface Vraja {

    int castSpell(Caracter camp, char mediu, Caracter me);
    void levelUpSpell();
    float dmg(char mediu);
}
