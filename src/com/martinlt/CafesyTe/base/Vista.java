package com.martinlt.CafesyTe.base;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista<pu> {
    //creados por mi
    private JFrame frame;
    public DefaultListModel<Bebida> dlmBebida;
    // creados por el from
    private JPanel panel1;
    private JRadioButton cafesRB;
    private JTextField textField1;
    private JRadioButton TésRB;
    private JTextField nombreTxt;
    private JTextField tipoTxt;
    private JButton nuevoButton;
    private JButton exportarButton;
    private JButton importarButton;
    private JList list1;
    public DatePicker FechaPedido;




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

}
