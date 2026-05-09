package com.example.easytable.Controllers;

import com.example.easytable.Dtos.RoleDTO;
import com.example.easytable.Dtos.UsuarioActualizarDTO;
import com.example.easytable.Dtos.UsuarioDTO;
import com.example.easytable.Entities.Role;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService US;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {

        List<Usuario> lista = US.list();

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen usuarios registrados.");
        }

        List<UsuarioDTO> response = lista.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> insertar(@RequestBody UsuarioDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body("El cuerpo de la solicitud está vacío.");
        }

        if (dto.getIdUsuario() <= 0) {
            return ResponseEntity.badRequest()
                    .body("El ID del usuario es inválido.");
        }

        Usuario existe = US.listId(dto.getIdUsuario());


        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("El usuario debe tener al menos un rol.");
        }

        for (RoleDTO role : dto.getRoles()) {

            String nombreRol = role.getRol();

            if (!nombreRol.equalsIgnoreCase("ADMIN") &&
                    !nombreRol.equalsIgnoreCase("OWNER") &&
                    !nombreRol.equalsIgnoreCase("USER")) {

                return ResponseEntity.badRequest()
                        .body("Rol inválido: " + nombreRol +
                                ". Solo se permiten ADMIN, OWNER o USER.");
            }
        }

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        US.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente.");
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioActualizarDTO dto) {

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body("El ID del usuario es inválido.");
        }

        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body("El cuerpo de la solicitud está vacío.");
        }

        Usuario ex = US.listId(id);

        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con ese ID.");
        }

        if (dto.getIdUsuario() != id) {
            return ResponseEntity.badRequest()
                    .body("El ID del usuario no coincide con la URL.");
        }

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        u.setIdUsuario(id);
        US.update(u);

        return ResponseEntity.ok("Usuario actualizado correctamente");
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Usuario usuario = US.listId(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + id);
        }
        US.delete(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

}
