/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteHabitacion;


import clienteHabitacion.utilidades.UtilidadesConsola;
import clienteHabitacion.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import java.util.Scanner;
import servidorAlertas.sop_rmi.GestionPacienteInt;

/**
 *
 * @author usuario
 */
public class ClienteAdministrador {

    public static GestionPacienteInt objRemoto;
    

    /**
     * @param args the command line arguments
     */
    public void iniciarSesion(String direccionIpRMIRegistry, int numPuertoRMIRegistry) throws RemoteException {

       /* Scanner reader = new Scanner(System.in);
        int numPuertoRMIRegistry = 2020;
        String direccionIpRMIRegistry = "localhost";*/
/*
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = reader.nextLine();

        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = reader.nextInt();
*/
        objRemoto = (GestionPacienteInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoAsintomatico");
        //MenuPrincipal();
    }

    public GestionPacienteInt obtenerObjRemoto(){
        return objRemoto;
    }
            
    public static void MenuPrincipal() throws RemoteException {
        int opcion = 0;

        do {
            System.out.println("========Menu==========");
            System.out.println("1. meter #Registros ");
            System.out.println("2. sacar #Registros ");
            System.out.println("3. Salir");
            
          opcion = UtilidadesConsola.leerEntero();
          
          switch (opcion) {
            case 1:
                System.out.println("Entrando a #reg ");
                System.out.println("Ingrese el # ");
                Scanner leer = new Scanner(System.in);
                int n = leer.nextInt();
                objRemoto.establecerMaxPacientes(n);
                
            case 2:
                System.out.println("Buscando # max de REg");
                System.out.println("# es: " + objRemoto.obtenerMaxPacientes());
                 
                
            case 3: 
                System.out.println("Terminando...");
        }
        } while (opcion != 3);

       

        
    }
}
