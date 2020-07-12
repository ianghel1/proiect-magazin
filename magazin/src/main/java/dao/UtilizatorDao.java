package dao;

import magazinConfig.MagazinConfig;
import modele.Utilizator;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UtilizatorDao {

    public void create(Utilizator utilizator) {
        Transaction transaction = null;
        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();

//            cast la Utilizator deoarece astepta un Serializable
            session.save(utilizator);

            transaction.commit();
            session.close();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Utilizatorul nu a fost salvat deoarece " + e.getMessage());
//            varianta 2 de afisare a exceptiei
//            e.printStackTrace();
        }


    }


    public Utilizator findByUsername(String username) {
//        String nativeQueryName = "SELECT FROM utilizator u  WHERE u.username = :userName ;";
//        List<Utilizator> result = new ArrayList<Utilizator>();
        List<Utilizator> lista = new ArrayList<Utilizator>();

        try {
            Session session = MagazinConfig.getSessionFactory().openSession();
////            Utilizator utilizator = session.find(Utilizator.class, username);
////            SELECT * FROM magazin.utilizator WHERE utilizator.username = 'ianghel';
//            Query<Utilizator> namedNativeQuery = session.createNamedQuery("findUtilizatorByUsername", Utilizator.class);
//// use query from org.hibernate.query
//            namedNativeQuery.setParameter("username", username);
//            result = namedNativeQuery.list();

            Query query = session.createQuery("FROM Utilizator u WHERE u.username = :userName ");
            query.setParameter("userName", username);
            lista = query.list();
            session.close();
            // returnam primul element pentru ca e / ar trebui sa fie singurul din lista
            return lista.get(0);
        } catch (Exception e) {
            System.out.println("Utilizatorul " + username + " nu a fost gasit!");
        }
        return null;
    }


    public Utilizator findById(int id) {
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
