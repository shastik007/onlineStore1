package online.db.repository;

import online.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {
    Optional<User> findByEmail(String email);

    @Query("select s from User s where s.email = ?1")
    Optional<User> getUser(String username);

    @Query("SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM User e where e.email = ?1")
    Boolean existsByEmail(String email);

}
