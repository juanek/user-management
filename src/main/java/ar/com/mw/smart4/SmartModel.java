package ar.com.mw.smart4;

import java.io.Serializable;

public class SmartModel implements Serializable {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "SmartModel{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
