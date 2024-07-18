package TodoApp.services;

import TodoApp.Models.Todo;
import TodoApp.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public void createTodo(Todo todo){  // Method to create a new todo
        repo.save(todo);
    }

    public void updateTodo(Todo todo){ // Method to update an existing todo
        repo.save(todo);
    }



    public Todo getTodoById(Long id){ // Method to get a todo by its ID
        return repo.findById(id).orElse(null);
    }

    public List<Todo> getAllTodos(){  // Method to get all todos
        return repo.findAll();
    }

    public void deleteTodo(Long todo_id){  // Method to delete a todo by its ID
        repo.deleteById(todo_id);
    }

    // New search methods
    public List<Todo> searchTodosByTitle(String title){  //Method to search todos by title
        return repo.findByTitleContaining(title);
    }
}