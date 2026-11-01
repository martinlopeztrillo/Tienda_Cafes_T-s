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
    private  void altaCafes(String nombre, String tipo, String intensidad, LocalDate FechaPedido){
        Cafes nuevoCafe=new Cafes((nombre,tipo,intensidad,FechaPedido);
        listaBebidas.add(nuevoCafe);
    }

    //alta tés
    private  void altaTes(String nombre,String tipo,int oxidacion,LocalDate FechaPedido){
        Tes nuevoTes=new Cafes((nombre,tipo,oxidacion,FechaPedido);
        listaBebidas.add(nuevoTes);
    }

    //existe bebida
    public  boolean existeBebida(String nombre) throws ParserConfigurationException, TransformerConfigurationException {
        for(Bebida bebidas:listaBebidas){
            if(bebidas.getNombre().equals(nombre)){
                return  true;
            }
            return false;
        }


    //exportar
       public void exportar(File fichero) throws ParserConfigurationException,TransformerException{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            DOMImplementation dom=builder.getDOMImplementation();
            Document documento=dom.createDocument(null,"xml",null);

            //raiz
            Element raiz=documento.createElement("Bebida");
            documento.getDocumentElement().appendChild(raiz);

            //
            Element nodoBebida=null;
            Element nodoDatos=null;
            Text texto=null;


            for(Bebida unBebida:listaBebidas){
                //añado dentro de la etiqueta raiz Bebidas cafes o tes)
                if(unBebida instanceof Cafes){
                    nodoBebida=documento.createElement("Cafe");
                }else{
                    nodoBebida=documento.createElement("Te");
                }
                raiz.appendChild(nodoBebida);

                //dentro de cada cafes tes tengo que poner las etiquetas matricula etc
                nodoDatos=documento.createElement("Nombre");
                nodoBebida.appendChild(nodoDatos);

                texto=documento.createTextNode(unBebida.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos=documento.createElement("Tipo");
                nodoBebida.appendChild(nodoDatos);

                nodoDatos=documento.createElement("fechaPedido");
                nodoBebida.appendChild(nodoDatos);

              ;

                //nos falata el dato diferenet (numPlazas,kms)

                if(unBebida instanceof  Cafes){
                    nodoDatos=documento.createElement("Intensidad");
                    nodoBebida.appendChild(nodoDatos);

                    texto=documento.createTextNode(String.valueOf(((Cafes) unBebida).getIntensidad()));
                    nodoDatos.appendChild(texto);
                }else{
                    nodoDatos=documento.createElement("Oxidacion");
                    nodoBebida.appendChild(nodoDatos);

                    texto=documento.createTextNode(String.valueOf(((Tes) unBebida).getOxidacion()));
                    nodoDatos.appendChild(texto);
                }

            }
            // guardo en fichero
            Source source=new DOMSource(documento);
            Result resultado=new StreamResult(fichero);

            Transformer transformer= TransformerFactory.newInstance().newTransformer();
            try {
                transformer.transform(source,resultado);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
        }
    //importar
    public  void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaBebidas=new ArrayList<Bebida>();
        Cafes nuevoCafe=null;
        Tes nuevaTes=null;

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= factory.newDocumentBuilder();
        Document documento=builder.parse(fichero);

        NodeList listaElementos=documento.getElementsByTagName("*");

        for(int i=0;i<listaElementos.getLength();i++){
            Element nodoBebida=(Element) listaElementos.item(i);
            if(nodoBebida.getTagName().equals("Cafes")){
                nuevoCafe=new Cafes();
                nuevoCafe.setNombre(nodoBebida.getChildNodes().item(0).getTextContent());
                nuevoCafe.setTipo(nodoBebida.getChildNodes().item(1).getTextContent());
                nuevoCafe.setIntensidad(nodoBebida.getChildNodes().item(2).getTextContent());
                nuevoCafe.setFechaPedido(LocalDate.parse(nodoBebida.getChildNodes().item(3).getTextContent()));
                listaBebidas.add(nuevoCafe);
            }else{
                nuevaTes=new Tes();
                nuevaTes.setNombre(nodoBebida.getChildNodes().item(0).getTextContent());
                nuevaTes.setTipo(nodoBebida.getChildNodes().item(1).getTextContent());
                nuevaTes.setOxidacion(Integer.parseInt(nodoBebida.getChildNodes().item(2).getTextContent()));
                nuevaTes.setFechaPedido(LocalDate.parse(nodoBebida.getChildNodes().item(3).getTextContent()));
                listaBebidas.add(nuevaTes);
            }
        }
    }
    }



