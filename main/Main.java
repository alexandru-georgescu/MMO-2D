package main;
import java.io.IOException;

import FileIO.FileSystem;
import componente.GameTable;

/**
 * Citirea cu ajutorul fisierului FileIO
 * Am realizat citirea in main pentru ca este functia principala
 * unde au loc implementarile ca citirea sau scirerea.
 * @author Alexandru Georgescu
 *
 */
public class Main {

    public static void main(final String[] args) throws IOException {
        FileSystem scan = new FileSystem(args[0], args[1]);

        int lungime = scan.nextInt();
        int latime = scan.nextInt();
        int nrPersonaje;
        String save;
        char[][] tip = new char[lungime][latime];

        for (int i = 0; i < lungime; i++) {
           save = scan.nextWord();
           for (int j = 0; j < latime; j++) {
                tip[i][j] = save.charAt(j);
           }
        }

        nrPersonaje = scan.nextInt();

        char[] clasa = new char[nrPersonaje];
        int[] x = new int[nrPersonaje];
        int[] y = new int[nrPersonaje];

        for (int i = 0; i < nrPersonaje; i++) {
            save = scan.nextWord();
            clasa[i] = save.charAt(0);
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }

        int runde = scan.nextInt();
        GameTable joc = new GameTable(lungime, latime, tip, nrPersonaje, runde);
        char[][] miscari = new char[runde][nrPersonaje];

        for (int i = 0; i < runde; i++) {
            save = scan.nextWord();
            for (int j = 0; j < nrPersonaje; j++) {
                miscari[i][j] = save.charAt(j);
            }
        }

       joc.adaugaPersonaje(clasa, x, y);
       joc.startJoc(miscari, scan);
       scan.close();
 }
}
