/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorAlertas.sop_rmi;

import servidorAlertas.dto.IndicadorDTO;
import servidorAlertas.dto.UsuarioDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author usuario
 */
public interface GestionPacienteInt extends Remote{
    
    public String registrarPaciente(UsuarioDTO objPaciente) throws RemoteException;  
    public String enviarIndicadores(IndicadorDTO objIndicador) throws RemoteException; 
    public boolean establecerMaxPacientes(int num)throws RemoteException;
    public int obtenerMaxPacientes()throws RemoteException;
    public int numeroRegistros()throws RemoteException;//valida el numero de pacientes registrados
    
}
