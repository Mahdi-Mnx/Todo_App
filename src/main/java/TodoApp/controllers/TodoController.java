package TodoApp.controllers;

import TodoApp.Models.Todo;
import TodoApp.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "Home";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "/Todo/Create";
    }

    @PostMapping("/create")
    public String createTodo(@ModelAttribute Todo todo){
        service.createTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/todos")
    public String getTodos(Model model){
        List<Todo> list = service.getAllTodos();
        model.addAttribute("todos", list);
        return "AllTodos";
    }

    @GetMapping("/update/{todoId}")
    public String getUpdatePage(@PathVariable Long todoId, Model model){
        Todo todo = service.getTodoById(todoId);
        if(todo != null){
            model.addAttribute("todo", todo);
            return "/Todo/UpdateTodo";
        }
        return "redirect:/todos";
    }

    @PostMapping("/update")
    public String updateTodo(@ModelAttribute Todo todo){
        service.UpdateTodo(todo);
        return "redirect:/todos";
    }

    @RequestMapping("/deleted/{todo_id}")
    public String deleteTodo(@PathVariable Long todo_id){
        service.deleteTodo(todo_id);
        return "redirect:/todos";
    }

    // New search endpoint
    @GetMapping("/search")
    public String searchTodos(@RequestParam String query, Model model) {
        List<Todo> todos;
        String message = null;
        try {
            // Try to parse the query as a Long to see if it can be an ID
            Long id = Long.parseLong(query);
            Todo todo = service.getTodoById(id);
            todos = todo != null ? List.of(todo) : List.of();
            if (todos.isEmpty()) {
                message = "Todo with ID " + id + " not found.";
            }
        } catch (NumberFormatException e) {
            // If parsing fails, treat the query as a title
            todos = service.searchTodosByTitle(query);
            if (todos.isEmpty()) {
                message = "Todos with title '" + query + "' not found.";
            }
        }
        model.addAttribute("todos", todos);
        model.addAttribute("message", message);
        return "AllTodos";
    }


}
