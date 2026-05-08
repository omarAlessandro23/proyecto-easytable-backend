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
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @GetMapping("/listar")
    public List<NotificacionDTO> listar() {
        return NS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, NotificacionDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')or hasRole('USER')")
    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody NotificacionDTO dto) {

        ModelMapper m = new ModelMapper();
        Notificacion u = m.map(dto, Notificacion.class);
        NS.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Notificacion registrada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody NotificacionDTO dto) {

        Notificacion ex = NS.listId(id);
        if (ex == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una notificacion con ese ID.");
        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto, Notificacion.class);
        n.setIdNotificacion(id);
        NS.update(n);

        return ResponseEntity.ok("Notificacion actualizada correctamente");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Notificacion notificacion = NS.listId(id);
        if (notificacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una notificacion con el ID: " + id);
        }
        NS.delete(id);
        return ResponseEntity.ok("Notificacion eliminada correctamente.");
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    @GetMapping("/notificaciones-fecha/{fecha}")
    public ResponseEntity<?> buscarPorFecha(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha) {

        Collection<Object> lista = NS.findByFecha(fecha);

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen notificaciones para la fecha: " + fecha);
        }

        return ResponseEntity.ok(lista);
    }

}
