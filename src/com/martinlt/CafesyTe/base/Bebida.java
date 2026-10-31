package com.martinlt.CafesyTe.base;

import java.time.LocalDate;

public abstract class Bebida {
    private String nombre;
    private String tipo;
    private LocalDate FechaPedido;

    public Bebida() {

    }

    public Bebida(String nombre, String tipo, LocalDate fechaPedido) {
        this.nombre = nombre;
        this.tipo = tipo;
        FechaPedido = fechaPedido;
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

    public LocalDate getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        FechaPedido = fechaPedido;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", FechaPedido=" + FechaPedido +
                '}';
    }
}
