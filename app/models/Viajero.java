package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
public class Viajero extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String usuario;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public Date fechaDeNacimiento;

    @Constraints.Required
    public Sexo sexo;

    public enum Sexo {
        @EnumValue("Hombre")
        HOMBRE,
        @EnumValue("Mujer")
        MUJER
    }

    public static Finder<Long,Viajero> find = new Finder<Long,Viajero>(
    Long.class, Viajero.class
    );

    public Viajero(String usuario, String password, Date fechaDeNacimiento, Sexo sexo) throws Exception {
        validateData(usuario, password);
        this.usuario = usuario;
        this.password = password;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.sexo = sexo;

    }

    private void validateData(String usuario, String password) throws Exception {
        if(usuario == null){
            throw new Exception("usuario incorrecto");
        }
        if(password == null || password.length() < 6){
            throw new Exception("password incorrecta, debe tener por lo menos 6 caracteres");
        }
    }

}