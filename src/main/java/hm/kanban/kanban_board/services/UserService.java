package hm.kanban.kanban_board.services;

import hm.kanban.kanban_board.dto.response.UserResponse;
import hm.kanban.kanban_board.dto.request.UserRequest;
import hm.kanban.kanban_board.entities.User;
import hm.kanban.kanban_board.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserResponse checkUser(UserRequest request) {
        User user = userRepo.findByUserName(request.getUserName());
        if (user != null)
           if (checkPassword(user, request.getPassword()))
               return new UserResponse(user.getId(), user.getUserName());
        return null;
    }

    private Boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public UserResponse addUser(UserRequest request) {
        User newUser = new User(request.getUserName(), request.getPassword());
        User saved = userRepo.save(newUser);
        return new UserResponse(saved.getId(), saved.getUserName());
    }
}
