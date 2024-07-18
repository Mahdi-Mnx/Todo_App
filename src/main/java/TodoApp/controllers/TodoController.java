package TodoApp.controllers;

import TodoApp.Models.Todo;
import TodoApp.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // Indicates that this is a controller component
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {  // Constructor injection for the service
        this.service = service;
    }

    @GetMapping("/")  // Maps GET requests to the root URL
    public String getHomePage() {
        return "Home";  // Returns the view name for the home page
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "About";  // Return the name of the about page template
    }


    @GetMapping("/create")  // Maps GET requests to the /create URL
    public String getCreatePage() {
        return "/Todo/Create";  // Returns the view name for the create page
    }

    @PostMapping("/create")  // Maps POST requests to the /create URL
    public String createTodo(@ModelAttribute Todo todo){  // Handles form submissions for creating a new todo
        service.createTodo(todo);  // Calls the service to create a new todo
        return "redirect:/todos";  // Redirects to the /todos URL
    }

    @GetMapping("/todos")  // Maps GET requests to the /todos URL
    public String getTodos(Model model){
        List<Todo> list = service.getAllTodos();  // Gets all todos from the service
        model.addAttribute("todos", list);  // Adds the todos to the model
        return "AllTodos";  // Returns the view name for displaying all todos
    }

    @GetMapping("/update/{todoId}")  // Maps GET requests to the /update/{todoId} URL
    public String getUpdatePage(@PathVariable Long todoId, Model model){
        Todo todo = service.getTodoById(todoId);  // Gets the todo by ID from the service
        if(todo != null){
            model.addAttribute("todo", todo);  // Adds the todo to the model
            return "/Todo/UpdateTodo";  // Returns the view name for the update page
        }
        return "redirect:/todos";  // Redirects to the /todos URL if the todo is not found
    }

    @PostMapping("/update")  // Maps POST requests to the /update URL
    public String updateTodo(@ModelAttribute Todo todo){  // Handles form submissions for updating a todo
        service.updateTodo(todo);  // Calls the service to update the todo
        return "redirect:/todos";  // Redirects to the /todos URL
    }

    @RequestMapping("/deleted/{todo_id}")  // Maps requests to the /deleted/{todo_id} URL
    public String deleteTodo(@PathVariable Long todo_id){
        service.deleteTodo(todo_id);  // Calls the service to delete the todo by ID
        return "redirect:/todos";  // Redirects to the /todos URL
    }

    // New search endpoint
    @GetMapping("/search")  // Maps GET requests to the /search URL
    public String searchTodos(@RequestParam String query, Model model) {
        List<Todo> todos;
        String message = null;
        try {
            // Try to parse the query as a Long to see if it can be an ID
            Long id = Long.parseLong(query);
            Todo todo = service.getTodoById(id);
            todos = todo != null ? List.of(todo) : List.of();  // If found, add to the list
            if (todos.isEmpty()) {
                message = "Todo with ID " + id + " not found.";
            }
        } catch (NumberFormatException e) {
            // If parsing fails, treat the query as a title
            todos = service.searchTodosByTitle(query);  // Search by title
            if (todos.isEmpty()) {
                message = "Todos with title '" + query + "' not found.";
            }
        }
        model.addAttribute("todos", todos);  // Add todos to the model
        model.addAttribute("message", message);  // Add message to the model
        return "AllTodos";  // Returns the view name for displaying all todos
    }
}
