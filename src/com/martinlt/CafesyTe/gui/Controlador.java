package com.martinlt.CafesyTe.gui;

import com.martinlt.CafesyTe.base.Vista;

public class Controlador {
private Vista vista;
private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
