/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import Servicios.*;


/**
 *
 * @author Jhon
 */
public class Cliente extends Usuario {

    private int edad;
    private int nro_tarjeta;
    private ArrayList<Servicio> listaServicio = new ArrayList();

    public Cliente(String nombre, String apellido, String nro_cedula, String celular, String usuario, String contraseña, int edad, int nro_tarjeta, char tipo) {
        super(nombre, apellido, nro_cedula, celular, usuario, contraseña, tipo);
        this.edad = edad;
        this.nro_tarjeta = nro_tarjeta;
    }

    public int getEdad() {
        return edad;
    }

    public int getNro_tarjeta() {
        return nro_tarjeta;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNro_tarjeta(int nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    //========================inicio===================
    public void setListaServicio(Servicio servicio) {
        this.listaServicio.add(servicio);
    }
    //========================fin===================
    
    @Override
    public void mostrarMenu(){
        super.mostrarMenu();
        System.out.println("1. Solicitar servicio de taxi");
        System.out.println("2. Solicitar servicio de encomienda");
        System.out.println("3. Solicitar servicio de comida");
        System.out.println("4. Consultar servicios");
    }
    
    
    
    @Override
    public int ConsultarServicioAsignado() {
        Scanner sc = new Scanner(System.in);
        int validarWhile = 1;
        //===============inicio============
        for (Servicio servi : listaServicio) {
            if (servi instanceof ServicioTaxi) {
                ServicioTaxi taxi = (ServicioTaxi) servi;
                System.out.println("Servicio de Taxi\n" + taxi.toString() + "\n");
            }
            if (servi instanceof EntregaEncomienda) {
                EntregaEncomienda enco = (EntregaEncomienda) servi;
                System.out.println("Servicio de Encomienda\n" + enco.toString() + "\n");
            }
            if (servi instanceof ServicioDelivery) {
                ServicioDelivery Deli = (ServicioDelivery) servi;
                System.out.println("Servicio de delivery\n" + Deli.toString() + "\n");
            }
        }
        System.out.println("¿Desea Solicitar otro Servicio? (si/no): ");
        String validar = sc.nextLine();

        if (validar.equals("si")) {
            validarWhile = 1;
        } else if (validar.equals("no")) {
            validarWhile = 0;
        }
        return validarWhile;
        //===============Fin===================
    }

    @Override
    public String toString() {
        return super.toString() + "[ Edad: " + getEdad() + " Nro.Tarjeta: " + getNro_tarjeta() + "]";
    }
    public static boolean validarcliente(String Nombrearchivo, Usuario usuario) {
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        String Encabezado;
        File archivo;
        boolean valor = false;
        try {
            archivo = new File(Nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                String[] datos;
                datos = linea.split(",");
                String cedula = datos[0];
                if (cedula.equals(usuario.getNro_cedula())) {
                    valor = true;
                }
            }
        } catch (Exception e) {
        }
        return valor;
    }
    public static void registrar_cliente(int edad, String Nro_tarjetacredito, String cedula, String nombreArchivo) {
        FileWriter fichero = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo, true);
            bw = new BufferedWriter(fichero);
            String Edad = String.valueOf(edad);
            bw.write(cedula + ",");
            bw.write(Edad + ",");
            bw.write(Nro_tarjetacredito + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
