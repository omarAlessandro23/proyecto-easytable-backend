package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Role;
import com.example.easytable.Entities.Usuario;
import com.example.easytable.Repositories.IUsuarioRepository;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository UR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> list() {
        return UR.findAll();
    }

    @Override
    public void insert(Usuario usuario) {

        // Encriptar contraseña antes de guardar
        usuario.setContrasenia(
                passwordEncoder.encode(usuario.getContrasenia())
        );

        // Fecha de creación automática
        usuario.setHoracreacion(LocalDate.now());

        // Crear rol por defecto
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {

            for (Role role : usuario.getRoles()) {
                role.setUsuario(usuario); // importante
            }

        } else {
            // si no envían rol, por defecto USER
            Role role = new Role();
            role.setRol("USER");
            role.setUsuario(usuario);

            usuario.setRoles(List.of(role));
        }

        // Guardar usuario con su rol
        UR.save(usuario);
    }

    @Override
    public void delete(int id) {
        UR.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        UR.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return UR.findById(id).orElse(new Usuario());
    }
}