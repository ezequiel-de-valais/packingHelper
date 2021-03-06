import models.Item;
import models.Lugar;
import models.Viaje;
import models.Viajero;
import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ViajeTest extends BaseTest {

    @Test
    public void crearViaje() throws Exception {
        Date fechaInicio =  TestHelper.getDateFromString("20-11-2018");
        Date fechaFin =  TestHelper.getDateFromString("30-11-2019");
        Viajero viajero = TestHelper.getViajero();

        Lugar destino = TestHelper.getBrasilLugar();

        Viaje viaje = new Viaje(fechaInicio,fechaFin,10,viajero, destino);
        viaje.save();

        assertThat(viaje).isNotNull();
        assertThat(viaje.getViajero().getId()).isEqualTo(viajero.getId());

    }


    @Test
    public void agregarItemAViaje() throws Exception {
        Viaje viaje = TestHelper.getViaje();
        Item remera = TestHelper.getRemeraItem();

        viaje.addItem(remera);

        viaje.update();

        Viaje viajeFounded = Viaje.findById(viaje.getId());

        assertThat(viaje).isNotNull();
        assertThat(viajeFounded.getItems().size()).isEqualTo(1);
        assertThat(viajeFounded.getItems().get(0).getId()).isEqualTo(remera.getId());
        assertThat(viajeFounded.getItems().get(0).getCantidad()).isEqualTo(remera.getCantidad());

    }




}
