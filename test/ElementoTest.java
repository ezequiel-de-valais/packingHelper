import models.Elemento;
import models.Lugar;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ElementoTest extends BaseTest {

    @Test
    public void crearElemento() throws Exception {
        String nombre = "Remera";
        Elemento remera = new Elemento(nombre, 300, false, false);
        remera.save();

        assertThat(remera).isNotNull();
        assertThat(remera.getId()).isGreaterThan(0);
        assertThat(remera.getNombre()).isEqualTo(nombre);
    }

    @Test
    public void encontrarElemento() throws Exception {
        Elemento remera = TestHelper.getRemeraElemento();
        assertThat(remera).isNotNull();

        Elemento remeraFounded = Elemento.findById(remera.getId());

        assertThat(remera.getId()).isEqualTo(remeraFounded.getId());
        assertThat(remera.getNombre()).isEqualTo(remeraFounded.getNombre());
    }


}
