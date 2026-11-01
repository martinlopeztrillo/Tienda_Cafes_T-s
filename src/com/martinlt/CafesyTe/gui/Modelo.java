package com.martinlt.CafesyTe.gui;

import com.martinlt.CafesyTe.base.Bebida;
import com.martinlt.CafesyTe.base.Cafes;
import com.martinlt.CafesyTe.base.Tes;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {
    public ArrayList<Bebida> listaBebidas;

    public Modelo(){
        listaBebidas=new ArrayList<Bebida>();
    }
    public  ArrayList<Bebida> obtenerBebidas(){
        return listaBebidas;
    }

    //alta cafes
    public  void altaCafes(String nombre, String tipo, String intensidad, LocalDate FechaPedido){
        Cafes nuevoCafe=new Cafes(nombre,tipo,FechaPedido,intensidad);
        listaBebidas.add(nuevoCafe);
    }

    //alta tés
    public  void altaTes(String nombre,String tipo,int oxidacion,LocalDate FechaPedido){
        Tes nuevoTes=new Tes(nombre,tipo,FechaPedido,oxidacion);
        listaBebidas.add(nuevoTes);
    }

    //existe bebida
    public boolean existeBebida(String nombre){
        for(Bebida bebidas:listaBebidas){
            if(bebidas.getNombre().equals(nombre)){
                return true;
            }
        }
        return  false;
    }
    //exportar
    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null,"xml",null);

        //añado el nodo raiz Bebida
        Element raiz = documento.createElement("Bebida");
        documento.getDocumentElement().appendChild(raiz);

        //otros elementos
        Element nodoBebida=null;
        Element nodoDatos=null;
        Text texto=null;

        for (Bebida unBebida: listaBebidas) {
            //añado dentro de la etiqueta raiz vehiculos (Cafes o Tes)
            if (unBebida instanceof Cafes) {
                nodoBebida=documento.createElement("Cafes");
            } else {
                nodoBebida=documento.createElement("Tes");
            }
            raiz.appendChild(nodoBebida);

            //dentro de cada Cafes, Tes tengo que poner las etiquetas Nombre, Tipo,...
            nodoDatos=documento.createElement("Nombre");
            nodoBebida.appendChild(nodoDatos);

            texto=documento.createTextNode(unBebida.getNombre());
            nodoBebida.appendChild(texto);

            nodoDatos=documento.createElement("Tipo");
            nodoBebida.appendChild(nodoDatos);

            texto=documento.createTextNode(unBebida.getTipo());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("fechaPedido");
            nodoBebida.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unBebida.getFechaPedido().toString()));
            nodoDatos.appendChild(texto);

            //nos falta el dato diferente (Intensidd, Oxidacion)
            if (unBebida instanceof Cafes) {
                nodoDatos=documento.createElement("Intensidad");
                nodoBebida.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(((Cafes) unBebida).getIntensidad()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos=documento.createElement("Oxidacion");
                nodoBebida.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(((Tes) unBebida).getOxidacion()));
                nodoDatos.appendChild(texto);
            }

        }
        //guardo en fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);
    }

    //importar
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaBebidas = new ArrayList<Bebida>();
        Cafes nuevoCafes = null;
        Tes nuevaTes = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoBebidas = (Element) listaElementos.item(i);
            if (nodoBebidas.getTagName().equals("Cafe")) {
                nuevoCafes = new Cafes();
                nuevoCafes.setNombre(nodoBebidas.getChildNodes().item(0).getTextContent());
                nuevoCafes.setTipo(nodoBebidas.getChildNodes().item(1).getTextContent());
                nuevoCafes.setFechaPedido(LocalDate.parse(nodoBebidas.getChildNodes().item(3).getTextContent()));
                nuevoCafes.setIntensidad(nodoBebidas.getChildNodes().item(4).getTextContent());
                listaBebidas.add(nuevoCafes);
            } else {
                if (nodoBebidas.getTagName().equals("Tes")) {
                    nuevaTes = new Tes();
                    nuevaTes.setNombre(nodoBebidas.getChildNodes().item(0).getTextContent());
                    nuevaTes.setTipo(nodoBebidas.getChildNodes().item(1).getTextContent());
                    nuevaTes.setFechaPedido(LocalDate.parse(nodoBebidas.getChildNodes().item(3).getTextContent()));
                    nuevaTes.setOxidacion(Integer.parseInt(nodoBebidas.getChildNodes().item(4).getTextContent()));
                    listaBebidas.add(nuevaTes);
                }
            }

        }

    }
}



