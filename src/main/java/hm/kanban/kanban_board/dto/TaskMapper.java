package hm.kanban.kanban_board.dto;

import hm.kanban.kanban_board.dto.response.SubTaskResponse;
import hm.kanban.kanban_board.dto.response.TaskResponse;
import hm.kanban.kanban_board.entities.Task;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskMapper {
    public TaskResponse entityToResponse(Task entity){
        TaskResponse response = new TaskResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setStatus(entity.getStatus());
        response.setColor(entity.getColor());
        response.setDeleted(entity.getDeleted());
        response.setCreateDate(entity.getCreateDate());
        response.setIdUser(entity.getIdUser());
        if (entity.getSubTasks() != null && !entity.getSubTasks().isEmpty())
            response.setSubTasks(
                    entity.getSubTasks().
                            stream()
                            .map(SubTaskResponse::new)
                            .collect(Collectors.toList())
            );
        return response;
    }

}
