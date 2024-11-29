package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GymStaffRepositoryImp implements GymStaffRepository {
    private static List<GymStaff> gymStaffList = new ArrayList<>();

    @Override
    public void save(GymStaff gymStaff) {
        gymStaffList.add(gymStaff);
    }

    static {
        gymStaffList.add(new GymStaff(1,1));
    }

    @Override
    public GymStaff findByUserId(int userId) {
        return gymStaffList.stream()
                .filter(gs -> gs.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<GymStaff> findAll() {
        return new ArrayList<>(gymStaffList);
    }
}
