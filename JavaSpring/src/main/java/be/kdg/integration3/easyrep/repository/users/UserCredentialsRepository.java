package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {

    @Query("SELECT u FROM UserCredentials u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
    UserCredentials findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

}
