/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Clase que representa un restaurante en la aplicación.
 * 
 * @author Jhon
 */
public class Restaurante {

    private String nombre;
    private int codigo;
    private ArrayList<Plato> listamenu = new ArrayList();

    public Restaurante(String nombre, int codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Plato> getListamenu() {
        return listamenu;
    }

    public void setListamenu(ArrayList<Plato> listamenu) {
        this.listamenu = listamenu;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "nombre=" + nombre + ", codigo=" + codigo + ", listamenu=" + listamenu + '}';
    }
    //Crea los menus que seran vistos por los usuarios cunado tomen la opcion 3
    public static ArrayList<Plato> crearMenu(String nombreArchivo, Restaurante restaurante) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        String Encabezado;

        ArrayList<Plato> listaPlato = new ArrayList();
        try {

            archivo = new File(nombreArchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            Encabezado = br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos;
                datos = linea.split(",");

                if (restaurante.getCodigo() == Integer.valueOf(datos[0])) {
                    Plato plato = new Plato(datos[1], Double.parseDouble(datos[2]), Integer.valueOf(datos[0]));
                    listaPlato.add(plato);

                }
            }
            restaurante.setListamenu(listaPlato);
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
        return restaurante.getListamenu();
    }
    //crea los objetos restaurantes que permitiran mostrar sus menus
    public static ArrayList<Restaurante> crearRestaurante(String nombrearchivo) {
        ArrayList<Restaurante> l_restaurante = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        try {
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String encabezado = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Restaurante r = new Restaurante(datos[1], Integer.parseInt(datos[0]));
                l_restaurante.add(r);
            }
        }catch (Exception e) {
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
        return l_restaurante;
    }
}