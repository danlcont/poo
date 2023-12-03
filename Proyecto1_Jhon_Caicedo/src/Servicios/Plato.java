/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

/**
 *
 * @author Jhon
 */
public class Plato {
    private String nombre;
    private double precio;
    private int codigoRestaurante;

    public Plato(String nombre, double precio, int codigoRestaurante) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigoRestaurante = codigoRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(int codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    @Override
    public String toString() {
        return "Plato{" + "nombre=" + nombre + ", precio=" + precio + ", codigoRestaurante=" + codigoRestaurante + '}';
    }
}
