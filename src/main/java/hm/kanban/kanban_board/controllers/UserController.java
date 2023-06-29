package hm.kanban.kanban_board.controllers;

import hm.kanban.kanban_board.dto.response.UserResponse;
import hm.kanban.kanban_board.dto.request.UserRequest;
import hm.kanban.kanban_board.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check")
    public ResponseEntity<UserResponse> getByUserName(@RequestBody UserRequest request){
        UserResponse response = userService.checkUser(request);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addNew(@RequestBody UserRequest request){
        UserResponse response = userService.addUser(request);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().build();
    }
}
