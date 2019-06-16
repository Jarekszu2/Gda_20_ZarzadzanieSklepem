package packZarzadzanieSklepem;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        Map<String, Zamowienie> mapaZamowienWszystkich = new HashMap<>();
        Map<String, Zamowienie> mapaZamowienNiedostarczonych = new HashMap<>();
        Map<String, Zamowienie> mapaDostaw = new HashMap<>();
        Map<String, Produkt> mapaProduktów = new HashMap<>();
        Magazyn magazyn = new Magazyn(mapaProduktów, mapaZamowienWszystkich, mapaZamowienNiedostarczonych, mapaDostaw);
//        magazyn.

        System.out.println("Aplikacja do zarządzania zamówieniami w sklepie spożywczym.");

        System.out.println();
        System.out.println("Wybierz komendę:\na: zamówienie\nb: listowanie zamówień\nc: realizacja zamówienia(rejestracja dostawy)" +
                "\nd: listowanie dostaw\ne: listowanie produktów\nf: zapis do pliku\ng: wczytanie danych z pliku\nh: sprzedaż" +
                "\nw: koniec pracy");

        Scanner scanner = new Scanner(System.in);
        char komenda = 'a';
        do {
            System.out.println();
            System.out.println("Wybierz komendę: a(zamówienie), b(lista zamówień), c(dostawa), d(lista dostaw), e(lista produktów)");
            System.out.println("                 f(zapis do pliku), g(wczytanie z pliku), h(sprzedaż), w(koniec pracy)");
            komenda = scanner.next().charAt(0);

            switch (komenda) {
                case 'a':
                    Set<Produkt> setZamowionychTowarow = new HashSet<>();
                    System.out.println("Podaj ilość zamawianych towarów.");
                    boolean flagInt = false;
                    int iloscZamawianychTowarowTemp = 0;
                    int iloscZamawianychTowarow = 0;
                    do {
                        while (!scanner.hasNextInt()) {
                            System.out.println("To nie jest liczba!");
                            scanner.next();
                        }
                        iloscZamawianychTowarowTemp = scanner.nextInt();
                        if (iloscZamawianychTowarowTemp > 0) {
                            iloscZamawianychTowarow = iloscZamawianychTowarowTemp;
                            flagInt = true;
                        } else {
                            System.out.println("Ilość zamawianego towaru musi być > 0!");
                        }
                    } while (!flagInt);

                    for (int i = 0; i < iloscZamawianychTowarow; i++) {

                        System.out.println("Podaj nazwę towaru: " + (i + 1));
                        String nazwaTowaru = scanner.next();

                        System.out.println("Podaj cenę towaru:");
                        boolean flagDouble = false;
                        double wprowadzonaWartoscDouble = 0.0;
                        double cenaTowaru = 0.0;
                        do {
                            while (!scanner.hasNextDouble()) {
                                System.out.println("To nie jest liczba!");
                                scanner.next();
                            }
                            wprowadzonaWartoscDouble = scanner.nextDouble();
                            if (wprowadzonaWartoscDouble <= 0) {
                                System.out.println("Cena towaru nie może być ujemna ani równa 0!");
                            } else {
                                cenaTowaru = wprowadzonaWartoscDouble;
                                flagDouble = true;
                            }
                        } while (!flagDouble);

                        System.out.println("Podaj ilość towaru:");
                        boolean flagIntIlosc = false;
                        int wprowadzonaWartoscInt = 0;
                        int iloscTowaru = 0;
                        do {
                            while (!scanner.hasNextInt()) {
                                System.out.println("To nie jest liczba!");
                                scanner.next();
                            }
                            wprowadzonaWartoscInt = scanner.nextInt();

                            if (wprowadzonaWartoscInt <= 0) {
                                System.out.println("Ilość towaru nie może być ujemna ani równa 0!");
                            } else {
                                iloscTowaru = wprowadzonaWartoscInt;
                                flagIntIlosc = true;
                            }
                        } while (!flagIntIlosc);

                        Produkt produkt = new Produkt(nazwaTowaru, cenaTowaru, iloscTowaru);
                        setZamowionychTowarow.add(produkt);
                    }
                    System.out.println("Zamówiono:");
                    for (Produkt element : setZamowionychTowarow) {
                        System.out.println(element.toString3());
                    }

                    LocalDateTime localDateTimeZamowienie = LocalDateTime.now();

                    boolean czyZnalazlemNieistniejacyNumer = false;
                    boolean istniejeNumerZamowienia = false;
                    String nrZamowienia = "";
                    do {
                        Random random = new Random();
                        int nrRandom = random.nextInt(9999);
                        String stringRandom = String.valueOf(nrRandom);
                        String nrZamowieniaTemp = "GD-" + stringRandom;

                        czyZnalazlemNieistniejacyNumer = !mapaZamowienWszystkich.containsKey(nrZamowieniaTemp);
                        if(czyZnalazlemNieistniejacyNumer){
                            nrZamowienia = nrZamowieniaTemp;
                        }
                    } while (!czyZnalazlemNieistniejacyNumer);

                    Zamowienie zamowienie = new Zamowienie(nrZamowienia, setZamowionychTowarow, localDateTimeZamowienie);

                    mapaZamowienWszystkich.put(nrZamowienia, zamowienie);
                    mapaZamowienNiedostarczonych.put(nrZamowienia, zamowienie);

                    System.out.println();
                    System.out.println("Złożono zamówienie o nr: " + nrZamowienia);
                    break;
                case 'b':
                    System.out.println("Lista niedostarczonych zamówień.");
                    magazyn.wydrukZamowienNiedostarczonych();
                    break;
                case 'c':
                    System.out.println("Realizacja zamówienia/rejestracja dostawy.");
                    System.out.println();
                    System.out.println("Dodaj dostawę.");

//                  Sprawdzam, czy są jakieś zamówienia w mapie zamówień
                    if (mapaZamowienNiedostarczonych.size() != 0) {
//                      Sprawdzam, czy jest zamówienie o podanym nr
                        boolean flagNrZamowieniaDoRejestracji = false;
                        String nrZamowieniaDoRejestracjiTemp = "";
                        String nrZamowieniaDoRejestracji = "";
                        do {
                            System.out.println();
                            System.out.println("Podaj nr zamówienia:");
                            nrZamowieniaDoRejestracjiTemp = scanner.next();
                            if (mapaZamowienNiedostarczonych.containsKey(nrZamowieniaDoRejestracjiTemp)) {
                                nrZamowieniaDoRejestracji = nrZamowieniaDoRejestracjiTemp;
                                flagNrZamowieniaDoRejestracji = true;
                            } else {
                                System.out.println("Nie ma takiego nr zamówienia!");
                            }
                        } while (!flagNrZamowieniaDoRejestracji);
//                        System.out.println(nrZamowieniaDoRejestracji);
//                      Wczytano poprawny nr zamówienia

//                        Oznaczenie czsu dostawy i nr faktury zakupowej
                        Zamowienie zamowienieDoRejestracji = mapaZamowienNiedostarczonych.get(nrZamowieniaDoRejestracji);

//                        Nadanie numeru faktury zakupowej dla zamówienia
                        String nrFakturyZakupowejDoZamowienia = "FVZ-" + nrZamowieniaDoRejestracji;
                        System.out.println("Nr faktuty zakupowej do zamówienia: " + nrFakturyZakupowejDoZamowienia);
                        zamowienieDoRejestracji.setNumerFaktury(nrFakturyZakupowejDoZamowienia);

//                        Oznaczenie czasu rejestracji i opóźnienia
//                        Oznaczenie czasu rejestracji dostawy
                        System.out.println();
                        System.out.println("Wprowadzenie daty rejestracji: a(automatycznie), r(ręcznie): a/r?");
                        char rodzajRejestracji = 'b';
                        do {
                            System.out.println();
                            rodzajRejestracji = scanner.next().charAt(0);
                            if (rodzajRejestracji != 'a' && rodzajRejestracji != 'r') {
                                System.out.println("Wprowadź a lub r!");
                            }
                        } while ((rodzajRejestracji != 'a' && rodzajRejestracji != 'r'));
                        System.out.println(rodzajRejestracji);

                        LocalDateTime localDateTimeRejestracja = null;
                        if (rodzajRejestracji == 'a') {
                            localDateTimeRejestracja = LocalDateTime.now();
                        } else {
                            System.out.println();
                            System.out.println("Podaj datę i czas rejestracji w formacie: dd-MM-yyyy-HH-mm-ss");
                            boolean poprawnaData = false;
                            String dataRegex = "";
                            do {
                                try {
                                    dataRegex = scanner.next("\\d{2}-\\d{2}-\\d{4}-\\d{2}-\\d{2}-\\d{2}$");
                                    poprawnaData = true;
                                } catch (InputMismatchException ime) {
                                    System.err.println("Zły format!");
                                    scanner.next();
                                }
                            } while (!poprawnaData);
                            System.out.println(dataRegex);
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
                            localDateTimeRejestracja = LocalDateTime.parse(dataRegex, dateTimeFormatter);
                        }


//                        System.out.println(localDateTimeRejestracja);
                        zamowienieDoRejestracji.setDataDostarczenia(localDateTimeRejestracja);

//                        Oznaczenie opóźnienia
                        localDateTimeZamowienie = zamowienieDoRejestracji.getDataZamowienia();
                        Timestamp timestampZamowienia = Timestamp.valueOf(localDateTimeZamowienie);
                        System.out.println("Data i czas zamówienia: " + timestampZamowienia);

                        Timestamp timestampRejestracja = Timestamp.valueOf(localDateTimeRejestracja);
                        System.out.println("Data i czas dostawy: " + timestampRejestracja);

                        long roznicaCzasu = timestampRejestracja.getTime() - timestampZamowienia.getTime();
//                        System.out.println(roznicaCzasu);
                        int opoznienieTemp = (int) roznicaCzasu / 1000;
                        int opoznienie = 0;
                        if (opoznienieTemp <= 60) {
                            opoznienie = 0;
                        } else {
                            opoznienie = (opoznienieTemp - 60);
                        }
                        System.out.println("opóźnienie: " + opoznienie);
                        zamowienieDoRejestracji.setOpoznienie(opoznienie);

//                      Wczytywanie kolejnych produktów dla sprawdzenia kompletności dostawy
                        Set<Produkt> setProduktowDoRejestracji = zamowienieDoRejestracji.getProdukts(); // set produktów zamówionych
                        Set<Produkt> setProduktowZarejestrowanych = new HashSet<>(); // set produktów dostarczonych
                        String odpPrzyRejestracjiProduktu = "";
                        for (Produkt element : setProduktowDoRejestracji) {
                            System.out.println();
                            System.out.println("Czy w dostawie znajduje się: produkt " + element.getNazwa() + " cena " + element.getCena() +
                                    " ilość " + element.getIlosc() + "?");
                            boolean flagPrzyRejestracjiProduktu = false;
                            char znakPrzyRejestracjiProduktu = 'a';
                            do {
                                // System.out.println();
                                System.out.println("Dostarczono zgodnie z zamówieniem: t/n?");
                                znakPrzyRejestracjiProduktu = scanner.next().charAt(0);
                                if (znakPrzyRejestracjiProduktu == 't') {
                                    // oznaczam produkt jako dostarczony
                                    element.setCzyDostarczony(true);

                                    setProduktowZarejestrowanych.add(element);

//                                    Dodanie dostarczonego produktu do mapy produktów
                                    if (mapaProduktów.containsKey(element.getNazwa())) {
                                        int iloscProduktWwMapie = mapaProduktów.get(element.getNazwa()).getIlosc();
                                        int ilośćProduktuWDostawie = element.getIlosc();
                                        int lacznaIloscProduktu = iloscProduktWwMapie + ilośćProduktuWDostawie;
                                        if (znakPrzyRejestracjiProduktu == 't') {
                                            element.setIlosc(lacznaIloscProduktu);
                                        }
                                        mapaProduktów.put(element.getNazwa(), element);
                                    } else {
                                        mapaProduktów.put(element.getNazwa(), element);
                                    }

                                    flagPrzyRejestracjiProduktu = true;
                                } else if (znakPrzyRejestracjiProduktu == 'n') {
                                    flagPrzyRejestracjiProduktu = true;
                                }
                            } while (!flagPrzyRejestracjiProduktu);
                        }
                        System.out.println("Przyjęto dostawę nr: " + nrZamowieniaDoRejestracji);

                        Zamowienie zamowienieZarejestrowane = new Zamowienie(zamowienieDoRejestracji.getNumer(), setProduktowZarejestrowanych, zamowienieDoRejestracji.getDataZamowienia(), zamowienieDoRejestracji.getDataDostarczenia(), zamowienieDoRejestracji.getNumerFaktury(), zamowienieDoRejestracji.getOpoznienie());
                        mapaDostaw.put(nrZamowieniaDoRejestracji, zamowienieZarejestrowane);
                        mapaZamowienNiedostarczonych.remove(nrZamowieniaDoRejestracji);
//                        System.out.println(mapaDostaw);
                    } else {
                        System.out.println();
                        System.out.println("Brak zamówień do rejestracji!");
                    }
                    break;
                case 'd':
                    System.out.println("Lista dostaw.");
                    magazyn.wydrukDostaw();
                    break;
                case 'e':
                    System.out.println("Lista produktów w magazynie:");
                    magazyn.wydrukStanowMagazynowych();
                    break;
                case 'f':
                    System.out.println("Zapis do pliku.");
                    break;
                case 'g':
                    System.out.println("Wczytanie danych z pliku.");
                    break;
                case 'h':
                    System.out.println("Sprzedaż.");
                    break;
                default:
                    if (komenda != 'w') {
                        System.out.println("Dostępne opcje: a, b, c, d, e, f, g, h lub w!");
                    }
            }
        } while (komenda != 'w');
    }
}
