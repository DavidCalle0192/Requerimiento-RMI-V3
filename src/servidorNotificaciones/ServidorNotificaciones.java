/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorNotificaciones;

import java.rmi.RemoteException;
import servidorNotificaciones.utilidades.UtilidadesConsola;
import servidorNotificaciones.utilidades.UtilidadesRegistroS;
import servidorNotificaciones.sop_rmi.ClsNotificaciones;
import servidorNotificaciones.vistas.VistaNotificaciones;

/**
 *
 * @author JhonMZ
 */
public class ServidorNotificaciones {
    public static void main(String args[]) throws RemoteException
    {   
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
                       
        System.out.print("Cual es el la dirección ip donde se encuentra  el rmiregistry:");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        Integer num;
        do{
            System.out.print("Cual es el número de puerto por el cual escucha el rmiregistry:");
            num = UtilidadesConsola.leerEntero();
            if(num!=null){
                if(num<1){
                    System.out.println("Digite un puerto valido");
                    num = null;
                }else{
                    numPuertoRMIRegistry = num;
                }
            }else{
                System.out.println("Digite un puerto valido");
            }
        }while(num == null);
        ClsNotificaciones objRemoto = new ClsNotificaciones();
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoNotificaciones");            
           
        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }   
    }
}
