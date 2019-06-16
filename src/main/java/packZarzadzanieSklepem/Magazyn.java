package packZarzadzanieSklepem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Magazyn {
    private Map<String, Produkt> produkts;
    private Map<String, Zamowienie> zamowieniesWszystkie;
    private Map<String, Zamowienie> zamowieniesNiedostarczone;
    private Map<String, Zamowienie> dostawy;

    public void wydrukStanowMagazynowych() {
        Set<Map.Entry<String, Produkt>> entrySet = produkts.entrySet();
        int i = 1;
        for (Map.Entry<String, Produkt> stringProduktEntry : entrySet) {
            System.out.println(i + ". produkt: " + stringProduktEntry.getKey() + " ilość: " + stringProduktEntry.getValue().getIlosc());
            i++;
        }
    }

    public String wydrukDoPlikuStanówMagazynowych() {

        String zapisMagazynu = "";
        Collection<Produkt> kolekcja = produkts.values();
        List<Produkt> listaPr = new ArrayList<>(kolekcja);
        String[] tab = new String[listaPr.size()];
        for (int i = 0; i < listaPr.size(); i++) {
            tab[i] = listaPr.get(i).toStringZapis();
        }
        // System.out.println(Arrays.toString(tab));
        for (int i = 0; i < tab.length; i++) {
            zapisMagazynu += tab[i];
        }
        String zapis = zapisMagazynu.trim();
        System.out.println(zapisMagazynu);

        // Set<Map.Entry<String, Produkt>> entrySet = produkts.entrySet();
        // List<Map.Entry<String, Produkt>> listaEntry = new ArrayList<>(entrySet);
        // String zapisMagazynu = "";
        // String[] tabMag = new String[listaEntry.size()];
        // for (int i = 0; i < listaEntry.size(); i++) {
        //     tabMag[i] = "nazwa=" + listaEntry.get(i).getValue().getNazwa() + "\n" +
        //             "cena=" + listaEntry.get(i).getValue().getCena() + "\n" +
        //             "ilosc=" + listaEntry.get(i).getValue().getIlosc();
        // }
        // System.out.println(Arrays.toString(tabMag));
        // // for (Map.Entry<String, Produkt> stringProduktEntry : entrySet) {
        // //     zapisMagazynu = "nazwa=" + stringProduktEntry.getValue().getNazwa() + "\n" +
        // //     "cena=" + stringProduktEntry.getValue().getCena() + "\n" +
        // //     "ilosc=" + stringProduktEntry.getValue().getIlosc();
        // // }
        // for (int i = 0; i < tabMag.length; i++) {
        //     zapisMagazynu += tabMag[i];
        // }
        // System.out.println(zapisMagazynu);
         return zapis;
    }


    public void wydrukZamowienWszystkich() {
        Set<Map.Entry<String, Zamowienie>> entrySet = zamowieniesWszystkie.entrySet();
        int i = 1;
        for (Map.Entry<String, Zamowienie> stringZamowienieEntry : entrySet) {
            System.out.println(i + ". Nr " + stringZamowienieEntry.getKey());
            i++;
        }
    }

    public void wydrukZamowienNiedostarczonych() {
        Set<Map.Entry<String, Zamowienie>> entrySet = zamowieniesNiedostarczone.entrySet();
        int i = 1;
        for (Map.Entry<String, Zamowienie> stringZamowienieEntry : entrySet) {
            System.out.println(i + ". " + stringZamowienieEntry.getKey());
            i++;
        }
    }

    public void wydrukDostaw() {
        Set<Map.Entry<String, Zamowienie>> entrySet = dostawy.entrySet();
        int i = 1;
        for (Map.Entry<String, Zamowienie> stringZamowienieEntry : entrySet) {
            System.out.println(i + ". Nr zamówienia: " + stringZamowienieEntry.getKey() + ", nr faktury: " + stringZamowienieEntry.getValue().getNumerFaktury()
                    + ", data dostawy: " + stringZamowienieEntry.getValue().getDataDostarczenia() + ", ilość produktów: " + stringZamowienieEntry.getValue().getProdukts().size()
                    + ", opóźnienie: " + stringZamowienieEntry.getValue().getOpoznienie());
            i++;
        }

    }
}
