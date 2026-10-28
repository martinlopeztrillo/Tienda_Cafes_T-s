package com.martinlt.CafesyTe.base;

public abstract class Bebida {
    private String nombre;
    private String tipo;

    public  Bebida(){

    }

    public Bebida(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombre + " - " + tipo;
    }
}
