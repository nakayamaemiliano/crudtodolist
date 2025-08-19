package com.ToDoList.demo.services;

import com.ToDoList.demo.entities.Tarea;
import com.ToDoList.demo.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImp implements TareaService {

    private final TareaRepository repository;

    public TareaServiceImp(TareaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tarea> findAll() {
        return (List<Tarea>) repository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public List<Tarea> findByCompletada(boolean completada) {
        return repository.findByCompletada(completada) ;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Tarea> findById(Long id) {
        return repository.findById(id);
    }
    @Transactional
    @Override
    public Tarea save(Tarea tarea) {
        return repository.save(tarea);
    }

    @Transactional
    @Override
    public Optional<Tarea> deleteById(Long id) {
        Optional<Tarea> tareaOptional = repository.findById(id);
        if(tareaOptional.isPresent()){
            repository.deleteById(id);
            return tareaOptional;
        }
        return Optional.empty();
    }
}
