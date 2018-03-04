==============================IMPLEMENTARE=================================
* Idee: Am preferat sa privesc fiecare caracter ca un OM deci nu am avut
nevoie de implementarea altor clase de tip clasa, am ales sa fac astea din
cauza ca daca noi am vrea sa adaugam un alt personaj atunci ar trebui sa-i
creem alta clasa apoi sa legam clasa respectiva cu clasa erou, am ales sa 
nu folosesc aceasta metoda(ducea la un Double Dispatch mai evident), am 
ramas cu o singura clasa caracter care are fuctionaltiati generale pentru
fiecare clasa. In legatura cu masa de joc am caracterizat aceasta idee ca 
o clasa "gameTable" unde se desfasoara jocul propriu zis.

* Structura proiectului:
 - O clasa principala: Main in care se realizeaza citirea cat si pornirea
 jocului. (PACHETUL MAIN)
 - Pachetul compnente:
   1. Caracter: - este o clasa in care este conturat un caracter, o clasa 
   formata din parametrii de tip coordonate, tip-ul clasei, xp, viata etc.
                - metodele clasei viseaza caracterul in sine si ajuta la
   modificarea stariile acestuia cat si parametrii. 
                - Clasa este explicata mult mai clar in fisier.

   2. Vraji: - este o clasa care contine mai multe subclase interne cu 
   spelluri, am folosit aceasta metoda deoarece am vrut sa incapsulez 
   absolut toate spell-urile intr-o singura structura de data, ajuta
   atunci cand vrei sa implementezi inca un spell sau daca vrei sa-l 
   modifici sau stergi, foarte flexibil si usor.
             - fiecare clasa interna este denumita exacut cum se nu-
             meste spellul si contine 3 metode: castSpell(), dmg() si
             lvlUpSpell()
             - castSpell calculeaza dmg propriu zis + bonusul de rasa si
             teren pe care trebuie sa-l casteze intr-un alt campion in 
             functie de parametrii primiti.
             - dmg calculeaza dmg-ul pe care un campion il da in alt 
             campion doar in functie de mediu.
             - lvlUpSpell creste dmg-ul spell-urilor atunci cand face
             lvlUp caracterul.

    3. GameTable: - este clasa care ajuta la conturarea mesei de joc
                  - contine parametrii precum tipul fiecarei bucati din
                  harta, dimensiunea, personajele si numarul lor.
                  - este compusa din mai multe metode: 
                    1: O metoda de adaugare de personaje in functie de 
                    parametrii primiti.
                    2: O metoda care cauta coliziuniile dintre personaje.
                    3: O metoda startJoc care este punctul de incepere al
                    jocului unde personajele isi executa pasii in fiecare
                    runda +- se bat.(se foloseste de metoda RundaNoua)
                    4: RundaNoua care reprezinta totalitatea pasilor pe care
                    fiecare jucator trebuie sa o faca intr-o runda.
                    5: ShowResult este o metoda care afiseaza rezultatele 
                    pentru fiecare jucator la sfarsitul jocului.
                    6: Fight este o metoda care se apeleaza atunci cand doi
                    jucatori se intalnesc si urmeaza sa poarte o lupta, fieca-
                    re jucator isi calculeaza dmg-ul pe care trebuie sa-l dea 
                    in celalat iar apoi are loc "batalia" propriu zisa.

    4. Modificator: - este o clasa care preia datele de la un spell si adauga
                    bonusuri in functie de rasa sau terenul unde se desfasoara
                    lupta.
 - Pachetul intefete:
      Contine 3 interfete:
         1. Vraja reprezinta conturul unui spell, fiecare spell are caracteri-
         sticile acestei interfete.
         2. CONST reprezinta o interfata de constante globare cum ar fii viata
         fiecarui personaj sau viata bonus pe care trebuie sa o primeasca, am 
         ales aceasta implementare deoarece daca intr-un punct ne razgandim si
         vrem sa schibam un parametru o putem face foarte usor modificand con-
         stanta, fapt ce ajuta sa nu stricam codul cat si sa fie mai lizibil.
         3. MAGIC reprezinta o interfata cu constante, contine dmg-ul fieca-
         rui spell, lvlup al acestui spell cat si parametrii de la efecte
         over time, la fel ca si clasa CONST am ales implementarea unei 
         interfete cu constante pentru benficile pe care le am.


