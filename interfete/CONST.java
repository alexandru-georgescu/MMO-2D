package interfete;

/**
 * Chiar daca sunt undele constante cu valori egale am plecat de la
 * presupunerea ca este mai eficient sa le pastrez pe toate in functie
 * de clasa pentru ca in acest mod putem schimba cu usurinta beneficiile
 * unei clase fara a influenta restul codului.
 * --
 * @author Alexandru Georgescu
 *
 */
public interface CONST {
     /* HP */
     int HP_ROGUE = 600;
     int HP_PYRO = 500;
     int HP_WIZ = 400;
     int HP_KNI = 900;

     /* BONUS HP */
     int HP_BONUS_R = 40;
     int HP_BONUS_P = 50;
     int HP_BONUS_W = 30;
     int HP_BONUS_K = 80;

     /* MAP */
     float WOODS =  1.15F;
     float VOLCANIC = 1.25F;
     float DESERT = 1.1F;
     float LAND = 1.15F;

     /* ROGUE CLASA - CONTRA CALASA - NUMAR SPELL*/
     float CRIT =  1.5F;
     float RRS1 =  1.20F;
     float RPS1 =  1.25F;
     float RWS1 =  1.25F;
     float RKS1 =  0.90F;

     float RRS2 =  0.9F;
     float RPS2 =  1.2F;
     float RWS2 =  1.25F;
     float RKS2 =  0.80F;

     /* PYRO CLASA - CONTRA CALASA - NUMAR SPELL*/
     float PRS1 =  0.8F;
     float PPS1 =  0.9F;
     float PWS1 =  1.05F;
     float PKS1 =  1.2F;

     float PRS2 =  0.8F;
     float PPS2 =  0.9F;
     float PWS2 =  1.05F;
     float PKS2 =  1.2F;

     /* WIZARD CLASA - CONTRA CALASA - NUMAR SPELL*/
     float WRS1 =  0.8F;
     float WPS1 =  0.9F;
     float WWS1 =  1.05F;
     float WKS1 =  1.2F;

     float WRS2 =  1.2F;
     float WPS2 =  1.3F;
     float WWS2 =  0F;
     float WKS2 =  1.4F;

     /* KNIGHT CLASA - CONTRA CALASA - NUMAR SPELL*/
     float KRS1 =  1.15F;
     float KPS1 =  1.1F;
     float KWS1 =  0.8F;
     float KKS1 =  1F;

     float KRS2 =  0.8F;
     float KPS2 =  0.9F;
     float KWS2 =  1.05F;
     float KKS2 =  1.2F;
}
