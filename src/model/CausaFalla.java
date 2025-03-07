package model;

import java.io.Serializable;

import model.utilities.NPR;

public class CausaFalla implements Serializable {
    private String nombre;
    private int ocurrencia;
    private int deteccion;
    private Double npr;

    public CausaFalla(String nombre, int ocurrencia, int deteccion) {
        this.nombre = nombre;
        this.ocurrencia = ocurrencia;
        this.deteccion = deteccion;
    }

    //GET-SET: Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //GET-SET: Ocurrencia
    public int getOcurrencia() {
        return ocurrencia;
    }
    public void setOcurrencia(int ocurrencia) {
        this.ocurrencia = ocurrencia;
    }

    //GET-SET: Deteccion
    public int getDeteccion() {
        return deteccion;
    }
    public void setDeteccion(int deteccion) {
        this.deteccion = deteccion;
    }

    //GET-SET: NPR
    public Double getNPR() {
        return npr;
    }
    public void setNPR(int gravedad, int ocurrencia, int deteccion) {
        npr = NPR.calcNPR(gravedad, ocurrencia, deteccion);
    }
}
