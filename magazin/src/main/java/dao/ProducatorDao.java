package dao;

import magazinConfig.MagazinConfig;
import modele.Producator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProducatorDao {

    public void create(Producator producator) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

//            cast la Produs deoarece astepta un Serializable
             session.save(producator);

            transaction.commit();
            session.close();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Producatorul nu a fost salvat");
        }

    }

    public Producator findById(int id) {
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            Producator producator = session.find(Producator.class, id);
            session.close();
            return producator;
        } catch (Exception e) {
            System.out.println("Producatorul " + id + " nu a fost gasit!");
        }
        return null;
    }

    public List<Producator> findAll() {
        Session session = MagazinConfig.getSessionFactory().openSession();

        List<Producator> producatori = (List<Producator>) session.
                createQuery("from producator").list();
        return producatori;
    }

    public boolean update(Producator producator) {
        Transaction transaction = null;

        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(producator);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Producatorul " + producator.getProducatorId() + " nu a fost modificat");
        }
        return false;
    }

    public boolean delete(Producator producator) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(producator);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Producatorul " + producator.getProducatorId() + " nu a fost sters");

        }
        return false;
    }

}