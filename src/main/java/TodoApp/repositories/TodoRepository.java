package TodoApp.repositories;

import TodoApp.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("SELECT t FROM Todo t WHERE t.title LIKE %:title%") // Custom query to search todos by title
    List<Todo> findByTitleContaining(@Param("title") String title);  // Method to execute the custom query
}
