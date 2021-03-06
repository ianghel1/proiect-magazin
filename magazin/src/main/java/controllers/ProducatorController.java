package controllers;

import modele.Producator;
import services.ProducatorService;

import java.util.logging.Logger;

public class ProducatorController {


    private static final Logger logger = Logger.getLogger(controllers.ProducatorController.class.getName());
    ProducatorService producatorService = new ProducatorService();

    public boolean saveProducator(Producator producator) {
        logger.info("Producatorul " + producator.getNume() + " se salveaza...");

        return producatorService.saveProducator(producator);
    }

    public Producator findProducator(int id) {
        Producator producatorGasit = producatorService.getProducatorById(id);
        logger.info("Producatorul gasit: " + producatorGasit.toString());

        return producatorGasit;
    }

    public boolean deleteProducator(int id) {
        logger.info("Producatorul se sterge...");
        Producator producatorCautat = producatorService.getProducatorById(id);

        boolean isProducatorDeleted = producatorService.deleteProducator(id);

        if (isProducatorDeleted) {
            logger.info("Producatorul a fost sters " + producatorCautat.getNume());
        } else {
            logger.info("Eroare la stergerea producatorului " + producatorCautat.getNume());
        }

        return isProducatorDeleted;
    }


    public boolean updateProducator(Producator producator) {
        boolean isProducatorUpdated = producatorService.updateProducator(producator);

        if (isProducatorUpdated) {
            logger.info("Producatorul " + producator.getProducatorId() + " a fost actualizat");
        } else {
            logger.info("Producatorul " + producator.getProducatorId() + " nu a fost actualizat!");
        }

        return isProducatorUpdated;
    }

}