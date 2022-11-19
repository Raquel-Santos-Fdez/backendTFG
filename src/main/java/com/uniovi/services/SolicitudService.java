package com.uniovi.services;

import com.uniovi.config.Mapper;
import com.uniovi.config.SolicitudMapper;
import com.uniovi.entities.*;
import com.uniovi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servicios encargado de la gestión de las solicitudes
 *
 * @author UO266047
 */
@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private SolicitudIntercambioRepository solicitudIntercambioRepository;

    @Autowired
    private SolicitudSimpleRepository solicitudSimpleRepository;

    @Autowired
    private JornadaService jornadaService;

    @Autowired
    private SolicitudVacacionesRepository solicitudVacacionesRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private TareaService tareaService;


    /**
     * Acepta una solicitud simple o de vacaciones
     *
     * @param solicitud a aceptar
     */
    @Transactional
    public void aceptarSolicitud(Solicitud solicitud) {

        if (solicitud != null) {
            SolicitudMapper objectMapper = Mapper.convertirObjectSolicitud(solicitud);
            int UN_DIA_EN_MILISEGUNDOS = 1000 * 60 * 60 * 24;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            //si es simple --> marcar la jornada como dia libre --> eliminar todas las tareas que tuviese asociadas
            if (objectMapper.getSolicitudMapeada().getClass() == SolicitudSimple.class) {

                Date fechaFormateada;
                try {
                    fechaFormateada = format.parse(new java.sql.Date(solicitud.getFecha().getTime()).toString());

                    List<Tarea> tareas = jornadaService.getTareasByFechaEmpleado(solicitud.getEmpleado().getId(), fechaFormateada);
                    //eliminamos las tareas de la jornada libre
                    for (Tarea tarea : tareas) {
                        tareaService.eliminarTarea(tarea);
                    }
                    jornadaService.marcarDiaLibre(fechaFormateada, solicitud.getEmpleado());
                    //decrementamos el número de días libres del empleado
                    solicitud.getEmpleado().setnDiasLibres(solicitud.getEmpleado().getnDiasLibres() - 1);
                    empleadoRepository.save(solicitud.getEmpleado());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //marcamos la solicitud como aceptada
                solicitud.setEstado(Solicitud.EstadoSolicitud.ACEPTADA);
                solicitudRepository.save(solicitud);

                //si es vacaciones --> marcar el rango como dias libres
            } else if (objectMapper.getSolicitudMapeada().getClass() == SolicitudVacaciones.class) {
                Date fechaInicio;
                Date fechaFinal;
                try {
                    fechaInicio = format.parse(new java.sql.Date(solicitud.getFecha().getTime()).toString());
                    fechaFinal = format.parse(new java.sql.Date(((SolicitudVacaciones) solicitud).getFechaFinVacaciones().getTime()).toString());
                    for (Date d = fechaInicio; d.toInstant().isBefore(fechaFinal.toInstant()); d = new Date(d.getTime() + UN_DIA_EN_MILISEGUNDOS)) {
                        jornadaService.marcarDiaLibre(d, solicitud.getEmpleado());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //marcamos la solicitud como aceptada
                solicitud.setEstado(Solicitud.EstadoSolicitud.ACEPTADA);
                solicitudRepository.save(solicitud);
            }


        }
    }

    /**
     * Añade una solicitud simple
     *
     * @param solicitud a añadir
     */
    @Transactional
    public void setSolicitud(SolicitudSimple solicitud) {
        if (solicitud != null) {
            SolicitudMapper solicitudMapper = Mapper.convertirObjectSolicitud(solicitud);
            solicitudSimpleRepository.save((SolicitudSimple) solicitudMapper.getSolicitudMapeada());
        }
    }

    /**
     * Rechaza una solicitud dado su id
     *
     * @param idSolicitud id de la solicitud
     */
    @Transactional
    public void rechazarSolicitud(Long idSolicitud) {
        solicitudRepository.rechazarSolicitud(idSolicitud);
    }

    /**
     * Busca las solicitudes de intercambio y simples de un empleado dado su id
     *
     * @param idEmpleado id del empleado
     * @return Lista de las solicitudes
     */
    public List<Solicitud> findOwnSolicitudes(Long idEmpleado) {
        List<Solicitud> solicitudes = new ArrayList<>();
        solicitudes.addAll(solicitudIntercambioRepository.findOwnSolicitudesIntercambio(idEmpleado));
        solicitudes.addAll(solicitudSimpleRepository.findOwnSolicitudesSimples(idEmpleado));
        return solicitudes;
    }

    /**
     * Busca las solicitudes de intercambio enviadas por otros compañeros
     *
     * @param empleado que busca solicitudes
     * @return Lista de las solicitudes
     */
    public List<SolicitudIntercambio> findOthersSolicitudesPendientes(Empleado empleado) {
        List<SolicitudIntercambio> solicitudesIntercambio = new ArrayList<>();

        if (empleado != null) {
            List<SolicitudIntercambio> solicitudesPending = solicitudIntercambioRepository.findOthersSolicitudesPendientes(empleado.getId(), empleado.getRole());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha=null;
            Date fechaDescanso=null;

            for (SolicitudIntercambio s : solicitudesPending) {
                try {
                    fecha = format.parse(new java.sql.Date(s.getFecha().getTime()).toString());
                    fechaDescanso = format.parse(new java.sql.Date(s.getFechaDescanso().getTime()).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<Jornada> jornadaDescanso = jornadaService.findJornadaByFechaEmpleado(fechaDescanso, empleado.getId());
                if ((jornadaDescanso.size() > 0 && !jornadaDescanso.get(0).isDiaLibre()) &&
                        jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).size() == 0)
                    solicitudesIntercambio.add(s);
            }
        }
        return solicitudesIntercambio;
    }

    /**
     * Busca las solicitudes de vacaciones de un empleado
     *
     * @param idEmpleado id del empleado
     * @return lista de las solicitudes de vacaciones
     */
    public List<SolicitudVacaciones> findSolicitudesVacaciones(Long idEmpleado) {
        return solicitudVacacionesRepository.findByEmpleado(idEmpleado);
    }

    /**
     * Añade una solicitud de vacaciones
     *
     * @param solicitud a añadir
     */
    public void solicitarVacaciones(SolicitudVacaciones solicitud) {
        if (solicitud != null)
            solicitudVacacionesRepository.save(solicitud);
    }

    public List<Solicitud> getAllSolicitudesPendientes() {
        List<Solicitud> solicitudes = new ArrayList<>();
        solicitudes.addAll(solicitudSimpleRepository.findAllPending());
        solicitudes.addAll(solicitudVacacionesRepository.findAllPending());
        return solicitudes;
    }

    /**
     * Añade una solicitud de intercambio
     *
     * @param solicitud a añadir
     */
    @Transactional
    public void addSolicitudIntercambio(Solicitud solicitud) {
        SolicitudMapper solicitudMapper = Mapper.convertirObjectSolicitud(solicitud);

        if (solicitudMapper.getSolicitudMapeada() instanceof SolicitudIntercambio)
            solicitudIntercambioRepository.save((SolicitudIntercambio) solicitudMapper.getSolicitudMapeada());
    }

    /**
     * Elimina todas las solicitudes
     *
     */
    public void deleteAllSolicitudes() {
        solicitudRepository.deleteAll();
        solicitudIntercambioRepository.deleteAll();
        solicitudSimpleRepository.deleteAll();
        solicitudVacacionesRepository.deleteAll();
    }

    /**
     * Comprueba si existen solicitudes para la fecha y empleado señalados
     * @param fecha fecha a buscar
     * @param idEmpleado id del empleado a consultar
     * @return true en caso de que existan jornadas, false en caso contrario
     */
    public boolean existeSolicitud(Date fecha, Long idEmpleado) {
        List<Solicitud> solicitudes = new ArrayList<>();
        solicitudes.addAll(solicitudSimpleRepository.findNotRechazadas(fecha, idEmpleado));
        solicitudes.addAll(solicitudVacacionesRepository.findNotRechazadas(fecha, idEmpleado));
        solicitudes.addAll(solicitudIntercambioRepository.findNotRechazadas(fecha, idEmpleado));
        return solicitudes.size() != 0;
    }

    /**
     * Busca las solicitudes de vacaciones de un empleado con estado pendiente
     * @param idEmpleado id del empelado a consultar
     * @return lista de las solicitudes de vacaciones encontradas
     */
    public List<SolicitudVacaciones> findSolicitudesVacacionesPendientes(Long idEmpleado) {
        return solicitudVacacionesRepository.findSolicitudesVacacionesPendientes(idEmpleado);
    }

    /**
     * Comprueba si existen solicitudes de vacaciones para la fecha y empleado dados
     * @param fecha fecha a comprobar
     * @param idEmpleado empleado a comprobar
     * @return true en caso de que existan, false en caso contrario
     */
    public boolean existenVacacionesByFechaEmpleado(Date fecha, Long idEmpleado){
        List<SolicitudVacaciones> solicitudes=solicitudVacacionesRepository.existenVacacionesByFechaEmpleado(fecha, idEmpleado);
        return(solicitudes.size()!=0);
    }

    /**
     * Elimina el nuevo empleado asignado a las solicitudes de vacaciones
     * @param idEmpleado empleado a eliminar de las solicitudes
     */
    public void deleteNuevoEmpleado(Long idEmpleado) {
        List<SolicitudIntercambio> intercambios=solicitudIntercambioRepository.findByNuevoEmpleado(idEmpleado);
        for(SolicitudIntercambio si: intercambios){
            si.setNuevoEmpleado(null);
            si.setEstado(Solicitud.EstadoSolicitud.PENDIENTE);
            solicitudIntercambioRepository.save(si);
        }
    }
}
