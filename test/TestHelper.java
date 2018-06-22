import models.Elemento;
import models.Lugar;
import models.Viajero;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestHelper {
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static Date getDateFromString(String stringDate){
        try {
            return  dateFormat.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Lugar getBrasilLugar() {
        Lugar brasil = new Lugar("Brasil", 5, 23);
        brasil.save();
        return brasil;
    }

    public static Viajero getViajero() {
        Date fechaDeNacimiento =  TestHelper.getDateFromString("11-11-1992");
        Viajero viajero = null;
        try {
            viajero = new Viajero("usuario", "password", fechaDeNacimiento, Viajero.Sexo.HOMBRE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        viajero.save();
        return viajero;
    }

    public static Elemento getRemeraElemento() {
        Elemento remera = new Elemento("Remera", 300, false, false);
        remera.save();
        return remera;
    }
}
