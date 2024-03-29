package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.repositories.JornadaRepository;
import com.uniovi.repositories.SolicitudIntercambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * Servicios encargado de la gestión de las jornadas
 *
 * @author UO266047
 */

@Service
public class JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private SolicitudIntercambioRepository solicitudIntercambioRepository;

    /**
     * Obtiene las tareas de un empleado dado su id y una fecha
     * @param id del empleado
     * @param date fecha a buscar
     * @return Lista de tareas
     */
    public List<Tarea> getTareasByFechaEmpleado(Long id, Date date) {
        List<Tarea> jornadas = jornadaRepository.findTareaByFechaEmpleado(id, date);
        return jornadas;
    }


    /**
     * Reasigna una solicitud de intercambio
     * @param solicitud solicitud a reasignar
     */
    @Transactional
    public void reasignar(SolicitudIntercambio solicitud) {
        if(solicitud!=null&& solicitud.getId()!=null && solicitud.getNuevoEmpleado()!=null) {
            solicitudIntercambioRepository.asignarNuevoEmpleado(solicitud.getId(), solicitud.getNuevoEmpleado());
            realizarCambio(solicitud);
            solicitud.setEstado(Solicitud.EstadoSolicitud.REASIGNADA);
            solicitudIntercambioRepository.save(solicitud);
        }
    }

    /**
     * Realiza el cambio de jornadas entre los empleados de la solicitud de tipo intercambio
     * @param solicitudIntercambio
     */
    private void realizarCambio(SolicitudIntercambio solicitudIntercambio) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Jornada jornadaEmpleadoViejo = jornadaRepository.findJornadaByDateEmpleado(format.parse(
                    new java.sql.Date(solicitudIntercambio.getFecha().getTime()).toString()),
                    solicitudIntercambio.getEmpleado().getId()).get(0);

            Jornada jornadaEmpleadoNuevo = jornadaRepository.findJornadaByDateEmpleado(format.parse(
                    new java.sql.Date(solicitudIntercambio.getFechaDescanso().getTime()).toString()),
                    solicitudIntercambio.getNuevoEmpleado().getId()).get(0);

            //la jornada del empleado viejo pasa a ser la fecha de descanso
            jornadaRepository.cambiarJornadaEmpleado(solicitudIntercambio.getNuevoEmpleado(), jornadaEmpleadoViejo.getId());

            //la jornada del nuevo empelado pasa a ser la fecha
            jornadaRepository.cambiarJornadaEmpleado(solicitudIntercambio.getEmpleado(), jornadaEmpleadoNuevo.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    /**
     * Obtiene la jornada de un empleado dado su id
     * @param id del empleado
     * @return Lista de las jornadas de un empleado
     */
    public List<Jornada> findJornadaByEmpleado(Long id) {
        List<Jornada> jornadas = jornadaRepository.findJornadaByEmpleado(id);
        return jornadas;
    }

    /**
     * Busca las jornadas existentes en una fecha
     * @param date fecha a buscar
     * @return lista de las jornadas
     */
    public List<Jornada> findJornadaByFecha(Date date) {
        List<Jornada> jornadas = jornadaRepository.findJornadaByDate(date);

        return jornadas;
    }

    /**
     * Busca las jornadas de un empleado dada una fecha
     * @param date de la jornada
     * @param id del emleado
     * @return Lista de las jornadas
     */
    public List<Jornada> findJornadaByFechaEmpleado(Date date, Long id) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Jornada> jornada = new ArrayList<>();
        try {
            jornada = jornadaRepository.findJornadaByDateEmpleado(format.parse(new java.sql.Date(date.getTime()).toString()), id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jornada;

    }

    /**
     * Añade una nueva jornada
     * @param jornada jornada a añadir
     * @return Jornada añadida
     */
    public Jornada addJornada(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

    /**
     * Marca un día como libre
     * @param fecha fecha a marcar como dia libre
     * @param empleado empleado con nueva jornada libre
     */
    public void marcarDiaLibre(Date fecha, Empleado empleado) {

        List<Jornada> jornadas = jornadaRepository.findJornadaByDateEmpleado(fecha, empleado.getId());
        Jornada jornada;
        if (jornadas.size() > 0)
            jornada = jornadas.get(0);
        else
            jornada = new Jornada(fecha, empleado);
        jornada.setDiaLibre(true);
        jornada.setTareas(new HashSet<>());
        jornadaRepository.save(jornada);


    }

    /**
     * Elimina todas las jornadas
     */
    public void eliminarTodos(){
        jornadaRepository.deleteAll();
    }


    /**
     * Comprueba si existe una tarea para un empleado en una fecha concreta que coincida con el horario dado.
     * @param fecha fecha de la jornada
     * @param idEmpleado id del empleado
     * @param horaSalida hora de comienzo de la tarea
     * @param horaFin hora de fin de la tarea
     * @return true en caso de que exista, false en caso contrario
     */
    public boolean existeTarea(Date fecha, Long idEmpleado, LocalTime horaSalida, LocalTime horaFin) {
        List<Tarea> tareas=jornadaRepository.findTareaByFechaEmpleado(idEmpleado,fecha);
        for(Tarea t: tareas){
            LocalTime tInicio=t.getHoraSalida();
            LocalTime tFinal=t.getHoraFin();

            if( horaFin.isBefore(tInicio))
                return false;
            else if(horaSalida.isAfter(tFinal))
                return false;
            return true;
        }
        return false;
    }
}
