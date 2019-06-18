package packZarzadzanieSklepem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Zamowienie implements Comparable<Zamowienie> {
    private String numer;
    private Set<Produkt> produkts;
    private LocalDateTime dataZamowienia;
    private LocalDateTime dataDostarczenia;
    private String numerFaktury;
    private int opoznienie;

    public Zamowienie(String numer, Set<Produkt> produkts, LocalDateTime dataZamowienia) {
        this.numer = numer;
        this.produkts = produkts;
        this.dataZamowienia = dataZamowienia;
    }

    public String zapisDoPlikuNiedo() {
        return  "numer=" + numer + "\n" +
                "produkts=" + produkts.size() + "\n" +
                "dataZamowienia=" + dataZamowienia + "\n" +
                "dataDostarczenia=" + dataDostarczenia + "\n" +
                "numerFaktury=" + numerFaktury + "\n" +
                "opoznienie=" + opoznienie + "\n";
    }


    @Override
    public int compareTo(Zamowienie o) {
        return this.numer.compareTo(o.numer);
    }
}
