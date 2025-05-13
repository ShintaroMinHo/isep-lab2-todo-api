package org.isep.cleancode.presentation;

import com.google.gson.Gson;
import org.isep.cleancode.Todo;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class TodoController {

    // this Todo class should be completed to achieve Step 1

    private static final Gson gson = new Gson();
    private final List<Todo> todos = new ArrayList<>();

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");

        return gson.toJson(todos);
    };

    public Object createTodo(Request req, Response res) {
        Todo newTodo = gson.fromJson(req.body(), Todo.class);

        // Rule 1: Name is required
        if (newTodo.getName() == null || newTodo.getName().trim().isEmpty()) {
            res.status(400);
            return "Error: Todo name is required.";
        }

        // Rule 2: Name must be unique
        for (Todo todo : todos) {
            if (todo.getName().equalsIgnoreCase(newTodo.getName().trim())) {
                res.status(400);
                return "Error: Todo name must be unique.";
            }
        }

        // Rule 3: Name must be < 64 characters
        if (newTodo.getName().length() >= 64) {
            res.status(400);
            return "Error: Todo name must be shorter than 64 characters.";
        }

        todos.add(newTodo);
        res.status(201);
        res.type("application/json");

        return gson.toJson(newTodo);
    }

}
