import controllers.ProducatorController;
import dao.ProducatorDao;
import dao.ProdusDao;
import modele.Producator;
import modele.Produs;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ProducatorControllerTest {
    ProducatorController producatorController = new ProducatorController();  // instantiere controller pentru producator
    ProducatorDao producatorDao = new ProducatorDao();  // instantiere DAO pentru producator
    ProdusDao produsDao = new ProdusDao();              // instantiere DAO pentru produs

    List<Produs> listaProduse = populateListOfProducts();  // creare si populare lista globala de produse

    // metoda auxiliara pentru crearea si popularea unei liste de 2 produse
    private static List<Produs> populateListOfProducts(){
        Produs produs1 = new Produs();   // instantiere primul produs
        produs1.setNume("juice");
        produs1.setPret(3.99);
        produs1.setCantitateStoc(20);
        Produs produs2 = new Produs();   // instantiere al doilea produs
        produs2.setNume("water");
        produs2.setPret(1.99);
        produs2.setCantitateStoc(33);

        List<Produs> listaProduse = new ArrayList<Produs>();  // creare lista goala de produse
        listaProduse.add(produs1);                            // populare lista cu produs1
        listaProduse.add(produs2);                            // populare lista cu produs2

        return listaProduse;
    }


    @Test
    public void saveProducatorTest() {
        // given
        Producator producator = new Producator();             // instantiere producator
        producator.setNume("UNO_SRL");
        producator.setProduse(listaProduse);

        // when
        boolean isSaved = producatorController.saveProducator(producator);  // creare producator

        // then
        assertTrue(isSaved);  // verificarea crearii producatorului
    }


    @Test
    public void findProducatorTest() {
        // given
        Producator producator = new Producator();
        producator.setProducatorId(4);               // vreau sa "controlez" cu ce id intra in BD, sa pot gasi producatorul dupa acel id
        producator.setNume("Ioana_SRL");
        producator.setProduse(listaProduse);
        producatorController.saveProducator(producator); // am introdus producatorul in BD

        // when
        Producator producatorFind = producatorController.findProducator(4);

        // then
        assertNotNull(producatorFind);                      // verificare producator gasit sa nu fie null
        assertEquals("Ioana_SRL", producatorFind.getNume());  // verificare daca producatorul gasit are numele respectiv
    }


    @Test
    public void updateProducatorTest(){
        //given
        Producator producator = new Producator();
        producator.setProducatorId(14);               // vreau sa "controlez" cu ce id intra in BD, sa pot face update dupa acel id
        producator.setNume("IF_SRL");
        producator.setProduse(listaProduse);
        producatorController.saveProducator(producator); // am introdus producatorul in BD

        Producator producatorForUpdate = producatorController.findProducator(14); // caut produsul dupa id-ul cunoscut (setat anterior)
        producatorForUpdate.setNume("IoanaF_SRL");         // noul nume al producatorului

        //when
        boolean isUpdated = producatorController.updateProducator(producatorForUpdate);

        //then
        assertTrue(isUpdated);                                      // verific daca metoda de update returneaza true-condition
        assertNotEquals("IF_SRL", producatorForUpdate.getNume()); // verific vechiul nume cu noul nume al producatorului
    }


    @Test
    public void deleteProducatorTest(){
        //given
        Producator producator = new Producator();
        producator.setProducatorId(22);
        producator.setNume("RO_DR_SRL");
        producator.setProduse(listaProduse);
        producatorController.saveProducator(producator);  // salvez in BD producatorul cu IDul cunoscut pentru testing

        //when
        boolean isDeleted = producatorController.deleteProducator(22);

        //then
        assertTrue(isDeleted);
    }
}
