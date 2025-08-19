package com.ToDoList.demo.controllers;

import com.ToDoList.demo.entities.Tarea;
import com.ToDoList.demo.services.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    final private TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> list(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> detais(@PathVariable Long id){
        Optional<Tarea> optionalTarea = service.findById(id);
        if (optionalTarea.isPresent()){
            return ResponseEntity.ok(optionalTarea.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Tarea> create(@RequestBody Tarea tarea){
      return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tarea));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> update(@RequestBody Tarea tarea,@PathVariable Long id){

        Optional<Tarea> optionalTarea = service.findById(id);
        if (optionalTarea.isPresent()){
            Tarea tareaDb = optionalTarea.orElseThrow();
            tareaDb.setDescripcion(tarea.getDescripcion());
            tareaDb.setCompletada(tarea.getCompletada());
            tareaDb.setTitulo(tarea.getTitulo());
            tareaDb.setFechaCreacion(tarea.getFechaCreacion());
            return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(tareaDb));
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/filtrar")
    public List<Tarea> obtenerPorCompletada(@RequestParam boolean completada) {
        return service.findByCompletada(completada);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Tarea> delete(@PathVariable Long id){
        Optional<Tarea> optionalTarea = service.findById(id);
        if(optionalTarea.isPresent()){
            Tarea tareaDeleted = service.deleteById(id).orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(tareaDeleted);
        }
        return  ResponseEntity.notFound().build();
    }
}
