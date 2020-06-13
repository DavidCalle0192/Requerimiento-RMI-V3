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

    public ClsGestionPaciente() throws RemoteException {
        super();
        this.pacientes = new ArrayList<UsuarioDTO>();
    }
    
    @Override
    public synchronized String registrarPaciente(UsuarioDTO objPaciente) throws RemoteException {
        System.out.println("Ejecutando registrarPaciente...");
        
        String respuesta = "";
    
            if(!pacienteRegistrado(objPaciente)){
            if(pacientes.size()<MAX_PACIENTES){
                if(pacientes.add(objPaciente)){
                    System.out.println("Paciente nuevo:");
                    System.out.println("Nombre:"+objPaciente.getNombres()+" "+objPaciente.getApellidos());
                    System.out.println("Identificacion: "+objPaciente.getTipo_id()+" "+objPaciente.getId());
                    System.out.println("Direccion:"+objPaciente.getDireccion());
                    System.out.println("Creando historial...");
                    crearHistorialPaciente(objPaciente.getId());
                    respuesta = "registrado";
                }else{
                    respuesta = "Error";
                }
            }else{
                respuesta = "Limite_superado";
            }
        }else{
            respuesta = "id_existente";
        }
        System.out.println(respuesta);
        
        
        return respuesta;
    }
    
    private void crearHistorialPaciente(int idPaciente){
        if(!HistorialAlertaDAO.existeHistorial(idPaciente)){    
            if(HistorialAlertaDAO.crearHistorial(idPaciente))
                System.out.println("Se creo un nuevo historial de alertas para el paciente");
            else
                System.out.println("Hubo un error al crear historial de alertas para el paciente");
        }else{
            System.out.println("El paciente ya tiene un historial");
        }
    }

    @Override
    public String enviarIndicadores(IndicadorDTO objIndicador){
        System.out.println("Ejecutando enviarIndicadores...");
        
        String respuesta = "";
        
        int puntuacion = obtenerPuntuacion(objIndicador);
        System.out.println("Indicadores del paciente con id "+objIndicador.getIdPaciente());
        System.out.println("FC:"+objIndicador.getFrecuenciaCardiaca());
        System.out.println("FR:"+objIndicador.getFrecuenciaRespiratoria());
        System.out.println("ToC:"+objIndicador.getTemperatura());
        System.out.println("Puntuacion:"+puntuacion);
        
        if(puntuacion > 1){
            HistorialDTO objHistorial = new HistorialDTO(LocalDate.now(), LocalTime.now(), puntuacion);
            respuesta = "Se genera alerta";
            
            //Si el paciente no tiene un historial se procede a crearlo
            if(!HistorialAlertaDAO.existeHistorial(objIndicador.getIdPaciente()))HistorialAlertaDAO.crearHistorial(objIndicador.getIdPaciente());
            
            System.out.println("Enviando y almacenando alerta del paciente "+objIndicador.getIdPaciente()+"...");
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
            
            System.out.println("Alerta almacenada y enviada");
        }else{
            System.out.println("Continuar monitorización del paciente "+objIndicador.getIdPaciente());
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
        System.out.println("Ejecutando establecerMaxPacientes...");
        if(num>=1 && num<=5){
            this.MAX_PACIENTES = num;
            return true;
        }else{
        return false;
        }
        
    }

    @Override
    public int obtenerMaxPacientes() throws RemoteException {
        System.out.println("Ejecutando obtenerMaxPacientes...");
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
        System.out.println("Desde consultarReferenciaRemotaDeNotificacion()...");
        objRefRemNotificacion = (NotificacionesInt) UtilidadesRegistroC.obtenerObjRemoto(dir_Ip, numPuerto, "ObjetoRemotoNotificaciones");
    }

    @Override
    public int numeroRegistros() throws RemoteException {
        
        return pacientes.size();
    }
        
        
    
}
