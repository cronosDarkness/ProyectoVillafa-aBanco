/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Nombre del paquete
package proyectonewbanco;

import java.util.Scanner;

/**
 *
 * @author jtcas
 */
//Tipo y nombre de la clase
public class ProyectoNewBanco 
{
    //variable estatica de tipo numerico llamado selec
    static int selec;
    /**
     * @param args the command line arguments
     */
    //Metodo principal del programa
    public static void main(String[] args) 
    {
        // TODO code application logic here
        //variables de tipo numerico
        int valid=0, numTar, nip, opc;
        //Variable para leer los datos ingresados
        Scanner s = new Scanner(System.in);
        //Conexion con las demas clases
        Usuario u = new Usuario();
        ConexionDB c = new ConexionDB();
        Transaccion t = new Transaccion();
        Administracion a = new Administracion();
        //Mostramos un menu con dos opciones.
        System.out.println("Bienvenido al banco 'TuBanquito'");
        System.out.println("Selecciona una opcion para continuar: ");
        System.out.println("1.- Nuevo Usuario");
        System.out.println("2.- Iniciar Sesión");
        //Leemos la opcion del usuario
        valid = s.nextInt();
        //Mientras que la opcion ingresada sea igual a la primera, se repite el bucle
        while(valid==1)
        {
            //Accedemos a la clase adminstracion y mandamos a llamar al metodo crearRegsitro
            a.crearRegistro();
            //Mostramos un menu con dos opciones
            System.out.println("¿Quieres agregar otro usuario?");
            System.out.println("1.- Si");
            System.out.println("2.- No");
            //Guardamos la respuesta del usuario
            valid = s.nextInt();
        }
        //Ingresamos a la clase conexion y mandamos a llamar al metodo SeleccionarRegistro
        //pasando como parametro la clase Usuario y llamamos tambien a mostarRegistro
        System.out.println(c.selecionarRegistro(u)+"\n \n");
        System.out.println(c.mostrarRegistros());
        
        //Solicitamos el numero de tarjeta
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println("Inicio de sesión");
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println("Favor de ingresar el número de su tarjeta: ");
        //lo leemos y almacenamos
        numTar = s.nextInt();
        //Solicitamos el nip
        System.out.println("Favor de ingresar su número de identificación personal(NIP): ");
        //lo leemos y almacenamos
        nip = s.nextInt();
        //Llamos al metodo validarTarjeta de nuestra clase Transacciones, pasando como parametro
        //El numero de la tarjeta y el pin solicitados
        //Si el resultado es positvo ingresa
        if(t.validarTarjeta(numTar, nip)==true)
        {
            //Iniciamos un bucle
            do
            {
                //Asigmaos al valor numero estatico el valor del numero de la tarjeta
                selec=numTar;
                //Mostramos un menu con las siguientes opciones
                System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
                System.out.println("        Menu de 'TuBanquito'      ");
                System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
                System.out.println("Favor de escoger la opcion a realizar: ");
                System.out.println("0.- Visualizar fondos actuales");
                System.out.println("1.- Realizar un deposito");
                System.out.println("2.- Realizar un retiro");
                System.out.println("3.- Cerrar sesión");
                //Leemos y almacenamos la opcion ingresada
                opc = s.nextInt();
                //Comparamos la opcion ingresada
                switch(opc)
                {
                    //Si la opcion es la 0 ingresamos al metodo visualizarFondos de nuestra clase Transacciones
                    case 0:
                        t.visualizarFondos(selec);
                        break;
                    //Si la opcion es la 1 ingresamos al metodo depositar de nuestra clase Transacciones
                    case 1:
                        t.depositar(selec);
                        break;
                    //Si la opcion es la 2 ingresamos al metodo retirar de nuestra clase Transacciones
                    case 2:
                        t.retirar();
                        break;
                    //Si la opcion es la 3 cerramos el metodo swtich
                    case 3:
                        System.exit(0);
                        break;
                }
            }
            //Siempre que la opcion sea a 1 el bucle se repite
            while(1==1);  
        }
        //Si no mandamos un mensaje de erroneo
        else
        {
            System.out.println("NIP erroneo");
        }
    }
    
}
