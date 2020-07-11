import controllers.ProdusController;
import modele.Produs;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ProdusControllerTest {
    ProdusController produsController = new ProdusController();

    @Test
    public void saveTest() {
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
}
