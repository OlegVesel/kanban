package hm.kanban.kanban_board.repositories;

import hm.kanban.kanban_board.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {
    List<Task> findAllByStatusAndIdUser(Short status, UUID idUser);
}
