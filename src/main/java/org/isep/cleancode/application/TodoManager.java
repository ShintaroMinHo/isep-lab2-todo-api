package org.isep.cleancode.application;

import org.isep.cleancode.Todo;
import org.isep.cleancode.persistence.TodoRepository;

import java.util.List;

public class TodoManager {

    private final TodoRepository repository;

    public TodoManager(TodoRepository repository) {
        this.repository = repository;
    }

    public void addTodo(Todo todo) throws Exception {
        if (todo.getName() == null || todo.getName().trim().isEmpty()) {
            throw new Exception("Todo name is required.");
        }

        if (todo.getName().length() >= 64) {
            throw new Exception("Todo name must be shorter than 64 characters.");
        }

        if (repository.getAllTodos().stream().anyMatch(t -> t.getName().equalsIgnoreCase(todo.getName()))) {
            throw new Exception("Todo name must be unique.");
        }

        repository.addTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return repository.getAllTodos();
    }
}

