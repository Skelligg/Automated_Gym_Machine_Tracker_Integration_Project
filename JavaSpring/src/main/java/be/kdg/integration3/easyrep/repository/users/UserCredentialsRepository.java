package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.UserCredentials;

import java.util.List;

public interface UserCredentialsRepository {
    void save(UserCredentials userCredentials);

    UserCredentials findById(int id);

    List<UserCredentials> findAll();
}
