package com.jsalazar.springcloud.msv.cursos.repositories;

import com.jsalazar.springcloud.msv.cursos.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    // No need to define any methods here, as we are using the default CRUD operations provided by CrudRepository
}
