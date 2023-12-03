/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import Usuarios.*;
/**
 * Clase que representa un servicio de transporte.
 * 
 * @author Jhon
 */
public class Servicio {
    protected Ruta ruta;
    protected String conductor;
    protected String fecha;
    protected static double valorPagar;//cambio
    protected String codigo;
    
    
    public Servicio(Ruta ruta,String fecha){
        this.ruta=ruta;
        this.fecha=fecha;
        this.valorPagar=Math.random();//cambio
        this.codigo=String.valueOf((int)(Math.random()*1000000));
                
    }
    public String getfecha(){
        return fecha;
    }
    public void setfecha(String fecha){
     this.fecha=fecha;
    }
    public String getConductor(){
        return conductor;
    }
    public void setConductor(String c){
        this.conductor= c;
    }
    public Ruta getRuta(){
        return ruta;
    }
    public void setRuta(String partida,String llegada){
        this.ruta=new Ruta(partida,llegada);
    }
    
    public double getvalorPagar(){
        return valorPagar;
    }
    public void setvalorPagar(double valorPagar){
        this.valorPagar=valorPagar;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCodigo() {
        return codigo;
    }
    
    
    @Override//comienzo
    public String toString() {
        return ruta.toString() + "\nConductor: " + conductor + "\nFecha: " + fecha + "\nValor a pagar: " + valorPagar + "\nCodigo: " + codigo;
    }
    //Metodo sobrecargado, se implementan en metodo_pago
    public static double calcularPrecio(double precio) {
        double precioFinal = precio;
        return precioFinal;
    }
    public static double calcularPrecio(double precio,  double incremento){
        double precioFinal = precio*incremento;
        return precioFinal;
    }
    //Metodo usado para calcular el precio y definir el tipo de pago (efectivo/trajeta)
    public static String metodo_pago(){
        String metodo="";
        double valor=0;
        int validar=1;
        Scanner sc =new Scanner(System.in);
        while (validar!=0){
            System.out.println("Elija una opción de pago:\n1.Pago en efectivo\n2.Pago con tarjeta\nIngrese una opcion:");
            int op =sc.nextInt();
            sc.nextLine();
            switch (op){
                case 1:
                    valor=calcularPrecio(valorPagar);
                    validar=0;
                    metodo="Pago en efectivo";
                    break;
                case 2:
                    double incremento =1.10;
                    valor=calcularPrecio(valorPagar,incremento);
                    validar=0;
                    metodo="Pago con trajeta";
                    break;
                default:
                    System.out.println("Opcion no valida. Vuelva a elegir\n");
                    validar=1;
                    
            }
            
        }
        
        valorPagar=valor;
        return metodo;
    }
    //Forma alterna para calcular el precio del servicio(para explorar opciones)
    public double calcularTotalServicios(String pago, double subtotal){
        double total=subtotal;
        if(pago.equals("Tarjeta de credito")){
            total=((subtotal*0.10)+subtotal);
        }
        
        return total;
    }
    
    //Asigna un conductor a los servicios
    public String asignarconductor(String tipoVehiculo){
        String conductor="";
        
        String nombrearchivo="conductoresApp.txt";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos=linea.split(",");
                if (datos[3].equals("D")){
                    String tipo=Conductor.tipo("vehiculo.txt", datos[4]);
                    if (tipo.equals(tipoVehiculo)){
                       conductor=datos[0]; 
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
        
        
        this.conductor=conductor;
        return conductor;
    }

        
}
