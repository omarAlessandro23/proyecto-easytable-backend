package com.example.easytable.Controllers;

import com.example.easytable.Dtos.UsuarioDTO;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService US;

    @GetMapping("/listar")
    public List<UsuarioDTO> listar() {
        return US.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertar(@RequestBody UsuarioDTO dto) {

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        US.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {

        Usuario ex = US.listId(id);
        if (ex == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con ese ID.");
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        u.setIdUsuario(id);
        US.update(u);

        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @DeleteMapping("/delete/{id}")
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
