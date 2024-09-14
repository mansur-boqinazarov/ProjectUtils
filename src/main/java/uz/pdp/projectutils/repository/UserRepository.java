package uz.pdp.projectutils.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectutils.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findFirstByUsername(String username);
}
