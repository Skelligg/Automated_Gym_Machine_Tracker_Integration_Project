package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findByGymId(int Id);
}
