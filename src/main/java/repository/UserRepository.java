package repository;

import com.testTask.telros.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Репозиторий для выполнения операций CRUD (Create, Read, Update, Delete) с сущностью User.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}