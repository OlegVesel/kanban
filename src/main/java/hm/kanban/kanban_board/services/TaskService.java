package hm.kanban.kanban_board.services;

import hm.kanban.kanban_board.dto.TaskMapper;
import hm.kanban.kanban_board.dto.response.TaskResponse;
import hm.kanban.kanban_board.entities.Task;
import hm.kanban.kanban_board.repositories.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepo taskRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> getAllTask() {
        return taskRepo.findAll().stream().map(taskMapper::entityToResponse).collect(Collectors.toList());
    }

    public TaskResponse getById(UUID id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Задачи с таким id не существует: " + id));
        return taskMapper.entityToResponse(task);
    }

    public TaskResponse createNewTask(Task newTask) {
        newTask.setCreateDate(LocalDate.now());
        newTask.setDeleted(false);
        newTask.setStatus((short) 0);
        return taskMapper.entityToResponse(taskRepo.save(newTask));
    }

    public TaskResponse updateTask(Task task) {
        if (!taskRepo.existsById(task.getId()))
            return null;
        task.getSubTasks().forEach(st -> st.setParent(task));
        return taskMapper.entityToResponse(taskRepo.save(task));
    }

    public Boolean softDelete(UUID id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task == null)
            return false;
        task.setDeleted(true);
        taskRepo.save(task);
        return true;
    }

    public Boolean hardDelete(UUID id) {
        if (!taskRepo.existsById(id))
            return false;
        taskRepo.deleteById(id);
        return true;
    }

    public List<TaskResponse> getAllParentTaskByStatus(Short status, UUID idUser) {
        return taskRepo.findAllByStatusAndIdUser(status, idUser)
                .stream()
                .map(taskMapper::entityToResponse)
                .collect(Collectors.toList());
    }
}
