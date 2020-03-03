/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Nombre del paquete
package proyectonewbanco;

//Importaciones
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author jtcas
 */
public class Administracion 
{
    //Generamos la conexión a la clase usuario y conexion
    Usuario u = new Usuario();
    ConexionDB c = new ConexionDB();
    
    public void crearRegistro()
    {
        //Metodo para leer el texto ingresado
        Scanner s = new Scanner(System.in);
        //Variables de tipo texto
        String cadena="",fechaN,salto,dato, datoF="4715";
        //Valores de tipo numerico
        int  result, cont=0,valTar1,valTar2,valTar3;
        
        //Realizamos un numero aleatorio, que será nuestro valor ID del usuario a registrarse
        int num = (int)(Math.random()*(855-25+1)+25);
        u.setId(num);       
        
        //Pedimos que ingresen los datos para dar de alta a un nuevo usuario
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        System.out.println("Usuario nuevo" );
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°° \n");
        System.out.println("Favor de ingresa los siguientes datos:" );
        
        //Solicitamos el nombre completo, lo guardamos y almacenamos
        System.out.println("Nombre completo:");
        u.setNombreCompleto(s.nextLine());
        
        //Solicitamos la fecha de nacimiento en un formato especifido y la guardamos
        System.out.println("Por favor ingresa tu fecha de nacimiento DD/MM/YYYY");
        fechaN=s.nextLine();
        //Usamos la libreria "DateTimeFormartter" para obtener la fecha actual
        DateTimeFormatter date=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate FechaN=LocalDate.parse(fechaN, date);
        LocalDate FechaA=LocalDate.now();
        //Calculamos la edad del usuario comparando la fecha actual con la fecha ingresada y la guardamos
        Period periodo = Period.between(FechaN, FechaA);
        result = Integer.parseInt(periodo.getYears()+"");
        //Verificamos el resultado de la comparacion, que vendria siendo la edad del usuario
        //En caso de que sea menor de edad se le solicitara el nombre de un tutor, si no, se guardara
        if(result>18)
        {
            u.setTutor("Null");
            u.setEdad(result);
        }
        else
        {
            System.out.println("Esta agregando a un menor de edad, se requiere de un tutor");
            System.out.println("Favor de ingresar el nombre del tutor:");
            u.setTutor(s.nextLine());
            u.setEdad(result);
        }
        
        //Solicitamos el número de identificacion personal, lo almacenamos y lo guardamos
        System.out.println("Ingrese un NIP:");
        u.setNip(s.nextInt());
        
         //Generamos los valores aleatorios de la tarjeta
        valTar1 = (int)(Math.random()*(9999-1000+1)+1000);
        valTar2 = (int)(Math.random()*(9999-1000+1)+1000);
        valTar3 = (int)(Math.random()*(9999-1000+1)+1000);
        
        //Agregamos los valores generados aleatorios más el dato fijo y mostramos el numero de tarjeta
        salto=Integer.toString(valTar1+valTar2+valTar3);
        System.out.println("El número de su tarjeta es: "+"\n"+datoF+salto);
        u.setNumTarjeta(Integer.parseInt(datoF+salto));
        
        //Solicitamos el fondo inicial de nuestra tarjeta, lo almacenamos y lo guardamos
        System.out.println("Fondo inicial: ");
        u.setFondos(s.nextInt());
        
        //insertamos todos los datos a la base de datos orientada a objetos
        c.insertarRegistro(u);
        System.out.println("Datos del usuario ingresados y almacenados correctamente");
    }
}
