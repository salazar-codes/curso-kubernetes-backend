package com.jsalazar.springcloud.msv.usuarios.repository;

import com.jsalazar.springcloud.msv.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// objeto y el tipo de llave definido -> Usuario y Long
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> porEmail(String email);

    Usuario findByNombre(String nombre);

    Usuario findByEmailAndNombre(String email, String nombre);
}
