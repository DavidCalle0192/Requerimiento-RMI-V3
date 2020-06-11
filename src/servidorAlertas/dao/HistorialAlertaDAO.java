/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorAlertas.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;
import servidorAlertas.dto.HistorialDTO;

/**
 *
 * @author JhonMZ
 */
public class HistorialAlertaDAO {
    
    /**
     * @brief Se encarga de crear un archivo de historial de alertas para el paciente.
     * Se crea un archivo con extension txt y de nombre historialDeAlertas<idPaciente>
     * @param idPaciente 
     * @return retorna true si el archivo se crea o false si el 
     * archivo ya existe o ocurre un error
     */
    public static boolean crearHistorial(int idPaciente){
        boolean res = false;
        if(!existeHistorial(idPaciente))
            res = ArchivoDAO.crearArchivo("historialDeAlertas"+idPaciente+".txt");
        return res;
    }
    
    /**
     * @brief Comprueba si ya existe un historial de akertas para el paciente
     * @param idPaciente 
     * @return retorna true si el paciente ya tiene un historial o false no no lo tiene
     */
    public static boolean existeHistorial(int idPaciente){
        return ArchivoDAO.existeArchivo("historialDeAlertas"+idPaciente+".txt");
    }
    
    public static ArrayList<HistorialDTO> obtenerRegistros(){
        ArrayList<HistorialDTO> historial = new ArrayList<HistorialDTO>();
        return historial;
    }
    
    public static boolean agregarHistorial(HistorialDTO objHistorial, int idPaciente){
        if(!existeHistorial(idPaciente))crearHistorial(idPaciente);
        //Fecha;Hota;Puntuacion
        String linea = objHistorial.getFecha().toString()+";"+objHistorial.getHora().toString()+";"+objHistorial.getPuntuacion();
        return ArchivoDAO.agregarLinea("historialDeAlertas"+idPaciente+".txt", linea);
    }
    
    public static Stack<HistorialDTO> obtenerUlt5Reg(int idPaciente){
        Stack<HistorialDTO> historial = new Stack<HistorialDTO>();
        int cont = 0;
        ArrayList<String> lineas = ArchivoDAO.obtenerLineas("historialDeAlertas"+idPaciente+".txt");
        if(lineas != null){
            ListIterator iterator = lineas.listIterator(lineas.size());   
            while(iterator.hasPrevious()){
                cont++;
                String linea = iterator.previous().toString();
                String tokens[] = linea.split(";");
                HistorialDTO objHistorialTemp = new HistorialDTO();
                objHistorialTemp.setFecha(LocalDate.parse(tokens[0]));
                objHistorialTemp.setHora(LocalTime.parse(tokens[1]));
                objHistorialTemp.setPuntuacion(Integer.parseInt(tokens[2]));
                historial.push(objHistorialTemp);
                if(cont>=5)break;
            }
        }
        return historial;
    }
}
