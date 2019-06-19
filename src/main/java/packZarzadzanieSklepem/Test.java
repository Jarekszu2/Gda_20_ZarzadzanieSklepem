package packZarzadzanieSklepem;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println();

        Map<String, Zamowienie> mapZamWszyst = new HashMap<>();
        Map<String, Zamowienie> mapZamNiedo = new HashMap<>();
        Map<String, Zamowienie> mapDost = new HashMap<>();
        Map<String, Produkt> mapMagazynTest = new HashMap<>();
        Produkt pr1 = new Produkt("a", 1.0, 1);
        Produkt pr2 = new Produkt("b", 2.0, 2);
        Produkt pr3 = new Produkt("b", 2.0, 2);
        String nr1 = "1";
        String nr2 = "2";
        Set<Produkt> set1 = new HashSet<>(Arrays.asList(pr1, pr2));
        Set<Produkt> set2 = new HashSet<>(Arrays.asList(pr3));
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now();
        LocalDateTime localDateTime3 = LocalDateTime.now();
        LocalDateTime localDateTime4 = LocalDateTime.now();
        String nrFakt1 = "f1";
        String nrFakt2 = "f2";
        int opz1 = 1;
        int opz2 = 2;
        Zamowienie zam1 = new Zamowienie(nr1, set1, localDateTime1,localDateTime2, nrFakt1, opz1);
        Zamowienie zam2 = new Zamowienie(nr2, set2, localDateTime3,localDateTime4, nrFakt2, opz2);
        mapMagazynTest.put(pr1.getNazwa(), pr1);
        mapMagazynTest.put(pr2.getNazwa(), pr2);

        mapZamNiedo.put(nr1, zam1);
        mapZamNiedo.put(nr2, zam2);
        Magazyn magazyn = new Magazyn(mapMagazynTest, mapZamWszyst, mapZamNiedo, mapDost);

        System.out.println();
        //System.out.println(mapZamNiedo);

        System.out.println();
        System.out.println();
        String s = magazyn.wydrukDoPlikuZamowienNiedostarczonych();
        System.out.println(s);
        System.out.println();
        String z = magazyn.wydrukDoPlikuProdZamNiedo();
        System.out.println(z);


        System.out.println();List<Integer> listaInt = new ArrayList<>(Arrays.asList(2, 1, 3));
        List<Integer> listaI = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> tabI = new ArrayList<>();
        for (int i = 0; i < listaInt.size(); i++) {
            for (int j = 0; j < listaInt.get(i); j++) {
                tabI.add(listaI.get(i));
            }
        }
        System.out.println(tabI);

        System.out.println();
        System.out.println("Wjazd:");
        LocalDateTime czasWjazd = LocalDateTime.now();
        System.out.println(czasWjazd);
        Timestamp timestamp = Timestamp.valueOf(czasWjazd);
        System.out.println(timestamp);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Wyjazd:");
        LocalDateTime czasWyjazd = LocalDateTime.now();
        System.out.println(czasWyjazd);
        Timestamp timestamp2 = Timestamp.valueOf(czasWyjazd);
        System.out.println(timestamp2);

        long roznica = timestamp2.getTime() - timestamp.getTime();
        System.out.println();
        System.out.println("różnica: " + roznica);


    }
}
