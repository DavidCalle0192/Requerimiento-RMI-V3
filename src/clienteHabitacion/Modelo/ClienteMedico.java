/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteHabitacion;


import clienteHabitacion.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import servidorAlertas.dto.IndicadorDTO;
import servidorAlertas.dto.UsuarioDTO;
import servidorAlertas.sop_rmi.GestionPacienteInt;

/**
 *
 * @author usuario
 */
public class ClienteMedico {
    
    public static IndicadorDTO inidicador;
    public static GestionPacienteInt objRemoto;
    private static int cont = 0;
    /**
     * @param args the command line arguments
     */
    public  void iniciarSesion( String direccionIpRMIRegistry, int numPuertoRMIRegistry)throws RemoteException {
        // TODO code application logic here
        objRemoto = (GestionPacienteInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoAsintomatico");
        System.out.println("valor: "+objRemoto.obtenerMaxPacientes());
    }
    
    /*
    public GestionPacienteInt obtenerObjRemoto(){
        return objRemoto;
    }*/
    
     
     
     public IndicadorDTO iniciarSeguimiento(int id) {
            inidicador = new IndicadorDTO(0,0,0,id);
            
                     Random fcr = new Random();
                    inidicador.setFrecuenciaCardiaca((int) (50 + fcr.nextFloat() * (90 - 50)));
                    //listaPaciente.get(i).setListaIndicadores(objIndicador);
                    
                    Random frr = new Random();
                    inidicador.setFrecuenciaRespiratoria((int) (60 + frr.nextFloat() * (100 - 60)));
                    //listaPaciente.get(i).setListaIndicadores(objIndicador);
                    
                    Random ter = new Random();
                    inidicador.setTemperatura((float) (35.2 + ter.nextFloat() * (38.2 - 35.2)));
                    //listaPaciente.get(i).setListaIndicadores(objIndicador);
     
                    return inidicador;
    }
}
