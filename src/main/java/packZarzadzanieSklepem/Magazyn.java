package packZarzadzanieSklepem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;
import java.util.Set;

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

    public  void wydrukZamowienWszystkich() {
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
