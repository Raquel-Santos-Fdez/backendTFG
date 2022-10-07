package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public SolicitudVacacionesRepository solicitudVacacionesRepository;

    @Autowired
    public TareaRepository tareaRepository;


    public List<Tarea> getJornadaByDateAndEmpleoyee(Long id, Date date) {
        List<Tarea> jornadas = jornadaRepository.findTareaByDateAndEmpleado(id, new java.sql.Date(date.getTime()));
        return jornadas;
    }



    @Transactional
    public void reasignar(SolicitudIntercambio solicitud) {
        solicitudIntercambioRepository.asignarNuevoEmpleado(solicitud.getId(), solicitud.getNuevoEmpleado());
        realizarCambio(solicitud);
        solicitudRepository.reasignar(solicitud.getId());
    }

    private void realizarCambio(SolicitudIntercambio solicitudIntercambio) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date fechaDescanso = null;
        try {
            fecha = sdf.parse(solicitudIntercambio.getFecha());
            fechaDescanso = sdf.parse(solicitudIntercambio.getFechaDescanso());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Jornada jornadaEmpleadoViejo = jornadaRepository.findJornadaByDateEmpleado(new java.sql.Date(fecha.getTime()),
                solicitudIntercambio.getEmpleado().getId()).get(0);
        Jornada jornadaEmpleadoNuevo = jornadaRepository.findJornadaByDateEmpleado(new java.sql.Date(fechaDescanso.getTime()),
                solicitudIntercambio.getNuevoEmpleado().getId()).get(0);

        //la jornada del empleado viejo pasa a ser la fecha de descanso
        jornadaRepository.cambiarJornadaEmpleado(solicitudIntercambio.getNuevoEmpleado(), jornadaEmpleadoViejo.getId());

        //la jornada del nuevo empelado pasa a ser la fecha
        jornadaRepository.cambiarJornadaEmpleado(solicitudIntercambio.getEmpleado(), jornadaEmpleadoNuevo.getId());
    }


    public Estacion findStopByTarea(Long id) {
        return tareaRepository.findStopByTarea(id).get(0);
    }

    @Transactional
    public void addSolicitudIntercambio(Solicitud solicitud) {
        SolicitudMapper solicitudMapper = Mapper.convertirObjectSolicitud(solicitud);

        if (solicitudMapper.getSolicitudMapeada() instanceof SolicitudIntercambio)
            solicitudIntercambioRepository.save((SolicitudIntercambio) solicitudMapper.getSolicitudMapeada());
    }


    public List<Jornada> findJornadaByEmpleado(Long id) {
        List<Jornada> jornadas = jornadaRepository.findJornadaByEmpleado(id);
        return jornadas;
    }

    public List<Jornada> findJornadaByDate(Date date) {
        List<Jornada> jornadas = jornadaRepository.findJornadaByDate(date);

        return jornadas;
    }

    public List<Jornada> findJornadaByDateEmployee(Date date, Long id) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Jornada> jornada = new ArrayList<>();
        try {
            jornada = jornadaRepository.findJornadaByDateEmpleado(format.parse(new java.sql.Date(date.getTime()).toString()), id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jornada;

    }

    public Jornada addJornada(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

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
}
