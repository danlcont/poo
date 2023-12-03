/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import java.util.ArrayList;
import java.util.Scanner;
import AcesoSistema.*;
import Usuarios.*;

/**
 *
 * @author Jhon
 */
public class ServicioDelivery extends Servicio {

    private Pedido pedido;
    private String hora;

    public ServicioDelivery(Ruta ruta, String fecha, String hora, Pedido pedido) {
        super(ruta, fecha);
        this.pedido = pedido;
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return super.toString()+pedido.toString();
    }

    public static int crearServicioDelivery(Cliente c) {
        Scanner sc = new Scanner(System.in);
        String resp = null;
        int validarWhile = 0;


        System.out.println("Ingrese la fecha (dia/mes/año) : "); // Mejorar el tipo de dato
        String fecha_de = sc.nextLine();
        System.out.println("Ingrese la hora (hora:minutos): ");// Mejorar el tipo de dato
        String hora_de = sc.nextLine();
        System.out.println("Ingrese punto de partida: ");
        String p_inicio_de = sc.nextLine();
        System.out.println("Ingrese punto de llegada: ");
        String p_llegada_de = sc.nextLine();
        String metodo = Servicio.metodo_pago();

        ArrayList<Plato> carrito_momentaneo = new ArrayList<>();
        ArrayList<Restaurante> listaRestaurante;
        listaRestaurante = Restaurante.crearRestaurante("restaurantes.txt");
        do {
            System.out.print("\n");
            System.out.println("/******Lista de Restaurantes*******/");

            for (Restaurante r : listaRestaurante) {
                System.out.println((listaRestaurante.indexOf(r) + 1) + " - " + r.getnombre());
            }
            System.out.println("\nIngrese el restaurante del cual desea ordenar: ");
            int i_res = sc.nextInt();
            sc.nextLine();
            ArrayList<Plato> l_menu = Restaurante.crearMenu("menus.txt", listaRestaurante.get(i_res - 1));
            for (Plato p : l_menu) {
                System.out.println((l_menu.indexOf(p) + 1) + " - " + p.getNombre() + " - " + p.getPrecio());

            }
            System.out.println("\nIngrese el plato que desea agregar al carrito: ");
            int op_p = AcesoSistema.pedirDatosEnteros();
            sc.nextLine();
            carrito_momentaneo.add(l_menu.get(op_p - 1));

            System.out.println("¿Quiere añadir otro plato al carriot? (Si/No) ");
            String resp_v = sc.nextLine();
            resp = resp_v.toLowerCase();
        } while (resp.equals("si"));

        //Creacion del objeto Delivery y asignacion del valor a pagar
        System.out.println("¿Desea generar el servicio? (si/no)");
        String confirmacion_de = sc.nextLine();
        String confir = confirmacion_de.toLowerCase();
        if (confirmacion_de.equals("si")) {
            Pedido pedido = new Pedido(c.getNombre());
            pedido.setCarrito(carrito_momentaneo);

            double subtotal = 0;
            for (Plato p : pedido.getCarrito()) {
                subtotal = 0;
                subtotal += p.getPrecio();
            }
            Ruta ruta_de = new Ruta(p_inicio_de, p_llegada_de);
            Servicio Delivery = new ServicioDelivery(ruta_de, fecha_de, hora_de, pedido);
            Delivery.setvalorPagar(Delivery.calcularTotalServicios(metodo, subtotal));
            String conductor = Delivery.asignarconductor("M");
            String linea = Delivery.getCodigo() + "," + c.getNombre() + "," + conductor + "," + Delivery.getRuta().getpuntoPartida() + "," + Delivery.getRuta().getpuntoLlegada() + "," + Delivery.getfecha() + "," + hora_de + "," + pedido.getcodigo() + "," + metodo + "," + Delivery.getvalorPagar();
            Archivos.EscribirArchivo("delivery.txt", linea);
            String lineaPedido = pedido.getcodigo() + "," + "5657" + "," + conductor +","+"sdssddss";
            Archivos.EscribirArchivo("pedido.txt", lineaPedido);
            System.out.println("**=============Factura=============**\n"+ Delivery.toString());

            System.out.println("¿Desea Solicitar otro Servicio? (si/no): ");
            String validar = sc.nextLine();
            String vali=validar.toLowerCase();

            if (validar.equals("si")) {
                validarWhile = 1;
            } else if (validar.equals("no")) {
                validarWhile = 0;
            }
            validarWhile = 0;
        } else {
            validarWhile = 1;
        }

        sc.close();
        return validarWhile;
    }

   
}
