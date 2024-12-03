package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymStaff;

import java.util.List;

public interface GymStaffRepository {
    void save(GymStaff gymStaff);

    GymStaff findByUserId(int userId);

    List<GymStaff> findAll();

    void remove(GymStaff gymStaff);

}