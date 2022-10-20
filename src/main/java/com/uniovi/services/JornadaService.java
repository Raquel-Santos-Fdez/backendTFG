package com.uniovi.services;

import com.uniovi.config.Mapper;
import com.uniovi.config.SolicitudMapper;
import com.uniovi.entities.*;
import com.uniovi.repositories.*;
import com.uniovi.validators.ArgumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Service
public class JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private SolicitudIntercambioRepository solicitudIntercambioRepository;


    @Autowired
    private SolicitudRepository solicitudRepository;


    public List<Tarea> getTareasByFechaEmpleado(Long id, Date date) {
        List<Tarea> jornadas = jornadaRepository.findTareaByFechaEmpleado(id, date);
        return jornadas;
    }


    @Transactional
    public void reasignar(SolicitudIntercambio solicitud) {
        if(solicitud!=null&& solicitud.getId()!=null && solicitud.getNuevoEmpleado()!=null) {
            solicitudIntercambioRepository.asignarNuevoEmpleado(solicitud.getId(), solicitud.getNuevoEmpleado());
            realizarCambio(solicitud);
            solicitud.setEstado(Solicitud.EstadoSolicitud.REASIGNADA);
            solicitudIntercambioRepository.save(solicitud);
        }
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

    public List<Jornada> findJornadaByEmpleado(Long id) {
        List<Jornada> jornadas = jornadaRepository.findJornadaByEmpleado(id);
        return jornadas;
    }

    public List<Jornada> findJornadaByDate(Date date) {
        List<Jornada> jornadas = jornadaRepository.findJornadaByDate(date);

        return jornadas;
    }

    public List<Jornada> findJornadaByDateEmpleado(Date date, Long id) {

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

    public void eliminarTodos(){
        jornadaRepository.deleteAll();
    }
}
