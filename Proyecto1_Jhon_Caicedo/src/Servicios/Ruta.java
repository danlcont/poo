/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

/**
 *
 * @author Jhon
 */
public class Ruta {
    private String puntoPartida;
    private String puntoLlegada;
    
    public Ruta(String puntoPartida,String puntoLlegada){
        this.puntoPartida=puntoPartida;
        this.puntoLlegada=puntoLlegada;
    }
    
    public String getpuntoPartida(){
        return puntoPartida;
    }
    public void setpuntoPartida(String puntoPartida){
        this.puntoPartida=puntoPartida;
    }
    
    public String getpuntoLlegada(){
        return puntoLlegada;
    }
    public void setpuntoLlegada(String puntoLlegada){
        this.puntoLlegada=puntoLlegada;
    }
    
    @Override
    public String toString() {
        return "Desde: "+ puntoPartida + "\nHasta: " + puntoLlegada;
    }
}
