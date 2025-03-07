package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModoFalla implements Serializable {
    private String nombre;
    private int gravedad;
    private String equipoEncargado;
    private LocalDate fecha;
    private String acciones;
    private double npr = 0;
    private EfectoFalla efectoFalla;
    private ArrayList<CausaFalla> causasFalla = new ArrayList<CausaFalla>();

    public ModoFalla(String nombre, int gravedad, String equipoEncargado, LocalDate fecha, String acciones, EfectoFalla efectoFalla, ArrayList<CausaFalla> causasFallas) {
        this.nombre = nombre;
        this.gravedad = gravedad;
        this.equipoEncargado = equipoEncargado;
        this.fecha = fecha;
        this.acciones = acciones;
        this.efectoFalla = efectoFalla;
        this.causasFalla.addAll(causasFallas);
    }

    //GET-SET: Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //GET-SET: Gravedad
    public int getGravedad() {
        return gravedad;
    }
    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    //GET-SET: Equipo Encargado
    public String getEquipo() {
        return equipoEncargado;
    }
    public void setEquipo(String equipoEncargado) {
        this.equipoEncargado = equipoEncargado;
    }

    //GET-SET: Fecha
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    //GET-SET: Acciones
    public String getAcciones() {
        return acciones;
    }
    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    //GET-SET: Efecto de Falla
    public EfectoFalla getEfectoFalla() {
        return efectoFalla;
    }
    public void setEfectoFalla(EfectoFalla efectoFalla) {
        this.efectoFalla = efectoFalla;
    }

    //GET-SET: Causas de Falla
    public ArrayList<CausaFalla> getCausasFalla() {
        return causasFalla;
    }
    public void setCausasFalla(ArrayList<CausaFalla> causasFalla) {
        this.causasFalla = causasFalla;
    }
    public void showCausas() {
        for (CausaFalla c : causasFalla) {
            System.out.println(c.getNombre());
        }
    }

    //GET-SET: NPR
    public double getNPR() {
        return npr;
    }
    public void setNpr(ArrayList<CausaFalla> causasFallas) {
        double suma = 0;
        for (CausaFalla c : this.causasFalla) {
            c.setNPR(this.gravedad, c.getOcurrencia(), c.getDeteccion());
            suma += c.getNPR();
        }
        npr = suma/this.causasFalla.size();
    }
    public void setNpr(double npr) {
        this.npr = npr;
    }
}
