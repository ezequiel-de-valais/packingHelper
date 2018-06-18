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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDeNacimiento = dateFormat.parse(fechaNacimientoStr);

        Viajero viajero = new Viajero("usuario", "password", fechaDeNacimiento, Viajero.Sexo.HOMBRE);
        viajero.save();


        Long id = viajero.getId();

        Viajero searchViajero = Viajero.findById(id);

        assertThat(searchViajero).isNotNull();
        assertThat(viajero.getUsuario()).isEqualTo("usuario");

    }
    @Test
    public void crearViaje() throws Exception {
        String fechaNacimientoStr = "11-11-1992";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDeNacimiento = dateFormat.parse(fechaNacimientoStr);
        String fechaInicioStr = "11-11-1992";
        Date fechaInicio = dateFormat.parse(fechaInicioStr);
        String fechaFinStr = "11-11-1992";
        Date fechaFin = dateFormat.parse(fechaFinStr);

        Viajero viajero = new Viajero("usuario", "password", fechaDeNacimiento, Viajero.Sexo.HOMBRE);
        viajero.save();

        Lugar destino = new Lugar("Brasil", 5, 23);
        destino.save();

        Viaje viaje = viajero.crearViaje(fechaInicio, fechaFin, destino);
        assertThat(viaje).isNotNull();
        assertThat(viaje.getViajero().getId()).isEqualTo(viajero.getId());



    }


}
