package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCredentialsRepositoryImp implements UserCredentialsRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserCredentialsRepositoryImp.class);
    private static List<UserCredentials> userCredentialsList = new ArrayList<>();

    static {
        userCredentialsList.add(new UserCredentials("gymstaff","1234","gymstaff@basicfit.com", LocalDate.now()));
    }

    @Override
    public void save(UserCredentials userCredentials) {
        userCredentialsList.add(userCredentials);
    }

    @Override
    public UserCredentials findByUsernameOrEmail(String usernameOrEmail) {
        return userCredentialsList.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(usernameOrEmail)
                        || user.getEmail().equalsIgnoreCase(usernameOrEmail))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<UserCredentials> findAll() {
        return new ArrayList<>(userCredentialsList);
    }
}