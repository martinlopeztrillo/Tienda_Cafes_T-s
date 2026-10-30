package com.martinlt.CafesyTe.base;

public class Tes extends  Bebida {
private int oxidacion;//preguntar tambien por si acaso la profesora no le gusta en int

    public Tes(){
        super();
    }

    public Tes(int oxidacion){
        this.oxidacion=oxidacion;
    }

    public Tes(String nombre, String tipo, int oxidacion) {
        super(nombre, tipo);
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
