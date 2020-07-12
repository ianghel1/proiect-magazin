package dao;

import magazinConfig.MagazinConfig;
import modele.Produs;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProdusDao {

    public void create(Produs produs) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

//            doar salveaza
             session.save(produs);

            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Produsul nu a fost salvat deoarece " + e.getMessage());
//            varianta 2 de afisare a exceptiei
//            e.printStackTrace();
        }

    }

    public Produs findById(int id) {
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();

            Produs produs = session.find(Produs.class, id);

            session.close();
            return produs;
        } catch (Exception e) {
            System.out.println("Produsul " + id + " nu a fost gasit!");
        }

        return null;
    }

    public List<Produs> findAll() {
        Session session = MagazinConfig.getSessionFactory().openSession();

        List<Produs> produse = (List<Produs>) session
                .createQuery("from produs").list();

        return produse;
    }

    public boolean update(Produs produs) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(produs);

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Produsul " + produs.getId() + " nu a fost modificat!");
        }

        return false;
    }

    public boolean delete(Produs produs) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(produs);

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Produsul " + produs.getId() + " nu a fost sters!");
        }

        return false;
    }


}
