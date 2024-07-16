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
        return "redirect:/";
    }

    @GetMapping("/todos")
    public String getTodos(Model model){
        List<Todo> list = service.getAllTodos();
        model.addAttribute("todos", list);
        return "AllTodos";
    }


    @GetMapping("/update")
    public String getUpdatePage() {
        return "/Todo/UpdateTodo";
    }

    @RequestMapping("/deleted/{todo_id}")
    public String deleteTodo(@PathVariable Long todo_id){
        service.deleteTodo(todo_id);
        return "redirect:/todos";
    }

}
