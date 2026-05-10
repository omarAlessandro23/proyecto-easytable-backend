package com.example.easytable.Controllers;

import com.example.easytable.Dtos.NotificacionDTO;
import com.example.easytable.Dtos.NotificacionQuery1DTO;
import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Serviceinterfaces.INotificacionService;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private INotificacionService NS;

    @Autowired
    private IUsuarioService US;

    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {

        List<Notificacion> lista = NS.list();

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen notificaciones registradas");
        }
        List<NotificacionDTO> response = lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, NotificacionDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody NotificacionDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body("El cuerpo de la solicitud está vacío");
        }

        if (dto.getIdUsuario() <= 0) {
            return ResponseEntity.badRequest()
                    .body("El idUsuario es obligatorio o inválido");
        }
        ModelMapper m = new ModelMapper();
        Notificacion u = m.map(dto, Notificacion.class);
        NS.insert(u);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Notificacion registrada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody NotificacionDTO dto) {
        if (id == null || id <= 0 ) {
            return ResponseEntity.badRequest()
                    .body("El ID de la notificación es inválido.");
        }

        Notificacion ex = NS.listId(id);

        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una notificación con ID: " + id);
        }

        if (dto.getIdUsuario() <= 0) {
            return ResponseEntity.badRequest()
                    .body("El idUsuario es inválido.");
        }

        boolean usuarioExiste = US.existsById(dto.getIdUsuario());

        if (!usuarioExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con ID: " + dto.getIdUsuario());
        }

        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto, Notificacion.class);

        n.setIdNotificacion(id);

        NS.update(n);

        return ResponseEntity.ok("Notificación actualizada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        // Validar ID inválido
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("El ID de la notificación es inválido.");
        }

        // Buscar si existe
        Notificacion notificacion = NS.listId(id);

        // Si no existe, enviar mensaje
        if (notificacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una notificación con el ID: " + id);
        }

        // Eliminar
        NS.delete(id);

        return ResponseEntity.ok("Notificación eliminada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @GetMapping("/buscar-fecha/{fecha}")
    public ResponseEntity<?> buscarPorFecha(
            @PathVariable LocalDate fecha) {

        List<Object[]> lista = NS.findByfecha(fecha);

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen notificaciones para la fecha: " + fecha);
        }

        List<NotificacionDTO> response = lista.stream().map(x -> {

            NotificacionDTO dto = new NotificacionDTO();

            dto.setIdNotificacion(((Number) x[0]).intValue());
            dto.setFecha(LocalDate.parse(x[1].toString()));
            dto.setMensaje((String) x[2]);

            return dto;

        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
