/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import java.util.ArrayList;

/**
 *
 * @author Jhon
 */
public class Pedido {
    private String codigo;
    private String nombre;
    private ArrayList<Plato> carrito;
    
    public Pedido(String nombre){
        this.codigo=String.valueOf((int)(Math.random()*1000000));;
        this.nombre=nombre;
        this.carrito= new ArrayList<>();
    }
    
    public String getcodigo(){
        return codigo;
    }
    public void setcodigo(String codigo){
        this.codigo=codigo;
    }

    public ArrayList<Plato> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Plato> carrito) {
        this.carrito = carrito;
    }
    
    
    public String getnombre(){
        return nombre;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }

    @Override
    public String toString() {
        return "\ncodigo: "+codigo+"\nnombre: "+ nombre+"\nSu pedido: " + carrito;
    }
    
    
            
}
