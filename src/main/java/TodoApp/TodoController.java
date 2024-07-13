package TodoApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/register")
    public String getRegisterPage() {
        return "/User/Registration";
    }
}
