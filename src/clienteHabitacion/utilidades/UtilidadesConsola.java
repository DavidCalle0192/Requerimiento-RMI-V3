/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteHabitacion.utilidades;

//import servidorAlertas.utilidades.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author David Calle
 */
public class UtilidadesConsola
{
	public static int leerEntero()
    {
    	String linea = "";
    	int opcion = 0;
    	boolean valido = false;
    	do
    	{
    		try
    		{
                    //System.out.println("Ingrese la opcion: ");
                    BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
                    linea = br.readLine();
                    opcion = Integer.parseInt(linea);
                    valido = true;
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error intente nuevamente...");
    			valido = false;
    		}
    	}while(!valido);
    	
    	return opcion;
    
    }
       /* 
        public static float leerDecimal(float f)
    {
    	String linea = "";
    	float opcion = 0;
    	boolean valido = false;
    	do
    	{
    		try
    		{
                    //System.out.println("Ingrese la opcion: ");
                    BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
                    linea = br.readLine();
                    opcion = Float.parseFloat(linea);
                    valido = true;
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error intente nuevamente...");
    			valido = false;
    		}
    	}while(!valido);
    	
    	return opcion;
    
    }
	*/
	public static String leerCadena()
    {
    	String linea = "";
    	boolean valido = false;
    	do
    	{
    		try
    		{
                    System.out.println("Ingrese la opcion: ");
                    BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
                    linea = br.readLine();
                    valido = true;
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error intente nuevamente...");
    			valido = false;
    		}
    	}while(!valido);
    	
    	return linea;
    
    }
}

