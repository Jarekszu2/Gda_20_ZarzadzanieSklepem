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
        for (int i = 0; i < tab.length; i++) {
            zapisMagazynu += tab[i];
        }
        String zapis = zapisMagazynu.trim().trim();
        System.out.println(zapisMagazynu);
        return zapis;
    }

    public String wydrukDoPlikuProdZamNiedo() {
        String zapisListaProdNTemp = "";
        Collection<Zamowienie> kolekcjaZN = zamowieniesNiedostarczone.values();
        List<Zamowienie> listaZN = new ArrayList<>(kolekcjaZN);
        Collections.sort(listaZN);
        List<Produkt> listaProduktowZN = new ArrayList<>();
        for (int i = 0; i < listaZN.size(); i++) {
            Set<Produkt> setPZN = listaZN.get(i).getProdukts();
            for (Produkt produkt : setPZN) {
                listaProduktowZN.add(produkt);
            }
        }
        String[] tab = new String[listaProduktowZN.size()];
        for (int i = 0; i < listaProduktowZN.size(); i++) {
            tab[i] = listaProduktowZN.get(i).toStringZapis();
        }
        for (int i = 0; i < tab.length; i++) {
            zapisListaProdNTemp += tab[i];
        }
        String zapis = zapisListaProdNTemp.trim();
        return zapis;
    }

    public String wydrukDoPlikuZamowienNiedostarczonych() {
        String zapisZamNiedostTemp = "";
        Collection<Zamowienie> kolekcjaNiedo = zamowieniesNiedostarczone.values();
        List<Zamowienie> listaZamNiedo = new ArrayList<>(kolekcjaNiedo);
        Collections.sort(listaZamNiedo);
        String[] tabZN = new String[listaZamNiedo.size()];
        for (int i = 0; i < listaZamNiedo.size(); i++) {
            tabZN[i] = listaZamNiedo.get(i).zapisDoPlikuNiedo();
        }
        for (int i = 0; i < tabZN.length; i++) {
            zapisZamNiedostTemp += tabZN[i];
        }
        String zapisZamNiedost = zapisZamNiedostTemp.trim();
        return zapisZamNiedost;
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
