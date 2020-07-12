package services;

import dao.UtilizatorDao;
import modele.Utilizator;

import java.util.Iterator;
import java.util.List;

public class UtilizatorService {
    //    utilizator DAO
    UtilizatorDao dao = new UtilizatorDao();

//    metode CREATE/SAVE
    public boolean saveUtilizator(Utilizator utilizator) {
        Utilizator utilizatorSalvat = dao.create(utilizator);

        if (utilizatorSalvat != null) {
            System.out.println("Utilizatorul a fost salvat!");

            return true;
        }

        return false;
    }

    public void saveUtilizatori(List<Utilizator> list) {
        System.out.println("Se salveaza lista!");

        for (Utilizator utilizator : list) {
            Utilizator utilizatorSalvat = dao.create(utilizator);
        }
    }

//    METODE READ/FIND
    public Utilizator getUtilizatorById(Long id) {
        System.out.println("Se cauta utilizatorul " + id);

        Utilizator utilizatorGasit = dao.findById(id);

        return utilizatorGasit;
    }

// TODO - METODA PRIN CARE SE GASESTE UN UTILIZATOR DUPA ORICE CAMP

//    public Utilizator getUtilizator(String orice) {
//        System.out.println("Se cauta utilizatorul " + id);
//
//        Utilizator utilizatorGasit = dao.findById(id);
//
//        return utilizatorGasit;
//    }

    public List<Utilizator> getAll() {
        System.out.println("Se afiseaza toti utilizatorii!");

        List<Utilizator> list = dao.findAll();

        return list;
    }

//    METODE UPDATE
    public boolean updateUtilizator(Utilizator utilizator) {
        System.out.println("Se realizeaza update-ul utilizatorului!");

        boolean isUtilizatorUpdated = dao.update(utilizator);

        return isUtilizatorUpdated;
    }

//    METODE DELETE
    public boolean deleteUtilizator(Long id ) {
        boolean isUtilizatorDeleted = false;
        System.out.println("Se sterge utilizatorul!");
        Utilizator utilizator = dao.findById(id);
        if (utilizator!= null){
            isUtilizatorDeleted = dao.delete(utilizator);
        }else {
            System.out.println("Utilizatorul nu a fost gasit");
        }

        return isUtilizatorDeleted;
    }

    public Utilizator getUtilizatorByUsername(String username){
        System.out.println("Se cauta utilizatorul " + username + " ...");
        Utilizator utilizatorGasit = dao.findByUsername(username);
        return utilizatorGasit;
    }

}
