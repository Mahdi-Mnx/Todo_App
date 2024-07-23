package TodoApp.Models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;
    @Column(nullable = true, length = 50)
    private String title;

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String description;

    @Enumerated(EnumType.STRING)  // Maps enum to a string in the database
    private Status status = Status.NOT_STARTED;  // Default status

    public enum Status { // Enum for the status field
        COMPLETE,
        NOT_STARTED
    }
}
