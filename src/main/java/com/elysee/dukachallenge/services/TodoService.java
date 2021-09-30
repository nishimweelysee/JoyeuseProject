package com.elysee.dukachallenge.services;

import com.elysee.dukachallenge.Exceiptions.TodoException;
import com.elysee.dukachallenge.domain.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    public Todo saveTask(Todo t);
    public void deleteTask(UUID id);
    public Todo updateTask(Todo todo) throws TodoException;
    public Todo findById(UUID id);
    public List<Todo> getAllTasks();
}
