package com.uniovi.services;

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
import java.util.Optional;

@Service
public class JornadaService {

    @Autowired
    public JornadaRepository jornadaRepository;

    @Autowired
    public SolicitudIntercambioRepository solicitudIntercambioRepository;

    @Autowired
    public SolicitudSimpleRepository solicitudSimpleRepository;

    @Autowired
    public SolicitudRepository solicitudRepository;

    @Autowired
    public TareaRepository tareaRepository;


    public List<Tarea> getJornadaByDateAndEmpleoyee(Long id, Date date) {
        List<Tarea> jornadas = jornadaRepository.findByDateAndEmployee(id, new java.sql.Date(date.getTime()));
        return jornadas;
    }

    public void setSolicitud(SolicitudSimple solicitud) {
        solicitudSimpleRepository.save(solicitud);
    }

    public List<Solicitud> getAllSolicitudesPendientes() {
        List<Solicitud> solicitudes = new ArrayList<>();
        solicitudes.addAll(solicitudIntercambioRepository.findAllPending());
        solicitudes.addAll(solicitudSimpleRepository.findAllPending());
        return solicitudes;
    }

    @Transactional
    public void aceptarSolicitud(Long id) {
        solicitudRepository.aceptarSolicitud(id);
    }

    @Transactional
    public void rechazarSolicitud(Long id) {
        solicitudRepository.rechazarSolicitud(id);
    }

    @Transactional
    public void reasignar(SolicitudIntercambio solicitud) {
        solicitudRepository.reasignar(solicitud.getId());
    }

    public void addTarea(Tarea t) {
        tareaRepository.save(t);
    }

    public Stop findStopByTarea(Long id) {
        return tareaRepository.findStopByTarea(id).get(0);
    }

    public List<SolicitudIntercambio> findOthersSolicitudesPending(Long id) {
        return solicitudIntercambioRepository.findOthersSolicitudesPending(id);
    }

    public List<SolicitudIntercambio> findOwnSolicitudes(Long id) {
        return solicitudIntercambioRepository.findOwnSolicitudes(id);
    }

    public void addSolicitudIntercambio(SolicitudIntercambio solicitud) {
        solicitudIntercambioRepository.save(solicitud);
    }

    public List<String> chackCambioJornada(Long id, String fecha, String fechaDescanso) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaD = formato.parse(fecha);
            Date fechaDescansoD = formato.parse(fechaDescanso);
            List<String> jornadas = jornadaRepository.chackCambioJornada(
                    id, new java.sql.Date(fechaD.getTime()), new java.sql.Date(fechaDescansoD.getTime()));
            return jornadas;
        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }


    public void realizarCambio(Long idSolicitud, Long idNuevoEmpleado) {
        //FALTA ASIGNAR EL NUEVO EMPLEADO ID
        Optional<SolicitudIntercambio> optionalSolicitudIntercambio=solicitudIntercambioRepository.findById(idSolicitud);
        SolicitudIntercambio sIntercambio=optionalSolicitudIntercambio.get();
        //la jornada del empleado viejo pasa a ser la fecha de descanso
        jornadaRepository.cambiarJornadaEmpleadoViejo(new java.sql.Date(Long.parseLong(sIntercambio.getFecha())),
                new java.sql.Date(Long.parseLong(sIntercambio.getFechaDescanso())));
        //la jornada del nuevo empelado pasa a ser la fecha
        jornadaRepository.cambiarJornadaEmpleadoNuevo(new java.sql.Date(Long.parseLong(sIntercambio.getFecha())),
                new java.sql.Date(Long.parseLong(sIntercambio.getFechaDescanso())), idNuevoEmpleado);
    }
}
