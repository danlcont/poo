/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author Jhon
 */
public class Vehiculo {
    private String placa;
    private String modelo;
    private String marca;
    
    public Vehiculo(String placa,String modelo,String marca){
        this.marca=marca;
        this.modelo=modelo;
        this.placa=placa;
        
    }
    
    public String getplaca(){
        return placa;
    }
    public void setplaca(String placa){
        this.placa=placa;
    }
    public String geymodelo(){
        return modelo;
    }
    public void setmodelo(String modelo){
        this.modelo=modelo;
    }
    public String getmarca(){
        return marca;
    }
    public void setmarca(String marca){
        this.marca=marca;
    }
}
