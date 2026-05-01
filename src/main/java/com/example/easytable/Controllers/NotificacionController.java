package com.example.easytable.Controllers;

import com.example.easytable.Dtos.NotificacionDTO;
import com.example.easytable.Dtos.NotificacionQuery1DTO;
import com.example.easytable.Dtos.ReservationDTO;
import com.example.easytable.Dtos.UsuarioDTO;
import com.example.easytable.Entities.Notificacion;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Serviceinterfaces.INotificacionService;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private INotificacionService NS;

    @GetMapping("/listar")
    public List<NotificacionDTO> listar() {
        return NS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, NotificacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody NotificacionDTO dto) {

        ModelMapper m = new ModelMapper();
        Notificacion u = m.map(dto, Notificacion.class);
        NS.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Notificacion registrada correctamente.");
    }

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

    @GetMapping("/notificaciones-fecha/{fecha}")
    public ResponseEntity<?> buscarPorFecha(@PathVariable LocalDate fecha) {

        List<NotificacionQuery1DTO> lista = NS.findByFecha(fecha).stream().map(x -> {
            NotificacionQuery1DTO dto = new NotificacionQuery1DTO();

            dto.setMensaje(x.getMensaje());
            dto.setLeido(x.getLeido());
            dto.setFecha(x.getFecha());
            dto.setIdUsuario(x.getUsuario().getIdUsuario());

            return dto;
        }).collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen notificaciones para la fecha: " + fecha);
        }

        return ResponseEntity.ok(lista);
    }

}
