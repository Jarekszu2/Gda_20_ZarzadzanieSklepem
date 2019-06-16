package packZarzadzanieSklepem;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println();

        Map<String, Zamowienie> mapZamWszyst = new HashMap<>();
        Map<String, Zamowienie> mapZamNiedo = new HashMap<>();
        Map<String, Zamowienie> mapDost = new HashMap<>();
        Map<String, Produkt> mapMagazynTest = new HashMap<>();
        Produkt pr1 = new Produkt("a", 1.0, 1);
        Produkt pr2 = new Produkt("b", 2.0, 2);
        mapMagazynTest.put(pr1.getNazwa(), pr1);
        mapMagazynTest.put(pr2.getNazwa(), pr2);
        Magazyn magazyn = new Magazyn(mapMagazynTest, mapZamWszyst, mapZamNiedo, mapDost);

        System.out.println();
        System.out.println(mapMagazynTest);

        System.out.println();
        System.out.println();
        magazyn.wydrukDoPlikuStanówMagazynowych();
    }
}
