package controllers;

import modele.Utilizator;
import services.UtilizatorService;

import java.util.logging.Logger;

public class UtilizatorController {
    private static final Logger logger = Logger.getLogger(UtilizatorController.class.getName());
    UtilizatorService utilizatorService = new UtilizatorService();

    public Utilizator findUtilizator(String username){
        Utilizator utilizatorGasit = utilizatorService.getUtilizatorByUsername(username);
        return utilizatorGasit;
    }

    public boolean saveUtilizator(Utilizator utilizator) {
        logger.info("Utilizatorul " + utilizator.getNume() + " se salveaza...");

        return utilizatorService.saveUtilizator(utilizator);
    }

    public Utilizator findUtilizator(Long id) {
        Utilizator utilizatorGasit = utilizatorService.getUtilizatorById(id);
        logger.info("Utilizatorul gasit: " + utilizatorGasit.toString());

        return utilizatorGasit;
    }

    public boolean deleteUtilizator(Long id) {
        logger.info("Utilizatorul se sterge...");
        Utilizator UtilizatorCautat = utilizatorService.getUtilizatorById(id);
    boolean isUtilizatorDeleted = utilizatorService.deleteUtilizator(id);
        if (isUtilizatorDeleted) {
            logger.info("Utilizatorul a fost sters " + UtilizatorCautat.getNume());
        } else {
            logger.info("Eroare la stergerea utilizatorului " + UtilizatorCautat.getNume());
        }

        return isUtilizatorDeleted;
    }

    public boolean updateUtilizator(Utilizator utilizator) {
        boolean isUtilizatorUpdated = utilizatorService.updateUtilizator(utilizator);

        if (isUtilizatorUpdated) {
            logger.info("Utilizatorul " + utilizator.getId() + " a fost actualizat");
        } else {
            logger.info("Utilizatorul " + utilizator.getId() + " nu a fost actualizat!");
        }

        return isUtilizatorUpdated;
    }

}
