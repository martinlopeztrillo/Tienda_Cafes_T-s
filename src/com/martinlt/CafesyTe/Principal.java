package com.martinlt.CafesyTe;

import com.martinlt.CafesyTe.base.Vista;
import com.martinlt.CafesyTe.gui.Controlador;
import com.martinlt.CafesyTe.gui.Modelo;

public class Principal {
    public static void main(String[] args) {
     Vista vista=new Vista();
     Modelo modelo=new Modelo();
     Controlador controlador=new Controlador(vista,modelo);

    }
}
