/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Enums.TipoEncomienda;
import java.util.Scanner;
import AcesoSistema.*;
import Usuarios.*;
/**
 *
 * @author Jhon
 */
public class EntregaEncomienda extends Servicio {

    private int cantidadProductos;
    private TipoEncomienda tipoEncomienda;

    public EntregaEncomienda(Ruta ruta, String fecha, int cantidadProductos, TipoEncomienda tipoEncomienda) {
        super(ruta, fecha);
        this.cantidadProductos = cantidadProductos;
        this.tipoEncomienda = tipoEncomienda;
    }

    public int getcantidadProductos() {
        return cantidadProductos;
    }

    public void setcantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public TipoEncomienda gettipoEncomienda() {
        return tipoEncomienda;
    }

    public void settipoEncomienda(TipoEncomienda tipoEncomienda) {
        this.tipoEncomienda = tipoEncomienda;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCantidad de productos: " + cantidadProductos + "\nTipo de encomienda: " + tipoEncomienda;
    }

    public static int crearServicioEncomienda(Cliente cliente_A) {
        int validarWhile = 0;
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
        TipoEncomienda tipo=tipoEncomienda();
        System.out.println("Ingrese la cantidad del producto enviado:");
        int cantidad = AcesoSistema.pedirDatosEnteros();
        sc.nextLine();
        String metodo = Servicio.metodo_pago();
        System.out.println("¿Desea generar el servicio? (si/no)");
        String confirmacion = sc.nextLine();
        String confir=confirmacion.toLowerCase();
        if (confir.equals("si")) {
            Servicio encomienda = new EntregaEncomienda(ruta, fecha, cantidad, tipo);
            String tipoVehiculo = "M";
            String conductor = encomienda.asignarconductor(tipoVehiculo);
            String linea = encomienda.getCodigo() + "," + cliente_A.getNombre() + "," + conductor + "," + encomienda.getRuta().getpuntoPartida() + "," + encomienda.getRuta().getpuntoLlegada() + "," + encomienda.getfecha() + "," + hora + ","+tipo+","+cantidad+","+ metodo + "," + encomienda.getvalorPagar();
            Archivos.EscribirArchivo("encomiendas.txt", linea);
            System.out.println("**=============Factura=============**\n" + encomienda.toString() + "\n\n");
            Servicio enco = (Servicio) encomienda;
            cliente_A.setListaServicio(enco);
            System.out.println("¿Desea Solicitar otro Servicio? (si/no): ");
            String validar = sc.nextLine();
            String vali = validar.toLowerCase();
            if (validar.equals("si")) {
                validarWhile = 1;
            } else if (validar.equals("no")) {
                validarWhile = 0;
            }

        } else {
            validarWhile = 1;
        }

        return validarWhile;
    }

    private static TipoEncomienda tipoEncomienda() {
        Scanner sc = new Scanner(System.in);
        int ingreso = 5;
        TipoEncomienda tipo = TipoEncomienda.MEDICAMENTOS;
        while (ingreso != 0) {
            System.out.println("Seleccione una opcion\n1.Medicamentos\n2.Documentos\n3.Ropa\nIngrese su opcion:");
            int op = AcesoSistema.pedirDatosEnteros();
            sc.nextLine();
            switch (op) {
                case 1:
                    ingreso = 0;
                    return tipo = TipoEncomienda.MEDICAMENTOS;
                    
                case 2:
                    ingreso = 0;
                    return tipo = TipoEncomienda.DOCUMENTOS;
                    
                case 3:
                    ingreso = 0;
                    return tipo = TipoEncomienda.ROPA;
                    
                default:
                    System.out.println("Opcion no valida. Vuelva a seleccionar\n");
                    ingreso = 5;
                    break;
            }
        }
        return tipo;
    }
}
