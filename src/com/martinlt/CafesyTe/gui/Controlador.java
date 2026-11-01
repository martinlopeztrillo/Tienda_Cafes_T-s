package com.martinlt.CafesyTe.gui;

import com.martinlt.CafesyTe.base.Bebida;
import com.martinlt.CafesyTe.base.Cafes;
import com.martinlt.CafesyTe.base.Tes;
import com.martinlt.CafesyTe.base.Vista;
import com.martinlt.CafesyTe.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Controlador  implements ActionListener, ListSelectionListener, WindowListener {
private Vista vista;
private Modelo modelo;
private File ultimaRutaExportada;

    public Controlador(Vista vista,Modelo modelo){
        this.vista=vista;
        this.modelo=modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracuion" +e.getMessage());
        }
        addActionListener(this);
        addSelectionListener(this);
        addWindowListener(this);
    }
    private boolean hayCamposVacios(){
        if(vista.nombreTxt.getText().isEmpty() ||
                vista.tipoTxt.getText().isEmpty() ||
                vista.FechaPedidoDP.getText().isEmpty()
        ){
            return  true;
        }
        return false;
    }

    private void limpiarCampos(){
        vista.nombreTxt.setText(null);
        vista.tipoTxt.setText(null);
        vista.FechaPedidoDP.setText(null);

    }

    public  void refrescar(){
        vista.dlmBebida.clear();
        for(Bebida unBebida:modelo.obtenerBebidas()){
            vista.dlmBebida.addElement(unBebida);
        }
    }

    private void addActionListener(ActionListener listener){
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.cafesRB.addActionListener(listener);
        vista.TesRB.addActionListener(listener);
    }
    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);

    }
    private void addSelectionListener(ListSelectionListener listener){
        vista.list1.addListSelectionListener(listener);
    }
    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion=new Properties();
        configuracion.load(new FileReader("Bebidass.conf"));
        ultimaRutaExportada=new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private  void  actualizarDatosConfiguracion(File ultimaRutaExportada){
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private  void guardarConfiguracion() throws IOException {
        Properties configuracion=new Properties();
        configuracion.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("Bebidas.conf"),"Datos configuracion de la tienda Té-rremoto de Sabor ");
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp= Util.MensajeConfirmacion("¿Estas seguro que Té quieres cerrar la ventana?","Té Sales");
        if(resp== JOptionPane.OK_OPTION){
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String  actionCommand=e.getActionCommand();
        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Porfavor rellene los siguientes campos: \n" +
                            "Nombre \n Tipo \n Fecha Pedido"
                            +vista.IntensiOxidablTxt.getText());
                    break;
                }
                if (modelo.existeBebida(vista.tipoTxt.getText())) {
                    Util.mensajeError("Ya existe esta bebida con este nombre \n" +
                            vista.tipoTxt.getText());
                    break;
                }
                if (vista.cafesRB.isSelected()) {
                    modelo.altaCafes(vista.nombreTxt.getText(),
                            vista.tipoTxt.getText(),
                            vista.IntensiOxidablTxt.getText(),
                            vista.FechaPedidoDP.getDate());

                } else {
                    modelo.altaTes(vista.nombreTxt.getText(),
                                                vista.tipoTxt.getText(),
                            Integer.parseInt(vista.IntensiOxidablTxt.getText()),
                            vista.FechaPedidoDP.getDate());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectionFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectionFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int opcion2 = selectorFichero2.showSaveDialog(null);
                if (opcion2==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Tes":
                vista.IntensiOxidablTxt.setText("Oxidacion");
                break;
            case "Cafes":
                vista.IntensiOxidablTxt.setText("Intensidad");
                break;
        }
    }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()){
                Bebida BebidaSeleccionada= (Bebida) vista.list1.getSelectedValue();
                vista.nombreTxt.setText(BebidaSeleccionada.getNombre());
                vista.tipoTxt.setText(BebidaSeleccionada.getTipo());

                vista.FechaPedidoDP.setDate(BebidaSeleccionada.getFechaPedido());
                if(BebidaSeleccionada instanceof Cafes){
                    vista.IntensiOxidablTxt.setText(String.valueOf(((Cafes) BebidaSeleccionada).getIntensidad()));
                    vista.cafesRB.doClick();
                }else{
                    vista.IntensiOxidablTxt.setText(String.valueOf(((Tes) BebidaSeleccionada).getOxidacion()));
                    vista.TesRB.doClick();
                }
            }
        }



    // No se usan

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

}
