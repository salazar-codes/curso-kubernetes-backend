package com.jsalazar.springcloud.msv.cursos.controllers;

import com.jsalazar.springcloud.msv.cursos.entity.Curso;
import com.jsalazar.springcloud.msv.cursos.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        ResponseEntity<Map<String, String>> errores = getMapResponseEntityValidation(result);
        if (errores != null) return errores;

        Curso cursoGuardado = cursoService.guardar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

        ResponseEntity<Map<String, String>> errores = getMapResponseEntityValidation(result);
        if (errores != null) return errores;

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

    private static ResponseEntity<Map<String, String>> getMapResponseEntityValidation(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        return null;
    }
}
