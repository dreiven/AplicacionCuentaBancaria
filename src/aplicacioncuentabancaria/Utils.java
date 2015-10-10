/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuentabancaria;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author WorksTation
 */
public class Utils {

// 2100 0813 61 0123456789 cuenta valida de prueba
    public static Boolean validarCuentaBancaria(String cuenta) {
//        String patron= "(\\d{20})";
        Pattern cp = Pattern.compile("\\d{4}+(\\s+)\\d{4}+(\\s+)\\d{2}+(\\s+)\\d{10}");
        Matcher m = cp.matcher(cuenta);
        if (m.matches()) {
// cuenta cumple el patrón (20 dígitos)
            String banco = cuenta.substring(0, 4);
            String sucursal = cuenta.substring(5, 9);
            String dC = cuenta.substring(10, 12);
            String CCC = cuenta.substring(13, 23);
            if (!(obtenerDigito("00" + banco + sucursal) == Integer.parseInt(String.valueOf(dC.charAt(0))))
                    || !(obtenerDigito(CCC) == Integer.parseInt(String.valueOf(dC.charAt(1))))) {
                return false;
            } else {
                return true;
            }
        }
        return false;

    }
//Metodo  para comprobar el digito de control es correcto asociado a la entidad ,oficina y cuenta bancaria introducidas en el CCC 
    public static int obtenerDigito(String valor) {
        Integer[] valores = new Integer[]{1, 2, 4, 8, 5, 10, 9, 7, 3, 6};

        Integer control = 0;
        for (int i = 0; i <= 9; i++) {
            control += Integer.parseInt(String.valueOf(valor.charAt(i))) * valores[i];
        }
        control = 11 - (control % 11);
        if (control == 11) {
            control = 0;
        } else if (control == 10) {
            control = 1;
        }
        return control;
    }

    public static void rellenarClientes(CuentaBancaria[] a) {
        //se crea array de nombres 
        String nombres[] = {"Petrucio Perez", "Pedro Ramirez", "Maria B Garcia"};
        //se crea array de CodigoCuentaCliente
        String CCC[] = {"0000 0000 00 0000000000", "1111 1111 11 1111111111", "2222 2222 22 2222222222"};
        //se crea array de int 
        Double saldo[] = {100.90, 200.34, 300.12};
        for (int i = 0; i < a.length; i++) {
            //se crean nuevos objetos cuenta bancaria con el nombre , el numero de cuenta y saldo declarados anteriormente
            a[i] = new CuentaBancaria(nombres[i], CCC[i], saldo[i]);

        }

        
    }

    public static void MenuPrincipal(CuentaBancaria[] b) {
        //se crea objeto scanner para solicitar una opcion al usuario en el menu
        Scanner scn = new Scanner(System.in);
        //se crea e inicializa una variable Boolean para controlar el bucle while 
        Boolean out = false;
        //se crea String opcion para almacenar la opcion elegida por el usuario en el menu
        String opcion = "0";
        //se crea e incicializa en 0 la variable int contador para iterar el array de CuentaBancaria b en la creacion de nuevos clientes
        int cont = 0;
        // 2100 0813 61 0123456789 cuenta de prueba correcta
        while (out == false) {
            System.out.println("__________________________________");
            System.out.println("____________MENU__________________");
            System.out.println("Elija una opcion");
            System.out.println("_________________");
            System.out.println("1.Informacion Cuentas");
            System.out.println("2.Crear cuenta");
            System.out.println("3.Acceder cuenta");
            System.out.println("4.Salir");
            System.out.println("_________________");
            opcion = scn.nextLine();
            System.out.println("******************");
            switch (opcion) {

                case "1":
                    //bucle for para iterar el array de Objetos CuentaBancaria
                    for (int i = 0; i < b.length; i++) {
                        //Se muestra a traves de una ventana emergente el nombre,cuenta bancaria y saldo de las cuentas actuales y se actualiza con los clientes nuevos 
                        JOptionPane.showMessageDialog(null, "Nombre: " + b[i].getNombre() + "\n" + "Nº de CCC: " + b[i].getCodigoCuenta() + "\n" + "Saldo Actual: " + b[i].getSaldoActual() + " €", "Informacion de la cuenta", JOptionPane.INFORMATION_MESSAGE);

                    }

                    break;

                case "2":
                    //se crea un objeto cuentaBancaria nuevo en blanco para guardar el nuevo cliente
                    CuentaBancaria cnt1 = new CuentaBancaria();
                    //solicitamos el nombre del titular de la cuenta como String a traves de un JOptionPane
                    String nombreApellidos = JOptionPane.showInputDialog(null, "Introduzca Nombre y Apellidos", "Bienvenido", JOptionPane.QUESTION_MESSAGE);
                    //si la comprobacion devuelve true almacenamos el valor de nombreApellidos en el atributo nombre del objeto cnt1
                    cnt1.setNombre(nombreApellidos);
                    //solicitamos el Codigo cuenta cliente como String a traves de un JOptionPane
                    String ccc = JOptionPane.showInputDialog(null, "Introduzca Codigo Cuenta Cliente ", "Bienvenido", JOptionPane.QUESTION_MESSAGE);
                    //llamamos al metodo validarCuentaBancaria y le pasamos el string ccc como parametro para comprobar si es numero valido
                    if (Utils.validarCuentaBancaria(ccc)) {
                        //si la comprobacion devuelve true almacenamos el valor de ccc en el atributo codigoCuenta del objeto cnt1
                        cnt1.setCodigoCuenta(ccc);
                        //bucle for para recorrer 1 a 1 
                        for (int i = 0; i <= cont; i++) {
                            //añadimos el contenido de cnt1 a la primera posicion del array b de clientes 
                            b[cont] = cnt1;
                            //se accede al metodo MenuCuenta con un parametro de array de objetos CuentaBancaria almacenados en el parametro b 
                            cnt1.MenuCuenta(b[cont]);
                            //aumenta en 1 la variable contador 
                            cont++;
                            break;

                        }

                    } else {
                        //si el usuario no introduce un numero bancario correcto se muestra mensaje de error y lo devuelve al menu 
                        JOptionPane.showMessageDialog(null, "Cuenta Incorrecta");
                    }

                    break;

                case "3":
                    //se llama al metodo buscar con el parametro recibido de MenuPrincipal
                    Buscar(b);

                    break;

                case "4":
                    out = true;
                    break;

                default:
                    System.out.println("Opcion invalida");
                    opcion = "0";
                    break;

            }

        }
    }

    public static void Buscar(CuentaBancaria[] b) {
//se crea objeto cuentaBancaria
        CuentaBancaria cnt2 = new CuentaBancaria();
//se solicita al usuario el nombre a buscar y se guarda en busquedaNombre
        String busquedaNombre = JOptionPane.showInputDialog(null, "Introduzca Nombre y Apellidos a Buscar");
        //se solicita al usuario el CCC a buscar y se guarda en busquedaCcc
        String busquedaCcc = JOptionPane.showInputDialog(null, "Introduzca CCC a buscar" + " 20 digitos");

        for (int i = 0; i < b.length; i++) {
            //comparamos el valor almacenado en busquedaNombre con el atributo nombre del array b,si es true avanza al 2º if
            if (busquedaNombre.equalsIgnoreCase(b[i].getNombre())) {
                //comparamos el valor almacenado en busquedaCcc con el atributo CodigoCuenta del array b, si es true avanza al siguiente menu
               if (busquedaCcc.equalsIgnoreCase(b[i].getCodigoCuenta())){
                //Llamamos al metodo  MenuCuenta y le pasamos el objeto CuentaBancaria que concuerda con el introducido por el usuario 
                cnt2.MenuCuenta(b[i]);
                break;
               
               
               }else{
                   //si no encuentra la cuenta muestra un mensaje de error
               JOptionPane.showMessageDialog(null, "Cuenta  No Encontrada");
                break;
               
               
               }
               
            } else {
                 //si no encuentra un nombre igual al enviado muestra un error 
                JOptionPane.showMessageDialog(null, "Cliente  No Encontrado");
                break;
            }

        }

    }

}
