/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Nombre del paquete
package proyectonewbanco;

//Importaciones
import java.util.Scanner;

/**
 *
 * @author jtcas
 */

//Nombre de la clase
public class Transaccion 
{
    //Conexiones a la clase Conexion y Usuario
    ConexionDB c = new ConexionDB();
    Usuario u = new Usuario();
    
    //declaramos una variable de tipo númerico
    int numero;
    
    //Metodo para leer las letras ingresadas
    Scanner s = new Scanner(System.in);
    
    //creamos el metodo publico de tipo booleano llamado validarTarjeta
    //El metodo espera dos parametros de tipo numerico llamados "NumTar" y "Nip"
    public boolean validarTarjeta(int numTar, int nip)
    {
        //Asigmaos el numero de tarjeta utilizando el metodo set de nuestra clase Usuario
        u.setNumTarjeta(numTar);
        //Buscamos el registro mandando como parametro el numero de tarjeta metodo
        //seleccionar Registro dentro de nuestra clase conexion
        u= c.selecionarRegistro(u);
        //Validamos si el nip guardado es el mismo con el ingresado
        if(nip==u.getNip())
        {
            //Mostramos toda la cadena de informacion dentro de nuestra clase usuario con el metodo toString
            System.out.println(u.toString());
            numero = numTar;
            return true;
        }
        return false;
    }
    
    //creamos el metodo publico de tipo booleano llamado validarFondo
    //El metodo espera un parametro de tipo numerico llamado "Cant"
    public boolean validarFondo(int cant)
    {
        Usuario use = new Usuario();
        //Asigamos el numero de tarjeta
        use.setNumTarjeta(numero);
        //Selecionamos al usario mandado como parametro el numero de tarjeta
        use = c.selecionarRegistro(use);
        //Si la cantidad ingresada es menor a los fondos
        if(cant<=use.getFondos())
        {
            //Mandamos a llamar al metodo actualizarFondo de la clase conexion
            //con los parametros del ID del usuario y la cantidad
            c.actualizarFondo(use.getId(), (0-cant));
            return  true;
        }
        return  false;
    }
    
    //creamos el metodo publico de tipo void llamado depositar
    //El metodo espera un parametro de tipo numerico llamado numT
    public void depositar(int numT)
    {
        //Pedimos la cantidad y la almacenamos
        System.out.println("Cantidad a depositar: ");
        int cant = s.nextInt();
        //Mandamos a llamar al metodo "ActualizarFondo" dentro de la clase
        //conexion con los parametros esperados que es el ID del usuario y la cantidad
        c.actualizarFondo(u.getId(), cant);
        //Mostrados los datos
        System.out.println("Deposito de "+cant+" hecho correctamente");
    }
    
    //Creamos un metodo de tipo void llamado Retirar
    public void retirar()
    {
        //Solicitamos la cantidad
        System.out.println("Ingrese la cantidad que desea retirar: ");
        //Leemos y guardamos la cantidad
        int cant = s.nextInt();
        //Llamados al metodo validarFondo para ver los fondos esperando un true para poder ingresar
        if(validarFondo(cant)==true)
        {
            System.out.println("\n Retiro exitoso");
        }
        else
        {
            //Mostramos el siguiente menu
            System.out.println("Saldo insuficiente");
            System.out.println("¿Desea intentarlo de nuevo?");
            System.out.println("1.- Si");
            System.out.println("2.- No");
            //Leemos la opcion ingresada por el usuario
            int op = s.nextInt();
            //Comparamos la opcion, si es igual a 1 entra
            if(op==1)
            {
                //volvemos a llamar el metodo
                retirar();
            }
        }
    }
    
    //creamos el metodo publico de tipo void llamado visualizarFondos
    //El metodo espera un parametro de tipo númerico llamado "numTar"
    public void visualizarFondos(int numTar)
    {   Usuario user = new Usuario();
        //Mandamos el parametro recibido al metodo set de la variable numTarjeta dentro de la clase Usuario
        user.setNumTarjeta(numTar);
        //Seleccionamos el registro mandamo como parameotro el numero de tarjeta
        user = c.selecionarRegistro(user);
        //Lo mostramos en consola
        System.out.println("\n Fondo actual: "+user.getFondos());
    }
}
