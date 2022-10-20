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


    public List<Solicitud> findSolicitudByFechaEmpleado(String date, Long id) {
        List<Solicitud> solicitudes = new ArrayList<>();
        solicitudes.addAll(solicitudIntercambioRepository.findSolicitudIntercambioByFechaEmpleado(date, id));
        solicitudes.addAll(solicitudSimpleRepository.findSolicitudSimpleByFechaEmpleado(date, id));
        return solicitudes;
    }

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
                    fechaFormateada = format.parse(solicitud.getFecha());

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
//                solicitudRepository.aceptarSolicitud(solicitud.getId());

                //si es vacaciones --> marcar el rango como dias libres
            } else if (objectMapper.getSolicitudMapeada().getClass() == SolicitudVacaciones.class) {
                Date fechaInicio;
                Date fechaFinal;
                //= ((SolicitudVacaciones) solicitud).getFechaFinVacaciones();
                try {
                    fechaInicio = format.parse(solicitud.getFecha());
                    fechaFinal=format.parse(((SolicitudVacaciones) solicitud).getFechaFinVacaciones());
                    for (Date d = fechaInicio; d.toInstant().isBefore(fechaFinal.toInstant()); d = new Date(d.getTime() + UN_DIA_EN_MILISEGUNDOS)) {
                        jornadaService.marcarDiaLibre(d, solicitud.getEmpleado());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //marcamos la solicitud como aceptada
                solicitud.setEstado(Solicitud.EstadoSolicitud.ACEPTADA);
                solicitudRepository.save(solicitud);
//                solicitudRepository.aceptarSolicitud(solicitud.getId());
            }


        }
    }

    @Transactional
    public void setSolicitud(SolicitudSimple solicitud) {
        if (solicitud != null) {
            SolicitudMapper solicitudMapper = Mapper.convertirObjectSolicitud(solicitud);
            solicitudSimpleRepository.save((SolicitudSimple) solicitudMapper.getSolicitudMapeada());
        }
    }

    @Transactional
    public void rechazarSolicitud(Long id) {
        solicitudRepository.rechazarSolicitud(id);
    }

    public List<Solicitud> findOwnSolicitudes(Long id) {
        List<Solicitud> solicitudes = new ArrayList<>();
        solicitudes.addAll(solicitudIntercambioRepository.findOwnSolicitudesIntercambio(id));
        solicitudes.addAll(solicitudSimpleRepository.findOwnSolicitudesSimples(id));
        return solicitudes;
    }

    public List<SolicitudIntercambio> findOthersSolicitudesPendientes(Empleado empleado) {
        List<SolicitudIntercambio> solicitudesIntercambio = new ArrayList<>();

        if (empleado != null) {
            List<SolicitudIntercambio> solicitudesPending = solicitudIntercambioRepository.findOthersSolicitudesPendientes(empleado.getId(), empleado.getRole());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = null;
            Date fechaDescanso = null;

            for (SolicitudIntercambio s : solicitudesPending) {
                try {
                    fecha = sdf.parse(s.getFecha());
                    fechaDescanso = sdf.parse(s.getFechaDescanso());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<Jornada> jornadaDescanso = jornadaService.findJornadaByDateEmpleado(fechaDescanso, empleado.getId());
                if ((jornadaDescanso.size() > 0 && !jornadaDescanso.get(0).isDiaLibre()) &&
                        jornadaService.findJornadaByDateEmpleado(fecha, empleado.getId()).size() == 0)
                    solicitudesIntercambio.add(s);
            }
        }
        return solicitudesIntercambio;
    }

    public List<SolicitudVacaciones> findSolicitudesVacaciones(Long idEmpleado) {
        return solicitudVacacionesRepository.findByEmpleado(idEmpleado);
    }

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

    @Transactional
    public void addSolicitudIntercambio(Solicitud solicitud) {
        SolicitudMapper solicitudMapper = Mapper.convertirObjectSolicitud(solicitud);

        if (solicitudMapper.getSolicitudMapeada() instanceof SolicitudIntercambio)
            solicitudIntercambioRepository.save((SolicitudIntercambio) solicitudMapper.getSolicitudMapeada());
    }

    public void deleteAllSolicitudes() {
        solicitudRepository.deleteAll();
        solicitudIntercambioRepository.deleteAll();
        solicitudSimpleRepository.deleteAll();
        solicitudVacacionesRepository.deleteAll();
    }
}
