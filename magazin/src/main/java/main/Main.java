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
        logger.info("Please log in");
        System.out.println("Username: ");
        String userInputUsername = scanner.next();
        if (utilizatorController.findUtilizator(userInputUsername)!= null){
            System.out.println("Intoruceti parola: ");
            int userInputPassword = scanner.nextInt();
            if (userInputPassword == utilizatorController.findUtilizator(userInputUsername).getParola()){
                System.out.println("V-ati logat cu succes! ");
            }else {
                logger.warning("Ati introdus o parola gresita!");
            }
        }else {
            logger.warning("Numele de utilizator nu exista!");
            System.out.println("Doriti sa creati un cont nou?");
            String userInputCreareContNou = scanner.next();
            if (userInputCreareContNou.equalsIgnoreCase("da")){
                creareUtilizator();
            }else {
                System.out.println("Va mai asteptam!");
            }


        }
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
                adaugareProduseLaProducator();
                break;
            }
            case 2: {
                stergereProducator();
                break;
            }
            case 3: {
                modifcareProducator();

                break;
            }
            case 4: {
                cautareProducator();
                break;
            }
            default: {
                System.out.println("Introduceti o optiune valida.");
                break;
            }
        }
    }

    private static void cautareProducator() {
        System.out.println("Introduceti id-ul producatorului pe care il cautati");
        int idProducatorCautat = scanner.nextInt();
        producatorController.findProducator(idProducatorCautat);
    }

    private static void modifcareProducator() {
        System.out.println("Introduceti id-ul producatorului pe care doriti sa il modificati");
        int idProducatorDeModificat = scanner.nextInt();
        Producator producatorGasit = producatorController.findProducator(idProducatorDeModificat);

        System.out.println("Ce anume vreti sa modificati la producatorul " + producatorGasit.getProducatorId());
        System.out.println("1. numele");
        System.out.println("2. produsele");
        int alegereModificare = scanner.nextInt();
        switch (alegereModificare) {
            case 1: {
                System.out.println("Introduceti noul nume");
                String noulNume = scanner.next();
                producatorGasit.setNume(noulNume);
                producatorController.updateProducator(producatorGasit);
                break;
            }
            //todo metoda prin care sa se modifice produsele producatorului
        }
//                        TODO : sa poata fi modificat orice field al producatorului
    }

    private static void stergereProducator() {
        System.out.println("Care producator doriti sa fie sters? \n introduceti id");
        int idProducatoruluiDeSters = scanner.nextInt();
        producatorController.deleteProducator(idProducatoruluiDeSters);
    }

    private static void adaugareProduseLaProducator() {
        Producator producator = new Producator();
        String numeProducator = scanner.next();
        producator.setNume(numeProducator);

        System.out.println("Cate produse vrei sa adaugi?");
        int alegeNrProduse = scanner.nextInt();
        List<Produs> produse = new ArrayList<Produs>();
        for (int i = 0; i < alegeNrProduse; i++) {
            System.out.println("Se creeaza produsul: " + (i + 1));
            Produs produs = new Produs();

            System.out.println("Introduceti numele produsului!");
            String numeProdus = scanner.next();
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
        int idUtilzatorCautat = scanner.nextInt();
        utilizatorController.findUtilizator(idUtilzatorCautat);
    }

    public static void actualizareUtilizator() {
        System.out.println("Introduceti id-ul utilizatorului pe care vreti sa-l modificati");
        int idUtilizatorDeModificat = scanner.nextInt();
        Utilizator utilizatorGasit = utilizatorController.findUtilizator(idUtilizatorDeModificat);

        System.out.println("Ce vreti sa modificati la utilizatorul "+ utilizatorGasit.getId());
        System.out.println("1. numele");
        System.out.println("2. prenumele");
        System.out.println("3. adresa");
        System.out.println("4. numarul de telefon");

        int optiuneModificareUtilizator = scanner.nextInt();

        switch (optiuneModificareUtilizator){
            case 1:{
                System.out.println("Introduceti noul nume");
                String noulNume = scanner.next();
                utilizatorGasit.setNume(noulNume);
                utilizatorController.updateUtilizator(utilizatorGasit);
            }
            case 2:{
                System.out.println("introduceti noul prenume");
                String noulPrenume = scanner.next();
                utilizatorGasit.setPrenume(noulPrenume);
                utilizatorController.updateUtilizator(utilizatorGasit);
            }
            case 3:{
                System.out.println("introduceti noua adresa");
                String adresaNoua = scanner.next();
                utilizatorGasit.setAdresa(adresaNoua);
                utilizatorController.updateUtilizator(utilizatorGasit);
            }
            case 4:{
                System.out.println("Introduceti noul numar de telefon");
                String nrTelefonNou = scanner.next();
                utilizatorGasit.setTelefon(nrTelefonNou);
                utilizatorController.updateUtilizator(utilizatorGasit);
            }
        }


    }

    public static void stergereUtilizator() {
        System.out.println("Introduceti id-ul utilizatorului pe care doriti sa-l stergeti");
        int idUtilizatorDeSters = scanner.nextInt();
        utilizatorController.deleteUtilizator(idUtilizatorDeSters);
    }

    public static void creareUtilizator() {
        Utilizator utilizator = new Utilizator();
        String numeUtilizator = scanner.next();
        utilizator.setNume(numeUtilizator);
        String prenumeUtilizator = scanner.next();
        utilizator.setPrenume(prenumeUtilizator);
        String adresa = scanner.next();
        utilizator.setAdresa(adresa);
        String nrTelefon = scanner.next();
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
        int idProdusCautat = scanner.nextInt();
        produsController.findProdus(idProdusCautat);
    }

    public static void modificareProdus() {
        System.out.println("introduceti id-ul produsului pe care vreti sa il modificati");
        int idProdusDeModificat = scanner.nextInt();
        Produs produsGasit = produsController.findProdus(idProdusDeModificat);

        System.out.println("Ce anume vreti sa modificati la produsul " + produsGasit.getNume());
        System.out.println("1. numele");
        System.out.println("2. pretul");
        System.out.println("3. cantitatea de pe stoc");

        int alegereModificare = scanner.nextInt();
        switch (alegereModificare) {
            case 1: {
                System.out.println("introduceti noul nume");
                String noulNume = scanner.next();
                produsGasit.setNume(noulNume);
                produsController.updateProdus(produsGasit);
                break;
            }
            case 2: {
                System.out.println("Introduceti noul pret al produslui " + produsGasit.getNume());
                double pretNou = scanner.nextDouble();
                produsGasit.setPret(pretNou);
                produsController.updateProdus(produsGasit);
                break;
            }
            case 3: {
                System.out.println("Introduceti noua cantitate de pe stoc al produsului: " + produsGasit.getNume());
                int cantitateNoua = scanner.nextInt();
                produsGasit.setCantitateStoc(cantitateNoua);
                produsController.updateProdus(produsGasit);
                break;
            }
        }


    }

    public static void stergereProdus() {
        System.out.println("Introduceti id-ul produsului pe care vreti sa-l stergeti");
        int idProdusDeSters = scanner.nextInt();
        produsController.deleteProdus(idProdusDeSters);
    }

    public static void salvareProdus() {
        Produs produs = new Produs();
        String numeProdus = scanner.next();
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

        Utilizator utilizator2 = new Utilizator();
        utilizator2.setNume("marius");
        utilizator2.setPrenume("cristi");
        utilizator2.setAdresa("Cluj, Romania");
        utilizator2.setTelefon("0715123456");
        utilizator2.setUsername("cristi");
        utilizator2.setParola(123456L);


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
        utilizator.setUsername("ianghel");
        utilizator.setParola(123456L);

        produsDao.create(produs);
        produsDao.create(produs1);
        producatorDao.create(producator);
        utilizatorDao.create(utilizator);
        utilizatorDao.create(utilizator2);
    }
}

