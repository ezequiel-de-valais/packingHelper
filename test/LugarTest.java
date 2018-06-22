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
public class LugarTest extends BaseTest {

    @Test
    public void crearLugar() throws Exception {
        String nombreLugar = "Argentina, Buenos Aires";
        Lugar lugar = new Lugar(nombreLugar, 5, 23);
        lugar.save();

        assertThat(lugar).isNotNull();
        assertThat(lugar.getId()).isGreaterThan(0);
        assertThat(lugar.getNombre()).isEqualTo(nombreLugar);
    }

    @Test
    public void encontrarLugar() throws Exception {
        Lugar brasil = TestHelper.getBrasilLugar();
        assertThat(brasil).isNotNull();

        Lugar brasilFounded = Lugar.findById(brasil.getId());
        assertThat(brasil.getId()).isEqualTo(brasilFounded.getId());
        assertThat(brasil.getNombre()).isEqualTo(brasilFounded.getNombre());
        assertThat(brasil.getLimiteEquipajeDeBodega()).isEqualTo(brasilFounded.getLimiteEquipajeDeBodega());
        assertThat(brasil.getLimiteEquipajeDeMano()).isEqualTo(brasilFounded.getLimiteEquipajeDeMano());
    }


}
