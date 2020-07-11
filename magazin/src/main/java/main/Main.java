package main;

import controllers.ProducatorController;
import controllers.ProdusController;
import controllers.UtilizatorController;
import dao.ProducatorDao;
import dao.ProdusDao;
import dao.UtilizatorDao;
import modele.Producator;
import modele.Produs;
import modele.Utilizator;
import services.ProducatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private static ProducatorController producatorController = new ProducatorController();
    private static UtilizatorController utilizatorController = new UtilizatorController();
    private static ProdusController produsController = new ProdusController();

    public static void main(String[] args) {


        populate();

        logger.info("Bun Venit!");
        System.out.println("Cu ce vrei sa lucrezi?");
        System.out.println("1. Producatori");
        System.out.println("2. Utilizatori");
        System.out.println("3. Produse");

        int alegere = scanner.nextInt();

        switch (alegere) {
            case 1: {
                alegereProducatori();
                break;
            }
            case 2: {
                alegereUtilizatori();
                break;
            }
            case 3: {
                alegereProduse();
                break;
            }
        }
    }

    public static void alegereProducatori() {


        System.out.println("Alegeti operatia dorita: ");
        System.out.println("1. Salvare producator");
        System.out.println("2. Stergere producator");
        System.out.println("3. Actualizare producator");
        System.out.println("4. Gasire Utilizator");

        int alegereProducator = scanner.nextInt();
        switch (alegereProducator) {
            case 1: {
                Producator producator = new Producator();
                String numeProducator = scanner.nextLine();
                producator.setNume(numeProducator);

                System.out.println("Cate produse vrei sa adaugi?");
                int alegeNrProduse = scanner.nextInt();
                List<Produs> produse = new ArrayList<Produs>();
                for (int i = 0; i < alegeNrProduse; i++) {
                    System.out.println("Se creeaza produsul: " + (i + 1));
                    Produs produs = new Produs();

                    System.out.println("Introduceti numele produsului!");
                    String numeProdus = scanner.nextLine();
                    produs.setNume(numeProdus);

                    System.out.println("Introduceti pretul produsului");
                    double pretProdus = scanner.nextDouble();
                    produs.setPret(pretProdus);

                    System.out.println("Introduceti cantitatea stocului");
                    int cantitateStoc = scanner.nextInt();
                    produs.setCantitateStoc(cantitateStoc);

                    produse.add(produs);

                }

                producator.setProduse(produse);
                break;
            }
            case 2: {
                System.out.println("Care producator doriti sa fie sters? \n introduceti id");
                Long idProducatoruluiDeSters = scanner.nextLong();
                producatorController.deleteProducator(idProducatoruluiDeSters);
                break;
            }
            case 3: {
                System.out.println("Alegeti producatorul pe care vreti sa-l modificati");
                System.out.println("Introduceti id-ul producatorului: ");
                Long idProducatorDeModificat = scanner.nextLong();
                Producator producatorGasit = producatorController.findProducator(idProducatorDeModificat);

//                        TODO : sa poata fi modificat orice field al producatorului
                System.out.println("Introduceti noul nume");
                String noulNume = scanner.nextLine();
                producatorGasit.setNume(noulNume);
                producatorController.updateProducator(producatorGasit);
                break;
            }
            case 4: {
                System.out.println("Introduceti id-ul producatorului pe care il cautati");
                Long idProducatorCautat = scanner.nextLong();
                producatorController.findProducator(idProducatorCautat);
                break;
            }
            default: {
                System.out.println("Introduceti o optiune valida.");
                break;
            }
        }
    }

    public static void alegereUtilizatori() {
        System.out.println("Ce operatie doriti sa efectuati?");
        System.out.println("1. Salvare utilizator");
        System.out.println("2. Stergere utilizator");
        System.out.println("3. Actualizare utilizator");
        System.out.println("4. Gasire utilizator");

        int alegereOperatie = scanner.nextInt();

        switch (alegereOperatie) {
            case 1: {
                creareUtilizator();
                break;
            }
            case 2: {
                stergereUtilizator();
                break;
            }
            case 3: {
                actualizareUtilizator();
                break;
            }
            case 4: {
                gasireUtilizator();
                break;
            }
            default: {
                System.out.println("Introduceti o optiune valida.");
                break;
            }
        }
    }

    public static void gasireUtilizator() {
        System.out.println("Introduceti id-ul Utilizatorului pe care il cautati");
        Long idUtilzatorCautat = scanner.nextLong();
        utilizatorController.findUtilizator(idUtilzatorCautat);
    }

    public static void actualizareUtilizator() {
        System.out.println("Introduceti id-ul utilizatorului pe care vreti sa-l modificati");
        Long idUtilizatorDeModificat = scanner.nextLong();
        Utilizator utilizatorGasit = utilizatorController.findUtilizator(idUtilizatorDeModificat);
        System.out.println("Introduceti noul nume");
        String noulNume = scanner.nextLine();
        utilizatorGasit.setNume(noulNume);
        utilizatorController.updateUtilizator(utilizatorGasit);
    }

    public static void stergereUtilizator() {
        System.out.println("Introduceti id-ul utilizatorului pe care doriti sa-l stergeti");
        Long idUtilizatorDeSters = scanner.nextLong();
        utilizatorController.deleteUtilizator(idUtilizatorDeSters);
    }

    public static void creareUtilizator() {
        Utilizator utilizator = new Utilizator();
        String numeUtilizator = scanner.nextLine();
        utilizator.setNume(numeUtilizator);
        String prenumeUtilizator = scanner.nextLine();
        utilizator.setPrenume(prenumeUtilizator);
        String adresa = scanner.nextLine();
        utilizator.setAdresa(adresa);
        String nrTelefon = scanner.nextLine();
        utilizator.setTelefon(nrTelefon);

        utilizatorController.saveUtilizator(utilizator);
    }


    public static void alegereProduse() {
        System.out.println("Ce operatie doriti sa efectuati?");
        System.out.println("1. Salvare produs");
        System.out.println("2. Stergere produs");
        System.out.println("3. Actualizare produs");
        System.out.println("4. Gasire produs");

        int alegere = scanner.nextInt();
        switch (alegere) {
            case 1: {
                salvareProdus();
                break;
            }
            case 2: {
                stergereProdus();
                break;
            }
            case 3: {
                modificareProdus();
                break;
            }
            case 4: {
                gasireProdus();
                break;
            }
        }


    }

    public static void gasireProdus() {
        System.out.println("Introduceti id-ul produsului cautat");
        Long idProdusCautat = scanner.nextLong();
        produsController.findProdus(idProdusCautat);
    }

    public static void modificareProdus() {
        System.out.println("introduceti id-ul produsului pe care vreti sa il modificati");
        Long idProdusDeModificat = scanner.nextLong();
        Produs produsGasit = produsController.findProdus(idProdusDeModificat);
        System.out.println("introduceti noul nume");
        String noulNume = scanner.nextLine();
        produsGasit.setNume(noulNume);
        produsController.updateProdus(produsGasit);
    }

    public static void stergereProdus() {
        System.out.println("Introduceti id-ul produsului pe care vreti sa-l stergeti");
        Long idProdusDeSters = scanner.nextLong();
        produsController.deleteProdus(idProdusDeSters);
    }

    public static void salvareProdus() {
        Produs produs = new Produs();
        String numeProdus = scanner.nextLine();
        produs.setNume(numeProdus);
        double pretProdus = scanner.nextDouble();
        produs.setPret(pretProdus);
        int cantitateStoc = scanner.nextInt();
        produs.setCantitateStoc(cantitateStoc);

        produsController.saveProdus(produs);
    }


    public static void populate() {
        ProducatorDao producatorDao = new ProducatorDao();
        ProdusDao produsDao = new ProdusDao();
        UtilizatorDao utilizatorDao = new UtilizatorDao();
        Producator producator = new Producator();

        producator.setNume("nike");

        Produs produs = new Produs();
        produs.setNume("shirt");
        produs.setPret(60.99);
        produs.setCantitateStoc(20);

        Produs produs1 = new Produs();
        produs1.setNume("shoes");
        produs1.setPret(99.99);
        produs1.setCantitateStoc(10);

        List<Produs> produse = new ArrayList<Produs>();
        produse.add(produs);
        produse.add(produs1);
        producator.setProduse(produse);

        Utilizator utilizator = new Utilizator();
        utilizator.setNume("ionut");
        utilizator.setPrenume("anghel");
        utilizator.setAdresa("Bucuresti, Romania");
        utilizator.setTelefon("0715123456");

        produsDao.create(produs);
        produsDao.create(produs1);
        producatorDao.create(producator);
        utilizatorDao.create(utilizator);


    }


}

