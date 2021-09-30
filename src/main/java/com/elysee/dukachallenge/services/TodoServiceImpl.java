package com.elysee.dukachallenge.services;

import com.elysee.dukachallenge.Exceiptions.TodoException;
import com.elysee.dukachallenge.domain.Todo;
import com.elysee.dukachallenge.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {
    TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    /**
     * @param t
     * @return Todo
     * */
    @Override
    public Todo saveTask(Todo t) {
        return repository.save(Todo.builder()
                .id(UUID.randomUUID())
                .name(t.getName())
                .description(t.getDescription())
                .priority(t.getPriority())
                .build()
        );
    }

    /**
     * @param id
     * @return void
     * */
    @Override
    public void deleteTask(UUID id) {
        repository.deleteById(id);
    }

    /**
     * @param t
     * @return Todo
     * */
    @Override
    public Todo updateTask(Todo t) throws TodoException {
        Todo todo = repository.findById(t.getId()).get();
        if(todo.equals(null)){
            throw new TodoException("Todo to update not exist");
        }
        if(t.getName()!=null){
            todo.setName(t.getName());
        }
        if(t.getDescription()!=null){
            todo.setDescription(t.getDescription());
        }
        if(t.getPriority()!=null){
            todo.setPriority(t.getPriority());
        }

        Todo updatedTodo = repository.save(todo);
        return  updatedTodo;
    }


    /**
     * @param id
     * @return Todo
     * */
    @Override
    public Todo findById(UUID id) {
        return repository.findById(id).get();
    }


    /**
     * @return List<Todo>
     * */
    @Override
    public List<Todo> getAllTasks() {
        List<Todo> todos =  new ArrayList<>();
        repository.findAll().forEach(t -> todos.add(t));
        return todos;
    }
}
