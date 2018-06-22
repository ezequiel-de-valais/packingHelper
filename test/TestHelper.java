import models.*;

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

    public static Item getRemeraItem() {
        Item remera = new Item(getRemeraElemento(),5);
        remera.save();
        return remera;
    }

    public static Viaje getViaje() {
        Date fechaInicio =  TestHelper.getDateFromString("20-11-2018");
        Date fechaFin =  TestHelper.getDateFromString("30-11-2019");
        Viajero viajero = TestHelper.getViajero();

        Lugar destino = TestHelper.getBrasilLugar();
        Viaje viaje = new Viaje(fechaInicio,fechaFin,10,viajero, destino);
        viaje.save();
        return viaje;
    }
}
