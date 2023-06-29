package hm.kanban.kanban_board.services;

import hm.kanban.kanban_board.entities.Task;
import hm.kanban.kanban_board.repositories.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    public Task getById(UUID id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task createNewTask(Task newTask) {
        newTask.setCreateDate(LocalDate.now());
        newTask.setDeleted(false);
        newTask.setStatus((short) 0);
        return taskRepo.save(newTask);
    }

    public Task updateTask(Task task) {
        if (!taskRepo.existsById(task.getId()))
            return null;
        return taskRepo.save(task);
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

    public List<Task> getAllTaskByStatus(Short status, UUID idUser) {
        return taskRepo.findAllByStatusAndIdUser(status, idUser);
    }
}
