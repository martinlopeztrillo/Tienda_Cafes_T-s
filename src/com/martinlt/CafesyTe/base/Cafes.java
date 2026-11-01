package com.martinlt.CafesyTe.base;

import java.time.LocalDate;

public class Cafes extends Bebida {
private String intensidad;

public Cafes(){
    super();
}
public  Cafes(String intensidad){
    this.intensidad=intensidad;
}

    public Cafes(String nombre, String tipo, LocalDate FechaPedido, String intensidad) {
        super(nombre, tipo,FechaPedido);
        this.intensidad = intensidad;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    @Override
    public String toString() {
        return "Cafes{" +
                "intensidad='" + intensidad + '\'' +
                '}';
    }
}
