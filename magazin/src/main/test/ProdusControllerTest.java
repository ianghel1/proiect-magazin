
import controllers.ProdusController;
import modele.Produs;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ProdusControllerTest {
    ProdusController produsController = new ProdusController();

    @Test
    public void saveProdusTest() {
        // given
        Produs produs = new Produs();
        produs.setNume("Cola");
        produs.setPret(5.99);
        produs.setCantitateStoc(2);

        // when
        boolean isSaved = produsController.saveProdus(produs);

        // then
        assertTrue(isSaved);
    }


    @Test
    public void findProdusTest() {
        // given

        // when
        List<Produs> lista = produsController.getAll();  // lista tuturor produselor

        // then
        assertNotNull(lista);                      // verificare lista nenula
        assertTrue(lista.size() > 0);     // verificare lista nu e goala
    }


    @Test
    public void updateProdusTest(){
        //given
        Produs produs = new Produs();
        produs.setNume("Fanta");
        produs.setPret(7.99);
        produs.setCantitateStoc(5);
        produs.setId(15);  // vreau sa "controlez" cu ce id intra in Baza de Date, sa pot face update dupa acel id
        produsController.saveProdus(produs);                          // salvat in Baza de Date

        Produs produsForUpdate = produsController.findProdus(15); // caut produsul dupa id-ul cunoscut (setat anterior)
        produsForUpdate.setPret(6.99);                                // noul pret (discount :)  )

        //when
        boolean isUpdated = produsController.updateProdus(produsForUpdate);

        //then
        assertTrue(isUpdated);                                      // verific daca metoda de update returneaza true-condition
        assertNotEquals(7.99, produsForUpdate.getPret()); // verific vechiul pret cu noul pret din update
    }


    @Test
    public void deleteProdusTest(){
        //given
        Produs produs = new Produs();
        produs.setId(12);
        produs.setNume("Sprite");
        produs.setPret(4.99);
        produs.setCantitateStoc(12);
        produsController.saveProdus(produs);       // salvat in Baza de Date

        //when
        boolean isDeleted = produsController.deleteProdus(12);

        //then
        assertTrue(isDeleted);
    }

}