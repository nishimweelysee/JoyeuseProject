package com.elysee.dukachallenge.service;


import com.elysee.dukachallenge.Exceiptions.TodoException;
import com.elysee.dukachallenge.dbsetup.DbOperation;
import com.elysee.dukachallenge.dbsetup.setupDb;
import com.elysee.dukachallenge.domain.Priority;
import com.elysee.dukachallenge.domain.Todo;
import com.elysee.dukachallenge.domain.User;
import com.elysee.dukachallenge.services.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class TodoServiceTest extends setupDb {
    @Autowired
    TodoService todoService;


    @Test
    public void test_save_new_todo(){
        Todo t = Todo.builder()
                .id(UUID.randomUUID())
                .name("Eating")
                .description("I Will be Eating at 11:00")
                .priority(Priority.valueOf("HIGH"))
                .build();

        Todo todo =  new Todo();
        todo.setName("Coding");
        todo.setDescription("I have to write Java Code");
        todo.setPriority(Priority.valueOf("LOW"));
        Todo savedTodo = todoService.saveTask(todo);
        assertThat(savedTodo.getName(),is(equalTo("Coding")));
    }

    @Test
    public void test_update_existing_todo_name() throws TodoException {
        Todo todo = todoService.findById(UUID.fromString("4fbf908b-085f-41ca-bcf4-fa6303f3cfc9"));
        todo.setName("Reading");
        Todo updatedTodo = todoService.updateTask(todo);
        assertThat(updatedTodo.getName(),is(equalTo("Reading")));
    }

    @Test
    public void test_getAll_todos(){
        List<Todo> todos = todoService.getAllTasks();
        assertThat(todos,not(equalTo(null)));
    }

    @Test
    public void test_findOne_Todo(){
        Todo todo = todoService.findById(UUID.fromString("4fbf908b-085f-41ca-bcf4-fa6303f3cfc9"));
        assertThat(todo.getName(),is(equalTo("Learning")));
    }

    @Test
    public void test_delete_todo(){
        todoService.deleteTask(UUID.fromString("4fbf908b-085f-41ca-bcf4-fa6303f3cfc9"));
    }


    @BeforeEach
    public void initializeDb(){
        execute(DbOperation.INSERT_USER);
        execute(DbOperation.INSERT_TODO);
    }
    @AfterEach
    public void cleanDb(){
        execute(DbOperation.DELETE_TODO);
        execute(DbOperation.DELETE_USER);
    }
}
