package hm.kanban.kanban_board.controllers;

import hm.kanban.kanban_board.dto.response.TaskResponse;
import hm.kanban.kanban_board.entities.Task;
import hm.kanban.kanban_board.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAll(){
        List<TaskResponse> response = taskService.getAllTask();
        if (response == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if (response.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/status/{status}/{idUser}")
    public ResponseEntity<List<TaskResponse>> getAllByStatus(@PathVariable Short status, @PathVariable UUID idUser){
        List<TaskResponse> response = taskService.getAllParentTaskByStatus(status, idUser);
        if (response == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if (response.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable("id") UUID id){
        TaskResponse response = taskService.getById(id);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@RequestBody Task newTask){
        TaskResponse response = taskService.createNewTask(newTask);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<TaskResponse> updateTask(@RequestBody Task task){
        TaskResponse response = taskService.updateTask(task);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<Boolean> softDelete(@PathVariable("id") UUID id){
        Boolean response = taskService.softDelete(id);
        return response ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/hard/{id}")
    public ResponseEntity<Boolean> hardDelete(@PathVariable("id") UUID id){
        Boolean response = taskService.hardDelete(id);
        return response ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
