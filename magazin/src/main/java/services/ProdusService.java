package services;

import dao.ProdusDao;
import modele.Producator;
import modele.Produs;

import java.util.ArrayList;
import java.util.List;

public class ProdusService {
    //    produs DAO
    ProdusDao dao = new ProdusDao();

    //    metode CREATE/SAVE
    public boolean saveProdus(Produs produs) {
        Produs produsSalvat = dao.create(produs);

        if (produsSalvat != null) {
            System.out.println("Produsul " + produsSalvat.getNume()
                    + " a fost salvat!");

            return true;
        }

        return false;
    }

    public boolean saveProduse(List<Produs> list) {
        List<Produs> produse =new ArrayList <Produs>();
        System.out.println("Se salveaza lista!");
        for (Produs produs : list) {
            Produs produsSalvat = dao.create(produs);
            produse.add(produsSalvat);
        }
        return produse.size() == list.size();
    }

    //    METODE READ/FIND
    public Produs getProdusById(Long id) {
        System.out.println("Se cauta produs " + id);

        Produs produsGasit = dao.findById(id);

        return produsGasit;
    }

    public List<Produs> getAll() {
        System.out.println("Se afiseaza toate produsele!");

        List<Produs> list = dao.findAll();

        return list;
    }

    //    METODE UPDATE
    public boolean updateProdus(Produs produs) {
        System.out.println("Se realizeaza update-ul produsului!");

        boolean isProdusUpdated = dao.update(produs);

        return isProdusUpdated;
    }

    //    METODE DELETE
    public boolean deleteProdus(Long id ) {
        boolean isProdusDeleted = false;
        System.out.println("Se sterge produsul..");
        Produs produs = dao.findById(id);
        if (produs!=null){
            isProdusDeleted = dao.delete(produs);
        }else {
            System.out.println("Produsul nu a fost gasit");
        }
        return isProdusDeleted;
    }
}



