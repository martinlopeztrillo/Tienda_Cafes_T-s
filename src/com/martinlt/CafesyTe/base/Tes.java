package com.martinlt.CafesyTe.base;

import java.time.LocalDate;

public class Tes extends  Bebida {
private int oxidacion;

    public Tes(){
        super();
    }

    public Tes(int oxidacion){
        this.oxidacion=oxidacion;
    }

    public Tes(String nombre, String tipo, LocalDate FechaPedido, int oxidacion) {
        super(nombre, tipo,FechaPedido);
        this.oxidacion = oxidacion;
    }

    public int getOxidacion() {
        return oxidacion;
    }

    public void setOxidacion(int oxidacion) {
        this.oxidacion = oxidacion;
    }

    @Override
    public String toString() {
        return "Tes{" +
                "oxidacion=" + oxidacion +
                '}';
    }
}
