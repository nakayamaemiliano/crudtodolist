package com.ToDoList.demo.repositories;

import com.ToDoList.demo.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea,Long> {
    List<Tarea> findByCompletada(boolean completada);

}
