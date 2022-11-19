package com.uniovi.services;

import com.uniovi.entities.Empleado;
import com.uniovi.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Servicios encargado de la gestión de los empleados
 *
 * @author UO266047
 */
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SolicitudService solicitudService;


    /**
     * Busca todos los empleados
     *
     * @return Lista de los empleados
     */
    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleados::add);
        return empleados;
    }

    /**
     * Añade un empleado
     *
     * @param empleado Empleado a añadir
     * @return Empleado añadido o null si no cumple los criterios
     */
    public void addEmpleado(Empleado empleado) {
        if (checkValues(empleado)) {
            enviarCorreo(empleado.getEmail(), "Contraseña", "Su contraseña es " + empleado.getPassword());
            empleado.setPassword(bCryptPasswordEncoder.encode(empleado.getPassword()));
            empleadoRepository.save(empleado);
        }
    }

    private void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        //La dirección de correo de envío
        String remitente = "TFGUO266047@gmail.com";
        //La clave de aplicación obtenida según se explica en este artículo:
        String claveemail = "iwomymzlgbxhcmqe";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", claveemail);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }

    }

    /**
     * Comprueba que no existe otro empleado en el sistema con ese nombre de usuario
     *
     * @param empleado
     * @return true en caso de que no exista, false en caso de que exista
     */
    private boolean checkValues(Empleado empleado) {
        if (findByUsername(empleado.getUsername()) != null && findByEmail(empleado.getEmail()) != null && findByDni(empleado.getDni()) != null)
            return false;
        return true;
    }

    /**
     * Busca un empleado por dni
     *
     * @param dni dni a buscar
     * @return empleado encontrado o null
     */
    public Empleado findByDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    /**
     * Busca un empleado por email
     *
     * @param email email a buscar
     * @return empleado encontrado o null
     */
    public Empleado findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }


    /**
     * Busca un empleado por nombre de usuario
     *
     * @param username Nombre de usuario del empleado
     * @return Empleado encontrado o null
     */
    public Empleado findByUsername(String username) {
        return empleadoRepository.findByUsername(username);
    }

    /**
     * Elimina un empleado dado su id
     *
     * @param idEmpleado id del empleado
     */
    public void deleteEmpleado(Long idEmpleado) {
        solicitudService.deleteNuevoEmpleado(idEmpleado);
        empleadoRepository.deleteById(idEmpleado);
    }

    /**
     * Busca empleados por nombre de usuario y contraseña
     *
     * @param username nombre de usuario
     * @param password contraseña
     * @return Empleado que coincide con las características
     */
    public Empleado findByUsernamePassword(String username, String password) {
        Empleado e= empleadoRepository.findByUsername(username);
        if(e.getPassword().equals(password))
            return e;
        else {
            if (bCryptPasswordEncoder.matches(password, e.getPassword())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Busca empleados por su id
     *
     * @param idEmpleado id del empleado
     * @return Empleado encontrado o null
     */
    public Empleado getEmpleadoById(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).get();
    }

    /**
     * Actualiza la contraseña del empleado
     *
     * @param empleado Empleado con la nueva contaseña
     */
    public void actualizarPassword(Empleado empleado) {
        empleado.setPassword(bCryptPasswordEncoder.encode(empleado.getPassword()));
        empleadoRepository.actualizarPassword(empleado.getId(), empleado.getPassword());
    }
}
