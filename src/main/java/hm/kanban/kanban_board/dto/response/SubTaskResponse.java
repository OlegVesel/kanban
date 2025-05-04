package hm.kanban.kanban_board.dto.response;

import hm.kanban.kanban_board.entities.SubTask;
import org.springframework.stereotype.Component;

import java.util.UUID;


public class SubTaskResponse {
    private UUID id;
    private String title;
    private boolean done;

    public SubTaskResponse() {
    }

    public SubTaskResponse(SubTask entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
