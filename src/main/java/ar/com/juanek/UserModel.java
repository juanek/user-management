package ar.com.juanek;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
