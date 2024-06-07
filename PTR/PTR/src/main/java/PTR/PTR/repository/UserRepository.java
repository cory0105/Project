package PTR.PTR.repository;

import PTR.PTR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUserId(String username);
    Optional<User> findByUserName(String userName);
}
