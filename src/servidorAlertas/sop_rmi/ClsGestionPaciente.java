/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorAlertas.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorAlertas.dao.HistorialAlertaDAO;
import servidorAlertas.dto.HistorialDTO;
import servidorAlertas.dto.IndicadorDTO;
import servidorAlertas.dto.UsuarioDTO;
import servidorAlertas.utilidades.UtilidadesRegistroC;
import servidorAlertas.vistas.VistaLogAlertas;
import servidorNotificaciones.dto.AlertaDTO;
import servidorNotificaciones.sop_rmi.NotificacionesInt;

/**
 *
 * @author JhonMZ
 */
public class ClsGestionPaciente extends UnicastRemoteObject implements GestionPacienteInt{

    private ArrayList<UsuarioDTO> pacientes;
    private NotificacionesInt objRefRemNotificacion;
    private int MAX_PACIENTES = -1;
    private VistaLogAlertas guiAlertas;

    public ClsGestionPaciente() throws RemoteException {
        super();
        this.pacientes = new ArrayList<UsuarioDTO>();
        guiAlertas = new VistaLogAlertas();
        guiAlertas.setVisible(true);
    }
    
    @Override
    public synchronized String registrarPaciente(UsuarioDTO objPaciente) throws RemoteException {
        log("\nEjecutando registrarPaciente...");
        String respuesta = "";
    
            if(!pacienteRegistrado(objPaciente)){
            if(pacientes.size()<MAX_PACIENTES){
                if(pacientes.add(objPaciente)){
                    log("Paciente nuevo:");
                    log("Nombre:"+objPaciente.getNombres()+" "+objPaciente.getApellidos());
                    log("Identificacion: "+objPaciente.getTipo_id()+" "+objPaciente.getId());
                    log("Direccion:"+objPaciente.getDireccion());
                    log("Creando historial...");
                    crearHistorialPaciente(objPaciente.getId());
                    respuesta = "registrado";
                }else{
                    respuesta = "Error";
                    log("Error al registrar paciente");
                }
            }else{
                respuesta = "Limite_superado";
                log("No se pueden almacenar mas pacientes.");
            }
        }else{
            respuesta = "id_existente";
            log("El  paciente con id "+objPaciente.getId()+" ya esta registrado");
        }
        log(respuesta);
        return respuesta;
    }
    
    private void crearHistorialPaciente(int idPaciente){
        if(!HistorialAlertaDAO.existeHistorial(idPaciente)){    
            if(HistorialAlertaDAO.crearHistorial(idPaciente)){
                log("Se creo un nuevo historial de alertas para el paciente");
            }else{
                log("Hubo un error al crear historial de alertas para el paciente");
            }
        }else{
            log("El paciente ya tiene un historial");
        }
    }

    @Override
    public String enviarIndicadores(IndicadorDTO objIndicador){
        log("\nEjecutando enviarIndicadores...");
        String respuesta = "";
        
        int puntuacion = obtenerPuntuacion(objIndicador);
        log("Indicadores del paciente con id "+objIndicador.getIdPaciente());
        log("FC:"+objIndicador.getFrecuenciaCardiaca());
        log("FR:"+objIndicador.getFrecuenciaRespiratoria());
        log("ToC:"+objIndicador.getTemperatura());
        log("Puntuacion:"+puntuacion);
        
        if(puntuacion > 1){
            HistorialDTO objHistorial = new HistorialDTO(LocalDate.now(), LocalTime.now(), puntuacion);
            respuesta = "Se genera alerta";
            
            //Si el paciente no tiene un historial se procede a crearlo
            if(!HistorialAlertaDAO.existeHistorial(objIndicador.getIdPaciente()))HistorialAlertaDAO.crearHistorial(objIndicador.getIdPaciente());
            
            log("Enviando y almacenando alerta del paciente "+objIndicador.getIdPaciente()+"...");
            Stack<HistorialDTO> historial = HistorialAlertaDAO.obtenerUlt5Reg(objIndicador.getIdPaciente());
            HistorialAlertaDAO.agregarHistorial(objHistorial, objIndicador.getIdPaciente());

            UsuarioDTO objPaciente = buscarPaciente(objIndicador.getIdPaciente());
            
            AlertaDTO objAlerta = new AlertaDTO(historial, objIndicador, objPaciente,LocalDate.now(),LocalTime.now(),puntuacion);
            
            
            try {
                objRefRemNotificacion.enviarAlerta(objAlerta); 
            } catch (RemoteException ex) {
                Logger.getLogger(ClsGestionPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                //Callback
                objPaciente.getObjRemoto().enviarAlarma("Se genero una alerta por el cambio en los signos vitales  del paciente");
            } catch (RemoteException ex) {
                Logger.getLogger(ClsGestionPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            log("Alerta almacenada y enviada");
        }else{
            log("Continuar monitorización del paciente "+objIndicador.getIdPaciente());
            respuesta = "Continuar monitorización ";
        }
        
        return respuesta;
    }
    
    private int obtenerPuntuacion(IndicadorDTO objIndicadores){
        int puntuacion = 0;
        
        if(objIndicadores != null){
            if(objIndicadores.getFrecuenciaCardiaca() < 60 
                    || objIndicadores.getFrecuenciaCardiaca() > 80)puntuacion++;

            if(objIndicadores.getFrecuenciaRespiratoria() < 70 
                    || objIndicadores.getFrecuenciaRespiratoria() > 90)puntuacion++;

            if(objIndicadores.getTemperatura() < 36.2 
                    || objIndicadores.getTemperatura() > 37.2)puntuacion++;
        }
        return puntuacion;
       
    }   

    @Override
    public synchronized boolean establecerMaxPacientes(int num) throws RemoteException {
        log("\nEjecutando establecerMaxPacientes...");
        if(num>=1 && num<=5){
            this.MAX_PACIENTES = num;
            return true;
        }else{
        return false;
        }
        
    }

    @Override
    public int obtenerMaxPacientes() throws RemoteException {
        log("\nEjecutando obtenerMaxPacientes...");
        return this.MAX_PACIENTES;
    }
    
    /**
     * @brief permite saber si un paciente ya se encuentra registrado
     * @param objPaciente objeto que contiene la informacion del paciente
     * @return retorna true si el paciente ya esta registrado o false si no lo esta
     */
    private boolean pacienteRegistrado(UsuarioDTO objPaciente){
        //System.out.println("Buscando paciente...");
        boolean res = false;
        if(objPaciente != null){
            for (UsuarioDTO pacienteDTO : pacientes) {
                if(pacienteDTO.getId() == objPaciente.getId()){
                    res = true;
                    break;
                }
            }
        }
        return res;
    }
    
    private UsuarioDTO buscarPaciente(int idPaciente){
        //System.out.println("Buscando paciente...");
        UsuarioDTO objPaciente = null;
        if(idPaciente != 0){
            for (UsuarioDTO pacienteDTO : pacientes) {
                if(pacienteDTO.getId() == idPaciente){
                    objPaciente = pacienteDTO;
                    break;
                }
            }
        }
        return objPaciente;
    }
    
    public void consultarReferenciaRemotaDeNotificacion(String dir_Ip, int numPuerto)
    {
        System.out.println(" ");
        log("Desde consultarReferenciaRemotaDeNotificacion()...");
        objRefRemNotificacion = (NotificacionesInt) UtilidadesRegistroC.obtenerObjRemoto(dir_Ip, numPuerto, "ObjetoRemotoNotificaciones");
    }

    @Override
    public int numeroRegistros() throws RemoteException {
        log("\nEjecutando numeroRegistros...");
        return pacientes.size();
    }
    
    private void log(String log){
        System.out.println(log);
        guiAlertas.agregarLog(log);
    }
}
