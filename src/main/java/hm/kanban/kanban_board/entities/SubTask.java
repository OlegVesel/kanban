package hm.kanban.kanban_board.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sub_tasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private boolean done;
    @JoinColumn(referencedColumnName = "id")
    @ManyToOne
    private Task parent;

    public SubTask() {
    }

    public SubTask(UUID id, String title, boolean done, Task parent) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.parent = parent;
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

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }
}
