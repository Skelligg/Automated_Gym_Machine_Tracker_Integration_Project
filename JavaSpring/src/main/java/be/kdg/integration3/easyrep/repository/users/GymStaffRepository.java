package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymStaffRepository extends JpaRepository<GymStaff, Integer> {

    GymStaff findByUserId(Integer integer);
}