package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymGoerRepository extends JpaRepository<GymGoer, Integer> {

    GymGoer findByUserId(int id);

}