package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GymGoerRepositoryImp implements GymGoerRepository {
    private List<GymGoer> gymGoerList = new ArrayList<>();

    @Override
    public void save(GymGoer gymGoer) {
        gymGoerList.add(gymGoer);
    }

    @Override
    public GymGoer findByUserId(int userId) {
        return gymGoerList.stream()
                .filter(gg -> gg.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<GymGoer> findAll() {
        return new ArrayList<>(gymGoerList);
    }
}
