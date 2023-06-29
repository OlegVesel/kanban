package hm.kanban.kanban_board.repositories;

import hm.kanban.kanban_board.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    User findByUserName(String userName);
}
