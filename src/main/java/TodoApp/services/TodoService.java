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

    public void createTodo(Todo todo){
        repo.save(todo);
    }

    public void UpdateTodo(Todo todo){
        repo.save(todo);
    }



    public Todo getTodoById(Long id){
       return repo.findById(id).orElse(null);
    }

    public List<Todo> getAllTodos(){
        return repo.findAll();
    }

    public void deleteTodo(Long todo_id){
        repo.deleteById(todo_id);
    }
}
