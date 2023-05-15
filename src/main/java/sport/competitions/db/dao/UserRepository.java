package sport.competitions.db.dao;

import org.springframework.data.repository.CrudRepository;
import sport.competitions.db.model.User;

public interface UserRepository
        extends CrudRepository<User, Integer> {

    User findByLogin(String login);

}
