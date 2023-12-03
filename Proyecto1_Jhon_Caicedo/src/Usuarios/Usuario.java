/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;
import Enums.*;


/**
 *
 * @author Jhon
 */

public class Usuario {
   protected  String nombre,apellido;
   protected  String nro_cedula, celular;
   protected String usuario,contraseña;
   protected char tipo;
   
   public Usuario(String nombre, String apellido, String nro_cedula, String celular, String usuario, String contraseña, char tipo){
       this.nombre= nombre;
       this.apellido= apellido;
       this.nro_cedula= nro_cedula;
       this.celular= celular;
       this.usuario= usuario;
       this.contraseña= contraseña;
       this.tipo=tipo;
       
       
   }
   public Usuario(){
       
   }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNro_cedula() {
        return nro_cedula;
    }

    public String getCelular() {
        return celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public char getTipo(){
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNro_cedula(String nro_cedula) {
        this.nro_cedula = nro_cedula;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
   
    
    public void mostrarMenu(){
        System.out.println("/********MENÚ********/");
        System.out.println("/*                  */");
        System.out.println("/********************/");
    }
    //Este metodo se debe sobreescribir
   public int ConsultarServicioAsignado(){
       return 1;
   }
    
   @Override
   public String toString(){
       return "[Nombre: "+getNombre()+" "+getApellido()+" Usuario: "+getUsuario()+" Nro.Cedula: "+getNro_cedula()+" Celular: "+getCelular()+"]";
   }
   
   
   
   
   
   
   
   
    
    
    
}

