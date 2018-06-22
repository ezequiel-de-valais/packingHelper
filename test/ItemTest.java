import models.Elemento;
import models.Item;
import models.Lugar;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ItemTest extends BaseTest {

    @Test
    public void crearItem() throws Exception {
        Elemento remera = TestHelper.getRemeraElemento();
        Item remeraItem = new Item(remera, 5);
        remeraItem.save();

        assertThat(remeraItem).isNotNull();
        assertThat(remeraItem.getId()).isGreaterThan(0);
        assertThat(remeraItem.getNombre()).isEqualTo(remera.getNombre());
        assertThat(remeraItem.getCantidad()).isEqualTo(5);
        assertThat(remeraItem.getPesoIndividual()).isEqualTo(remera.getPesoIndividual());
        assertThat(remeraItem.isDeMano()).isEqualTo(remera.isDeMano());
        assertThat(remeraItem.isConsumible()).isEqualTo(remera.isConsumible());
    }

    //@Test
    public void encontrarItem() throws Exception {
        Item remera = TestHelper.getRemeraItem();
        assertThat(remera).isNotNull();

        Item remeraFounded = Item.findById(remera.getId());

        assertThat(remera.getId()).isEqualTo(remeraFounded.getId());
        assertThat(remera.getNombre()).isNotEmpty();
        assertThat(remera.getNombre()).isEqualTo(remeraFounded.getNombre());
        assertThat(remera.getCantidad()).isGreaterThan(0);
    }


}
