package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {

    UserCredentials findByUsername(String usernameOrEmail);

}
