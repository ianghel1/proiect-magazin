import controllers.UtilizatorController;
import modele.Utilizator;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilizatorControllerTest {

    UtilizatorController utilizatorController = new UtilizatorController();  // instantiere controller pentru utilizator

    @Test
    public void saveUtilizatorTest() {
        // given
        Utilizator utilizator = new Utilizator();             // instantiere utilizator
        utilizator.setNume("Frone");
        utilizator.setPrenume("Gigel");
        utilizator.setAdresa("Bucuresti, Romania");
        utilizator.setTelefon("0722456789");
        utilizator.setParola(12345L);


        // when
        boolean isSaved = utilizatorController.saveUtilizator(utilizator);  // creare utilizator

        // then
        assertTrue(isSaved);  // verificarea crearii utilizatorului
    }


    @Test
    public void findUtilizatorTest() {
        // given
        Utilizator utilizator = new Utilizator();
        utilizator.setId(5L);               // vreau sa "controlez" cu ce id intra in BD, sa pot gasi utilizatorul dupa acel id
        utilizator.setNume("Farafrica");
        utilizator.setPrenume("Gica");
        utilizator.setAdresa("Romania");
        utilizator.setTelefon("07123456987");
        utilizator.setParola(852L);

        utilizatorController.saveUtilizator(utilizator); // am introdus utilizatorul in BD

        // when
        Utilizator utilizatorFind = utilizatorController.findUtilizator(5L);

        // then
        assertNotNull(utilizatorFind);                      // verificare utilizator gasit sa nu fie null
        assertEquals("Farafrica", utilizatorFind.getNume());  // verificare daca utilizatorul gasit are numele respectiv
    }


    @Test
    public void updateUtilizatorTest(){
        //given
        Utilizator utilizator = new Utilizator();
        utilizator.setId(2L);               // vreau sa "controlez" cu ce id intra in BD, sa pot face update dupa acel id
        utilizator.setNume("Furnica");
        utilizator.setPrenume("Mirciulica");
        utilizator.setAdresa("Bulgaria");
        utilizator.setTelefon("7773456321");
        utilizator.setParola(123L);

        utilizatorController.saveUtilizator(utilizator); // am introdus utilizatorul in BD
        Utilizator utilizatorForUpdate = utilizatorController.findUtilizator(2L);

        utilizatorForUpdate.setParola(548L);         // noua parola a  utilizatorului
        utilizatorForUpdate.setAdresa("Romania"); // noua adresa a utilizatorului

        //when
        boolean isUpdated = utilizatorController.updateUtilizator(utilizatorForUpdate);
        Long parolaNoua = utilizatorForUpdate.getParola();
        Long parolaVeche = 123L;
        //then
        assertTrue(isUpdated);                                      // verific daca metoda de update returneaza true-condition
        assertNotEquals("Bulgaria", utilizatorForUpdate.getAdresa()); // verific vechia adresa cu noua adresa a utilizatorului
        assertNotEquals(parolaVeche, parolaNoua); // verific vechia parola cu noua parola a utilizatorului
    }


    @Test
    public void deleteUtilizatorTest(){
        //given
        Utilizator utilizator = new Utilizator();
        utilizator.setId(3L);               // vreau sa "controlez" cu ce id intra in BD, sa pot face update dupa acel id
        utilizator.setNume("Floare");
        utilizator.setPrenume("Mugurel");
        utilizator.setAdresa("Italia");
        utilizator.setTelefon("394587978");
        utilizator.setParola(365478L);

        utilizatorController.saveUtilizator(utilizator); // am introdus utilizatorul in BD
        Utilizator utilizatorToDelete = utilizatorController.findUtilizator(3L);
        //when
        boolean isDeleted = utilizatorController.deleteUtilizator(3L);

        //then
        assertTrue(isDeleted);
    }
}