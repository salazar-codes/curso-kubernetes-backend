package com.jsalazar.springcloud.msv.usuarios.service;

import com.jsalazar.springcloud.msv.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id); // Optional para evitar null pointer
    Usuario save(Usuario usuario);
    void deleteById(Long id);
}
