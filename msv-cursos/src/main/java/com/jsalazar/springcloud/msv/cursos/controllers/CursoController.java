package com.jsalazar.springcloud.msv.cursos.controllers;

import com.jsalazar.springcloud.msv.cursos.entity.Curso;
import com.jsalazar.springcloud.msv.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping()
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.listar();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> porId(@PathVariable Long id) {
        return cursoService.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        Curso cursoGuardado = cursoService.guardar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> editar(@RequestBody Curso curso, @PathVariable Long id) {
        return cursoService.porId(id)
                .map(cursoExistente -> {
                    cursoExistente.setNombre(curso.getNombre());
                    return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return cursoService.porId(id)
                .map(curso -> {
                    cursoService.eliminar(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
