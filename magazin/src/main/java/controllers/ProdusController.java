package controllers;

import modele.Produs;
import services.ProdusService;

import java.util.List;
import java.util.logging.Logger;

public class ProdusController {

    private static final Logger logger = Logger.getLogger(controllers.ProdusController.class.getName());
    ProdusService produsService = new ProdusService();

    public boolean saveProdus(Produs produs) {
        logger.info("Produsul " + produs.getNume() + " se salveaza...");

        return produsService.saveProdus(produs);
    }

    public boolean saveProduse(List<Produs> produse) {
        logger.info("Se salveaza produsele");
        return produsService.saveProduse(produse);
    }

    public Produs findProdus(Long id) {
        Produs produsGasit = produsService.getProdusById(id);
        logger.info("Produsul gasit: " + produsGasit.toString());

        return produsGasit;
    }


    public List<Produs> getAll() {
        logger.info("lista tuturor produselor: ");
        List<Produs> produse = produsService.getAll();
        return produse;
    }


    public boolean deleteProdus(Long id) {
        logger.info("Produsul se sterge...");
        Produs produsCautat = produsService.getProdusById(id);
        boolean isProdusDeleted = produsService.deleteProdus(id);
        if (isProdusDeleted) {
            logger.info("Produsul a fost sters" + produsCautat.getNume());
        } else {
            System.out.println("Eroare la stergerea produsului");
        }
        return isProdusDeleted;
    }

    public boolean updateProdus(Produs produs) {
        boolean isProdusUpdated = produsService.updateProdus(produs);

        if (isProdusUpdated) {
            logger.info("Produsul " + produs.getId() + " a fost actualizat");
        } else {
            logger.info("Produsul " + produs.getId() + " nu a fost actualizat!");
        }

        return isProdusUpdated;
    }
}
