package aplicacioncuentabancaria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WorksTation
 */

//INterface para almacenar los metodos a utilizar
public interface Metodos {
    
    
    
    //metodo para mostrar el saldo actual de la cuenta
    public void ObtencionSaldo( CuentaBancaria a);
    
    //metodo para la modificacion del deposito actual de la cuenta 
    public void GestionDeposito(CuentaBancaria a);
    //metodo para el aumento del saldo actual
    public void GestionIngreso(CuentaBancaria a);
    
    //metodo para mostrar informacion de la cuenta detallada
    public void InfoCuenta(CuentaBancaria a);
    //metodo para mostrar el menu de cuenta 
   
    
    
}
