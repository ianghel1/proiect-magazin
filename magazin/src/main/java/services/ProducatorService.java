package services;

import dao.ProducatorDao;
import modele.Producator;

import java.util.List;

public class ProducatorService {
    ProducatorDao dao = new ProducatorDao();

    //    metode CREATE / SAVE
    public boolean saveProducator(Producator producator) {
         dao.create(producator);

        if (producator != null) {
            System.out.println("Producatorul " + producator.getNume()
                    + " a fost salvat!");
            return true;
        }
        return false;
    }

    public void saveProducatori(List<Producator> list) {
        System.out.println("Se salveaza lista!");

        for (Producator producator : list) {
            dao.create(producator);
        }
    }

//    METODE READ / FIND/

    public Producator getProducatorById(int id) {
        System.out.println("se cauta producator " + id);

        Producator producatorGasit = dao.findById(id);

        return producatorGasit;
    }

    public List<Producator> getAll() {
        System.out.println("Se afiseaza toti producatorii!");
        List<Producator> list = dao.findAll();

        return list;
    }

    //    METODE UPDATE
    public boolean updateProducator(Producator producator) {
        System.out.println("Se realizeaza update-ul producatorului");
        boolean isProducatorUpdated = dao.update(producator);

        return isProducatorUpdated;
    }

    //METODE DELETE
    public boolean deleteProducator(int id) {
        boolean isProducatorDeleted = false;
        System.out.println("Se sterge producatorul cu id-ul: " + id);
        Producator producator = dao.findById(id);
        if (producator != null) {
           isProducatorDeleted = dao.delete(producator);
        }else {
            System.out.println("Utilizatorul nu a fost gasit!");
        }


        return isProducatorDeleted;
    }
}
