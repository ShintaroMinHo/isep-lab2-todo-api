package org.isep.cleancode.persistence;

import org.isep.cleancode.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

    private final List<Todo> todos = new ArrayList<>();

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public List<Todo> getAllTodos() {
        return todos;
    }
}
