/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

import java.awt.Component;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author WorksTation
 */
public class CuentaBancaria implements Metodos {

    String nombre;
    String codigoCuenta;
    Double saldoActual = 0.0;
//constructor de la clase Cuenta Bancaria acepta 3 parametros de creacion

    public CuentaBancaria(String nombre, String codigoCuenta, Double saldoActual) {
        this.nombre = nombre;
        this.codigoCuenta = codigoCuenta;
        this.saldoActual = saldoActual;
    }
//constructor de la clase Cuenta Bancaria sin parametros de creacion

    public CuentaBancaria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    @Override
    //metodo de interfaz Metodos  sobrecargado para mostrar el saldo actual del objetos Cuenta Bancaria
    public void ObtencionSaldo(CuentaBancaria a) {
        JOptionPane.showMessageDialog(null, "El saldo actual de la cuenta es: " + a.getSaldoActual() + " €");
//        System.out.println("" + a.getSaldoActual()+" €");

    }

    @Override
    //metodo de interfaz Metodos sobrecargado para modificar el deposito actual de la cuenta bancaria
    public void GestionDeposito(CuentaBancaria a) {
        String opcion = "0";// 2100 0813 61 0123456789
        Boolean cond = false;
        while(!cond){
        System.out.println("__________________________________");
        System.out.println("___________Gestión Deposito_______");

        System.out.println("1.Realizar retirada de efectivo");
        System.out.println("2.Realizar transferencia");
        Scanner scn = new Scanner(System.in);
        opcion = scn.nextLine();
        switch (opcion) {

            case "1":
                Double numeroRestar=0.0;
                String restar = JOptionPane.showInputDialog("Cuanto desea retirar?", "\n" +a.getSaldoActual());           
                numeroRestar = Double.parseDouble(restar);
                numeroRestar = a.getSaldoActual() - numeroRestar;
                a.setSaldoActual(numeroRestar);
                System.out.println("Saldo retirado correctamente ");
                System.out.println("Saldo restante en cuenta :" + a.getSaldoActual()+" €");
                opcion = "0";
            case "2":
                
                break;
                
            default:
                cond = true;
                break;

        }
        }
    }

    @Override
    //GESTION DE INGRESO INCORRECTA NO SUMA EL NUEVO RESULTADO
    //metodo de interfaz Metodos sobrecargado para añadir al deposito actual de la cuenta bancaria
    public void GestionIngreso(CuentaBancaria a) {
        //declaramos variable para sumar el saldo ingresado al actual
        Double saldo;
        //declaramos varibale para almacenar el valor introducido por el usuario
        String ingreso;
        //solicitamos a traves de un showInputDiaqlog el valor a ingresar en la cuenta 
        ingreso = JOptionPane.showInputDialog("Cuanto desea ingresar", saldoActual);
        //casteamos el ingreso String a double en la variable saldo
        saldo = Double.parseDouble(ingreso);
        //sumamos en la variable saldo, el saldo actual de la cuenta
        saldo = a.getSaldoActual() + saldo;
        //le añadimos al saldo actual el saldo total
        a.setSaldoActual(saldo);

    }

    @Override
    //metodo de interfaz Metodos sobrecargado para mostrar la informacion de nombre ,CCC y saldo del objeto  CuentaBancaria
    public void InfoCuenta(CuentaBancaria a) {

        System.out.println("Nombre: " + a.getNombre());
        System.out.println("CCC: " + a.getCodigoCuenta());
        System.out.println("Saldo: " + a.getSaldoActual().floatValue() + " €");

    }

    //metodo de interfaz Metodos sobrecargado para mostrar el menu de opciones despues de introducir una cuenta valida
    public static void MenuCuenta(CuentaBancaria a) {
//se delcara objeto Scanner para almacenar la opcion elegida por el usuario
        Scanner scn = new Scanner(System.in);
        //se declara la variable opcion como string para utilizarla en el switch
        String opcion;
        //se declara variable Boolean salir en false 
        Boolean salir = false;
        // 2100 0813 61 0123456789
        //bucle while con salir en false mostrando el menu con opciones por pantalla
        while (salir != true) {
            System.out.println("__________________________________");
            System.out.println("____________MENU__________________");

            System.out.println("1.Informacion Cuenta");
            System.out.println("2.Gestionar Deposito");
            System.out.println("3.Realizar Ingreso");
            System.out.println("4.Obtencion de Saldo");
            System.out.println("5.Menu anterior");
            System.out.println("___________________________________");
            //almacenamos oppcion el valor que nos indica el usuario
            opcion = scn.nextLine();
            //a partir del valor de opcion el switch pasa a un case u otro
            switch (opcion) {

                case "1":
                    //llama al metodo InfoCuenta con el parametro a de objeto CuentaBancaria para mostrar informacion acerca de la cuenta
                    a.InfoCuenta(a);
                    break;

                case "2":
                 
                    a.GestionDeposito(a);

                    break;

                case "3":
                    //llama al metodo GestionIngreso con un parametro de cuentaBancaria
                    a.GestionIngreso(a);
                    break;
                case "4":
                    a.ObtencionSaldo(a);
                    break;
                case "5":
                    //la variable salir se iguala a true para salir del bucle while y acceder de nuevo al menu anterior
                    salir = true;
                    break;

                default:
                    System.out.println("Opcion Incorrecta");
//                    salir = true;
                    break;

            }

        }
    }

}
