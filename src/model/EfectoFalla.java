package model;

import java.io.Serializable;

public class EfectoFalla implements Serializable {
    private String nombre;

    public EfectoFalla(String nombre) {
        this.nombre = nombre;
    }

    //GET-SET: Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
