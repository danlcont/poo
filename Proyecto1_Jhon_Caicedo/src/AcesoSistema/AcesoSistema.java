/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AcesoSistema;
import Enums.*;
import Servicios.*;
import Usuarios.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author ismael123
 */
public class AcesoSistema {

    public static void main(String[] args) throws IOException {
        //Se crean los archivos
        Archivos.EscribirArchivo("conductoresApp.txt", "nombre,codigoUsuario,licencia,estado,codigoVehiculo\nAlex,2739,238983,D,23\nJuan,3847,293487,D,12\nPedro,3474,828737,D,15");
        Archivos.EscribirArchivo("vehiculo.txt", "codigoVehiculo,placa,modelo,marca,tipo\n23,GSX3847,CX3,Mazda,A\n12,GSD8475,Aveo,Cherolet,A\n15,GAF9833,I10,Hyundai,M");
        Archivos.EscribirArchivo("viajes.txt", "numeroServicio,nombreCliente,nombreConductor,desde,hasta,fecha,hora,numeroPasajeros,tipoPago,valorPagar");
        Archivos.EscribirArchivo("encomiendas.txt", "numeroServicio,nombreCliente,nombreConductor,desde,hasta,fecha,hora,tipoEncomienda,cantidadProductos,tipoPago,valorPagar");
        Archivos.EscribirArchivo("restaurantes.txt", "codigoRestaurante,nombreRestaurante\n678,Mc Donals\n875,Naturissimo\n736,Sweet and Coffee\n822,Cafe de Tere");
        Archivos.EscribirArchivo("menus.txt", "codigoRestaurate,nombrePlato,precio\n678,mc bacon combo,4.82\n678,mc nuggets combo,6.00\n678,mc flurry frutilla,1.90\n678,mc flurry manjar,1.90\n678,mc flurry chocolate,1.90\n678,papas fritas,1.50\n875,combo 1,3.50\n875,combo 2,5.60\n875,combo 3,6.70\n736,porcion tres leches,4.50\n736,milkshake oreo,3.90\n736,porcion torta de chocolate,2.50\n822,Bolón mixto,5.00");
        Archivos.EscribirArchivo("delivery.txt", "numeroServicio,nombreCliente,nombreCondu,desde,desde,hasta,fecha,hora,numeroPedido,tipoPago,valorPagar");
        Archivos.EscribirArchivo("pedido.txt", "numeroPedido,codigoRestaurante,nombrePlato,precio");
        
        boolean validar = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inicio del sistema");

        while (validar == false) { //se pone un ciclo para que cuando ponga mal los datos ingrese denuevo
            System.out.println("Ingrese el usuario y contraseña");
            System.out.println("usuario: ");
            String Usuario = sc.nextLine();
            System.out.println("Contraseña: ");
            String Contraseña = sc.nextLine();
            if (IngresoSistema(Usuario.toLowerCase(), Contraseña.toLowerCase(), "usuarios.txt") == false || validardatos(Usuario) == false) {
                System.out.println("credenciales no validas");
            } else {
                System.out.println("Bienvenido al sistema");
                validar = true;
                Usuario User = Crear_usuario("usuarios.txt", Usuario);
                if (User.getTipo() == 'C') {
                    if (Cliente.validarcliente("Clientes.txt", User) == false) {
                        System.out.println("Cliente no registrado");
                        System.out.println("Ingrese su edad");
                        int edad = sc.nextInt();
                        System.out.println("Ingres el numero de tarjeta de credito");
                        String tarjeta = sc.next();
                        Cliente.registrar_cliente(edad, tarjeta, User.getNro_cedula(), "Clientes.txt");
                    }
                    System.out.println("Cliente registrado");
                    Cliente cliente_A = (Cliente) User;//Down casting
                    int validarWhile = 1;
                    
                    while (validarWhile != 0) {
                        mostrarMenuCliente();
                        System.out.println("Escoja una opcion:");
                        int op = pedirDatosEnteros();
                        switch (op) {
                            case 1:
                                System.out.println("/********SERVICIO TAXI********/");
                                validarWhile = ServicioTaxi.crearServicioTaxi(cliente_A);
                                
                                break;
                            case 2:
                                System.out.println("/********SERVICIO ENCOMIENDAS********/");
                                validarWhile = EntregaEncomienda.crearServicioEncomienda(cliente_A);

                                break;
                            case 3:
                                System.out.println("/********SERVICIO DELIVERY COMIDA********/");
                                validarWhile = ServicioDelivery.crearServicioDelivery(cliente_A);

                                break;
                            case 4:
                                System.out.println("/********CONSULTAR SERVICIO********/");
                                validarWhile = cliente_A.ConsultarServicioAsignado();
                                break;
                            default:
                                System.out.print("Opcion no valida. Vuelva a seleccionar.");
                                validarWhile = 1;
                                break;

                        }
                    }

                } else {
                    Conductor conductor_A = (Conductor) User;//Down casting
                    int validarWhile = 1;
                    while (validarWhile != 0) {

                        conductor_A.mostrarMenu();
                        System.out.println("Ingrese su opcion: ");
                        int op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                System.out.println("/********CONSULTAR SERVICIO********/");
                                validarWhile = conductor_A.ConsultarServicioAsignado();
                                break;
                            default:
                                System.out.println("Opcion no valida. Vuelva a seleccionar.");
                                validarWhile = 1;
                                break;
                        }
                    }

                }
            }
        }
    }

    public static boolean validardatos(String datos) {
        return datos.matches("[a-zA-z]*");
    }
    
    private static boolean validardatosEnteros(String datos) {
        int n = 0;
        boolean validar = datos.matches("[0-9]");
        return validar;
    }
    //Pide y valida si el valor ingresado es un entero
    public static int pedirDatosEnteros() {
        int n = 0;
        Scanner sc = new Scanner(System.in);
        while (n == 0) {
            String ops = sc.next();
            if (validardatosEnteros(ops) == false) {
                System.out.println("debe ingresar datos validos");
            } else {
                n = Integer.parseInt(ops);
            }

        }

        return n;
    }
    //Permite ingresar los datos user y contraseña
    private static boolean IngresoSistema(String user, String contraseña, String nombrearchivo) {
        boolean encontrado = false;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        String Encabezado;
        try {
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            Encabezado = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos;
                datos = linea.split(",");
                String usuario = datos[3];
                String contra = datos[4];
                if (usuario.equals(user) && contraseña.equals(contra)) {
                    encontrado = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (encontrado == true) {
            return true;
        } else {
            return false;
        }

    }

    public static void mostrarMenuCliente() {
        System.out.println("/********MENÚ********/");
        System.out.println("/*                  */");
        System.out.println("/********************/");
        System.out.println("1. Solicitar servicio de taxi");
        System.out.println("2. Solicitar servicio de encomienda");
        System.out.println("3. Solicitar servicio de comida");
        System.out.println("4. Consultar servicios");
    }

    public static void mostrarMenuConductor() {
        System.out.println("/********MENÚ********/");
        System.out.println("/*                  */");
        System.out.println("/********************/");
        System.out.println("1. Consultar servicio asignado");
    }
    //Crea un Usuario que puede ser un Cliente o Conductor, aqui hay polimorfismo
    public static Usuario Crear_usuario(String nombrearchivo, String User) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        String Encabezado;
        Usuario user_final = new Usuario();
        try {

            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            Encabezado = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos;
                datos = linea.split(",");
                EstadoConductor estado;
                if (User.equals(datos[3])) {
                    if (datos[6].charAt(0) == 'R') {

                        String info = Conductor.licenciaEstado("conductoresApp.txt", datos[1]);
                        String[] licenciaEstado = info.split(",");
                        if (licenciaEstado[1].equals("D")) {
                            estado = EstadoConductor.DISPONIBLE;
                        } else {
                            estado = EstadoConductor.OCUPADO;
                        }
                        String informacion = Conductor.tipo("vehiculo.txt", licenciaEstado[2]);
                        TipoVehiculo tipo = TipoVehiculo.AUTO;
                        if (informacion.equals("A")) {
                            tipo = TipoVehiculo.AUTO;
                        } else {
                            tipo = TipoVehiculo.MOTO;
                        }
                        Conductor conductor = new Conductor(datos[1], datos[2], datos[0], datos[5], datos[3], datos[4], licenciaEstado[0], estado, tipo, datos[6].charAt(0));
                        //Polimorfismo de asignacion
                        user_final = conductor;

                    } else {
                        Cliente cliente = new Cliente(datos[1], datos[2], datos[0], datos[5], datos[3], datos[4], 25, 56561561, datos[6].charAt(0));
                        //Polimorfismo de asignacion
                        user_final = cliente;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return user_final;
    }

}
