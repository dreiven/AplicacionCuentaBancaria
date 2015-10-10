/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

/**
 *
 * @author WorksTation
 */
public class AplicacionCuentaBancaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 2100 0813 61 0123456789 cuenta valida de prueba

        CuentaBancaria cnt1 = new CuentaBancaria();

        //se crea array de objetos CuentaBancaria
        CuentaBancaria clientes1[] = new CuentaBancaria[3];
        Utils.rellenarClientes(clientes1);
        Utils.MenuPrincipal(clientes1);

    }

}
