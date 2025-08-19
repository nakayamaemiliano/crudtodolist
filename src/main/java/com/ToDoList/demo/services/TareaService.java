package com.ToDoList.demo.services;

import com.ToDoList.demo.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    List<Tarea> findAll();
    List<Tarea> findByCompletada(boolean completada);
    Optional<Tarea> findById(Long id);
    Tarea save(Tarea tarea);
    Optional<Tarea> deleteById (Long id);

}
