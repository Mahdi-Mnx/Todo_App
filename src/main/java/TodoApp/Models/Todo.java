package TodoApp.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;
    @Column(nullable = true, length = 50)
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)  // Maps enum to a string in the database
    private Status status = Status.NOT_STARTED;  // Default status

    public enum Status { // Enum for the status field
        COMPLETE,
        NOT_STARTED
    }
}
