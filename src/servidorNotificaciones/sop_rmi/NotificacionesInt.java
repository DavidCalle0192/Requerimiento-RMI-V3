/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorNotificaciones.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidorNotificaciones.dto.AlertaDTO;

/**
 *
 * @author JhonMZ
 */
public interface NotificacionesInt extends Remote{
    public void enviarAlerta(AlertaDTO objAlerta)throws RemoteException;
}
