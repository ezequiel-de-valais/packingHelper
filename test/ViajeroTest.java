import models.Viajero;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ViajeroTest extends BaseTest {

    @Test
    public void crearViajero() throws Exception {
        String inputString = "11-11-1992";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDeNacimiento = dateFormat.parse(inputString);

        Viajero viajero = new Viajero("usuario", "password", fechaDeNacimiento, Viajero.Sexo.HOMBRE);
        viajero.save();


        Long id = viajero.getId();

        Viajero searchViajero = Viajero.findById(id);

        assertThat(searchViajero).isNotNull();
        assertThat(viajero.getUsuario()).isEqualTo("usuario");


    }


}
