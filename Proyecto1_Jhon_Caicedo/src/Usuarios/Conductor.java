/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import Enums.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jhon
 */
public class Conductor extends Usuario {

    private String licencia;
    private EstadoConductor estado;
    private TipoVehiculo vehiculo;
    private ArrayList<String> serviciosAsignados = new ArrayList<>();

    public Conductor(String nombre, String apellido, String nro_cedula, String celular, String usuario, String contraseña, String licencia, EstadoConductor estado, TipoVehiculo vehiculo, char tipo) {
        super(nombre, apellido, nro_cedula, celular, usuario, contraseña, tipo);
        this.licencia = licencia;
        this.estado = estado;
        this.vehiculo = vehiculo;

    }

    public String getLicencia() {
        return licencia;
    }

    public EstadoConductor getEstado() {
        return estado;
    }

    public TipoVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public void setEstado(EstadoConductor estado) {
        this.estado = estado;
    }

    public void setVehiculo(TipoVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    //Metodo sobreescrito para el conductor
    public int ConsultarServicioAsignado() {
        Scanner sc = new Scanner(System.in);
        int validarWhile = 1;
        leerArchivoServicio();
        if (serviciosAsignados.size() == 0) {
            System.out.print(serviciosAsignados.size());
            System.out.println("Conductor sin servicios, se está trabajando");
        } else {
            for (String servicios : serviciosAsignados) {
                System.out.println(servicios);
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
    }


    
    @Override
    public void mostrarMenu(){
        super.mostrarMenu();
        System.out.println("1. Consultar servicio asignado");
    }
    

    @Override
    public String toString() {
        return super.toString() + "[ Licencia: " + getLicencia() + " Estado: " + getEstado() + " Vehiculo: " + getVehiculo() + "]";
    }
    //Lee los archivos de servicios para luego asignarle los servicios al conductor
    private void leerArchivoServicio() {
        String informacion = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String[] servicios = {"viajes.txt", "encomiendas.txt"};

        try {
            for (String nombreArchivo : servicios) {

                // Apertura del fichero y creacion de BufferedReader para poder
                // hacer una lectura comoda (disponer del metodo readLine()).
                archivo = new File(nombreArchivo);
                fr = new FileReader(archivo, StandardCharsets.UTF_8);
                br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    for (String nombre : datos) {
                        if (nombre.equals(this.nombre)) {
                            if (nombreArchivo.equals("viajes.txt")) {
                                String servicio = "Usted tiene asignado el Servicio Taxi\nDesde: " + datos[3] + "\nHasta: " + datos[4] + "\nFecha: " + datos[5] + "\nHora: " + datos[6] + "\nCantidad de personas: " + datos[7] + "\n\n";
                                serviciosAsignados.add(servicio);
                            }
                            if(nombreArchivo.equals("encomiendas.txt")){
                               String Servicio="Usted Tiene asignado el servicio de encomienda: \nTipo de encomienda: "+datos[7]+"\nCantidad: "+datos[8]+"\nFecha: "+datos[5]+"\nHora "+datos[6]+"\nDesde: "+datos[3]+"\nHasta: "+datos[4];
                               serviciosAsignados.add(Servicio);
                            }
                            if(nombreArchivo.equals("delivery.txt")){
                                String Servicio="Usted Tiene asignado el servicio de encomienda: \nTipo de encomienda: "+"\nFecha: "+datos[5]+"\nHora "+datos[6]+"\nDesde: "+datos[3]+"\nHasta: "+datos[4];
                                serviciosAsignados.add(Servicio);
                                
                            }
                            
                                
                        }
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
    }
    //Retorna el estado(O/D) y la licencia del conductor
    public static String licenciaEstado(String nombrearchivo, String nombre) {
        String informacion = "";
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
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(nombre)) {
                    informacion = datos[2] + "," + datos[3] + "," + datos[4];
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
    
     public static String tipo(String nombrearchivo, String code) {
        String informacion = "";
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
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(code)) {
                    informacion = datos[4];

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
}
