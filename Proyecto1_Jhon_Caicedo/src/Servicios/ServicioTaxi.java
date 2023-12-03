/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import java.util.Scanner;
import AcesoSistema.*;
import Usuarios.*;
/**
 * Clase que representa el servicio de taxi en la aplicación. Extiende la clase Servicio.
 * 
 * @author Jhon
 */
public class ServicioTaxi extends Servicio {

    private int cantidadPersonas;

    //constructor
    public ServicioTaxi(Ruta ruta, String fecha, int cantidadPersonas) {
        super(ruta, fecha);
        this.cantidadPersonas = cantidadPersonas;
    }
    public int getcantidadPersonas() {
        return cantidadPersonas;
    }
    public void setcantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    
    //Se encuentra en el case 1 y permite crear el servicio taxi si el cliente lo solicita
    public static int crearServicioTaxi(Cliente cliente_A) {
        int validarWhile=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su ubicacion:");
        String ubicacion = sc.nextLine();
        System.out.println("Ingrese su destino:");
        String destino = sc.nextLine();
        Ruta ruta = new Ruta(ubicacion, destino);
        System.out.println("Ingrese fecha con el formato(dd/mm/aaaa):");
        String fecha = sc.nextLine();
        System.out.println("Ingrese hora con el formato(hh:mm):");
        String hora = sc.nextLine();
       System.out.println("Numero de personas");
        int personas=AcesoSistema.pedirDatosEnteros();
        String metodo = Servicio.metodo_pago();
        System.out.println("Desea generar el servicio (si/no)");
        String confirmacion = sc.nextLine();
        String confi = confirmacion.toLowerCase();
        
        //Si el cliente confirma se genera el servicio
        if (confirmacion.equals("si")) {
             ServicioTaxi taxi = new ServicioTaxi(ruta, fecha, personas);
            String tipoVehiculo="A";
            String conductor = taxi.asignarconductor(tipoVehiculo);
            
            String linea = taxi.getCodigo() + "," + cliente_A.getNombre() + "," + conductor + ","+taxi.getRuta().getpuntoPartida() + "," + taxi.getRuta().getpuntoLlegada() + "," + taxi.getfecha() + ","+hora +","+ taxi.getcantidadPersonas() + "," + metodo +","+ taxi.getvalorPagar();
            Archivos.EscribirArchivo("viajes.txt", linea);
            System.out.println("**=============Factura=============**\n" + taxi.toString());
            
            Servicio taxi2 =(Servicio)taxi;
            cliente_A.setListaServicio(taxi2);
            System.out.println("¿Desea Solicitar otro Servicio? (si/no): ");
            String validar=sc.nextLine();
            String vali=validar.toLowerCase();
            
            if (validar.equals("si"))
                validarWhile = 1;
            else if(validar.equals("no"))
                validarWhile = 0;
        } else {
            validarWhile = 0;
        }
        return validarWhile;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCantidad de personas: " + cantidadPersonas;
    }

    
}
