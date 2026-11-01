package com.martinlt.CafesyTe.base;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista<pu> {
    //creados por mi
    public JFrame frame;
    public DefaultListModel<Bebida> dlmBebida;
    // creados por el from
    private JPanel panel1;
    public JRadioButton cafesRB;
    public JTextField nombreTxt;
    public JRadioButton TesRB;
    public JTextField intensidadTxt;
    public JTextField tipoTxt;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public DatePicker FechaPedidoDP;
    public JTextField IntensiOxidablTxt;


    public Vista() {
        frame = new JFrame("Bebidas MVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
        //centrar
        frame.setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents() {
        dlmBebida= new DefaultListModel<Bebida>();
        list1.setModel(dlmBebida);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
