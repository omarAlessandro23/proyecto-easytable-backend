package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Usuario;
import com.example.easytable.Repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository rep;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cambiamos findOneByNombre por findByUsername
        Usuario usuario = rep.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        usuario.getRoles().forEach(rol -> {
            // Asegúrate de que el prefijo sea "ROLE_" (en mayúsculas es el estándar de Spring)
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));
        });

        // Usamos los getters correctos de tu entidad Usuario
        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getContrasenia(),
                roles
        );
    }
}
