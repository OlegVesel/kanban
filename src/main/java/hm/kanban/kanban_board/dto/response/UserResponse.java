package hm.kanban.kanban_board.dto.response;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String userName;

    public UserResponse(UUID id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserResponse() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
