package hm.kanban.kanban_board.dto.response;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private Short status;
    private UUID idUser;
    private Boolean deleted;
    private String color;
    private LocalDate createDate;
    private UUID parentId;

    private List<SubTaskResponse> subTasks = new ArrayList<>();

    public void setSubTasks(List<SubTaskResponse> subTasks) {
        this.subTasks = subTasks;
    }

    public List<SubTaskResponse> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTaskResponse task){
        this.subTasks.add(task);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
