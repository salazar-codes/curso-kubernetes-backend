package com.jsalazar.springcloud.msv.usuarios.repository;

import com.jsalazar.springcloud.msv.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

// objeto y el tipo de llave definido -> Usuario y Long
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findByNombre(String nombre);

    Usuario findByEmailAndNombre(String email, String nombre);
}
