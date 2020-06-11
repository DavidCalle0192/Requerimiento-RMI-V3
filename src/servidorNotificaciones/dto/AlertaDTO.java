/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorNotificaciones.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Stack;
import servidorAlertas.dto.HistorialDTO;
import servidorAlertas.dto.IndicadorDTO;
import servidorAlertas.dto.UsuarioDTO;

/**
 *
 * @author JhonMZ
 */
public class AlertaDTO implements Serializable{
    private Stack<HistorialDTO> historial;
    private IndicadorDTO indicadores;
    private UsuarioDTO paciente;
    private LocalDate fecha;
    private LocalTime hora;
    private int puntuacion;

    public AlertaDTO(Stack<HistorialDTO> historial, IndicadorDTO indicadores, UsuarioDTO paciente, LocalDate fecha, LocalTime hora, int puntuacion) {
        this.historial = historial;
        this.indicadores = indicadores;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.puntuacion = puntuacion;
    }

    public Stack<HistorialDTO> getHistorial() {
        return historial;
    }

    public void setHistorial(Stack<HistorialDTO> historial) {
        this.historial = historial;
    }

    public IndicadorDTO getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(IndicadorDTO indicadores) {
        this.indicadores = indicadores;
    }

    public UsuarioDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(UsuarioDTO paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
}
