import models.Lugar;
import models.Viaje;
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
        String fechaNacimientoStr = "11-11-1992";
        Date fechaDeNacimiento = TestHelper.getDateFromString(fechaNacimientoStr);

        Viajero viajero = new Viajero("usuario", "password", fechaDeNacimiento, Viajero.Sexo.HOMBRE);
        viajero.save();
        Long id = viajero.getId();

        Viajero searchViajero = Viajero.findById(id);

        assertThat(searchViajero).isNotNull();
        assertThat(viajero.getUsuario()).isEqualTo("usuario");

    }

    @Test
    public void crearViaje() throws Exception {
        Date fechaDeNacimiento =  TestHelper.getDateFromString("11-11-1992");
        Date fechaInicio =  TestHelper.getDateFromString("20-11-2018");
        Date fechaFin =  TestHelper.getDateFromString("30-11-2019");
        Viajero viajero = new Viajero("usuario", "password", fechaDeNacimiento, Viajero.Sexo.HOMBRE);
        viajero.save();
        Lugar destino = TestHelper.getBrasilLugar();

        Viaje viaje = viajero.crearViaje(fechaInicio, fechaFin, destino);

        assertThat(viaje).isNotNull();
        assertThat(viaje.getViajero().getId()).isEqualTo(viajero.getId());

    }


}
