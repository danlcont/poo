/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AcesoSistema;

import Enums.TipoVehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jhon
 */
public class Archivos {

    double valorPagar;

    //================Inicio===============
    
    //EL metodo validarContenidoArchivo valida que lo que se desea escribir, no este en el archivo.
    public static boolean validarContenidoArchivo(String nombrearchivo, String linea) {
        boolean informacion = false;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String lineaArchivo;
            while ((lineaArchivo = br.readLine()) != null) {
                
                if (lineaArchivo.equals(linea)) {
                    informacion = false;
                } else{
                    String[] datos=linea.split(",");
                    String primerdato=datos[0];
                    String[] encabezado=lineaArchivo.split(",");
                    String validarEncabezado=encabezado[0];
                   
                    if(datos.length != encabezado.length){
                        informacion = false;
                    } else{
                        informacion = true;
                    }
                    
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return informacion;

    }
    //El metodo segundaValidacion valida que el archivo este vacio
    public static boolean segundaValidacion(String nombrearchivo) {
        boolean informacion = false;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String lineaArchivo;
            lineaArchivo = br.readLine();
            if (lineaArchivo != null) {
                informacion = true;
            } else if (lineaArchivo == null) {
                informacion = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return informacion;

    }
    //Escribe en el archivo
    public static void EscribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo, true);
            bw = new BufferedWriter(fichero);
            Boolean validar = validarContenidoArchivo(nombreArchivo, linea);
            if (validar == true) {
                bw.write(linea+"\n");
            } else {
                Boolean segundaValidacion = segundaValidacion(nombreArchivo);
                if (validar == false && segundaValidacion == false) {
                    bw.write(linea+"\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    //retorna el tipo de vehiculo
   

}
