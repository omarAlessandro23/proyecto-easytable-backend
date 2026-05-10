package com.example.easytable.Controllers;

import com.example.easytable.Dtos.RolDTO;
import com.example.easytable.Entities.Role;
import com.example.easytable.Serviceinterfaces.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public class RoleController {
    @RestController
    @RequestMapping("/rol")
    public static class RolController {
        @Autowired
        private IRolService RS;

        @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
        @PostMapping("/register")
        public void insert(@RequestBody RolDTO dto){
            ModelMapper m = new ModelMapper();
            Role r = m.map(dto, Role.class);
            RS.insert(r);
        }

        @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
        @PutMapping("/update")
        public ResponseEntity<String> modificar(@RequestBody RolDTO dto) {
            ModelMapper m = new ModelMapper();
            Role r = m.map(dto, Role.class);

            Role existente = RS.listId(Math.toIntExact(r.getId()));
            if (existente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se puede modificar. No existe un Rol con el ID: " + r.getId());
            }

            RS.update(r);
            return ResponseEntity.ok("Rol con ID " + r.getId() + " modificado correctamente.");
        }

        @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
            Role r = RS.listId(id);
            if (r == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe un Rol con el ID: " + id);
            }
            RS.delete(id);
            return ResponseEntity.ok("Rol con ID " + id + " eliminado correctamente.");
        }

        @PreAuthorize("hasRole('ADMIN') or hasRole('USER')or hasRole('OWNER')")
        @GetMapping("/listar")
        public List<RolDTO> list() {
            return RS.list().stream().map(x -> {
                ModelMapper m = new ModelMapper();
                return m.map(x, RolDTO.class);
            }).collect(Collectors.toList());
        }
    }
}
