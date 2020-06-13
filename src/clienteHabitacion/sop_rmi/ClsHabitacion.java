/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clienteHabitacion.sop_rmi;

import clienteHabitacion.Vistas.MenuMedico;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author JhonMZ
 */
public class ClsHabitacion extends UnicastRemoteObject implements HabitacionInt{
    
    private MenuMedico guiMedico;
    
    public ClsHabitacion(MenuMedico guiMedico) throws RemoteException {
        super();
        this.guiMedico = guiMedico;
    }
    
    @Override
    public void enviarAlarma(String mensaje) throws RemoteException {
        System.out.println("recibiendo mensaje");
        guiMedico.mostraMensajeAlerta(mensaje);
    }
    
}
