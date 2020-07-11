package dao;

import magazinConfig.MagazinConfig;
import modele.Utilizator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UtilizatorDao {

    public Utilizator create(Utilizator utilizator) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

//            cast la Utilizator deoarece astepta un Serializable
            Utilizator utilizatorSalvat = (Utilizator) session.save(utilizator);

            transaction.commit();
            session.close();

            return utilizatorSalvat;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Utilizatorul nu a fost salvat deoarece " + e.getMessage());
//            varianta 2 de afisare a exceptiei
//            e.printStackTrace();
        }

        return null;
    }

    public Utilizator findById(Long id) {
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();

            Utilizator utilizator = session.find(Utilizator.class, id);

            session.close();
            return utilizator;
        } catch (Exception e) {
            System.out.println("Utilizatorul " + id + " nu a fost gasit!");
        }

        return null;
    }

    public List<Utilizator> findAll() {
        Session session = MagazinConfig.getSessionFactory().openSession();

        List<Utilizator> utilizatori = (List<Utilizator>) session
                .createQuery("from utilizator").list();

        return utilizatori;
    }

    // TODO - METODA PRIN CARE SE GASESTE UN UTILIZATOR DUPA ORICE CAMP

    public boolean update(Utilizator utilizator) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(utilizator);

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Utilizatorul " + utilizator.getId() + " nu a fost modificat!");
        }

        return false;
    }

    public boolean delete(Utilizator utilizator) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(utilizator);

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Utilizatorul " + utilizator.getId() + " nu a fost sters!");
        }

        return false;
    }

}
