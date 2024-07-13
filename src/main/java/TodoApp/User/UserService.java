package TodoApp.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void createUser(User user){
        repo.save(user);
    }

    public void UpdateUser(User user){
        repo.save(user);
    }

    public void deleteUser(Long id){
        repo.deleteById(id);
    }

    public User getUserById(Long id){
        return repo.findById(id).orElse(null);
    }

    public List<User> getAllUser(){
        return repo.findAll();
    }
}

