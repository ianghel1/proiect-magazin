import controllers.ProdusController;
import modele.Produs;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ProdusControllerTest {
    ProdusController produsController = new ProdusController();

    @Test
    public void saveProdusTest() {
//        given
        Produs produs = new Produs();
        produs.setNume("Cola");
        produs.setPret(5.99);
        produs.setCantitateStoc(2);
//        when
        boolean isSaved = produsController.saveProdus(produs);

//        then
        assertTrue(isSaved);
    }

    @Test
    public void findProdusTest() {

        List<Produs> lista = produsController.getAll();

        assertNotNull(lista);

        assertTrue(lista.size() > 0);

    }


    @Test
    public void updateProdusTest(){
        //given
        Produs produs = new Produs();
        produs.setNume("Fanta");
        produs.setPret(7.99);
        produs.setCantitateStoc(5);

        //when


        //then



    }


    @Test
    public void deleteProdusTest(){
       //given
        Produs produs = new Produs();
        produs.setId(12L);
        produs.setNume("Sprite");
        produs.setPret(4.99);
        produs.setCantitateStoc(12);

        //when
        boolean isDeleted = produsController.deleteProdus(12L);

        //then
        assertTrue(isDeleted);
    }

}
