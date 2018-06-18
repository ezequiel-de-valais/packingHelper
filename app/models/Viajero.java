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
    private Long id;

    @Constraints.Required
    private String usuario;

    @Constraints.Required
    private String password;

    @Constraints.Required
    private Date fechaDeNacimiento;

    @Constraints.Required
    private Sexo sexo;

    public static Viajero findById(Long id) {
        return FIND.byId(id);
    }

    public enum Sexo {
        @EnumValue("Hombre")
        HOMBRE,
        @EnumValue("Mujer")
        MUJER
    }

    public static Finder<Long,Viajero> FIND = new Finder<Long,Viajero>(
    Long.class, Viajero.class
    );

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

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