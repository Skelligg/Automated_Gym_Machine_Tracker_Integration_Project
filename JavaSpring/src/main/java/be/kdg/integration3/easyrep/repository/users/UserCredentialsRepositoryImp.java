package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.UserCredentials;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCredentialsRepositoryImp implements UserCredentialsRepository {
    private List<UserCredentials> userCredentialsList = new ArrayList<>();

    @Override
    public void save(UserCredentials userCredentials) {
        userCredentialsList.add(userCredentials);
    }

    @Override
    public UserCredentials findById(int id) {
        return userCredentialsList.stream()
                .filter(uc -> uc.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<UserCredentials> findAll() {
        return new ArrayList<>(userCredentialsList);
    }
}