package be.kdg.integration3.easyrep.repository.users;

import be.kdg.integration3.easyrep.model.GymGoer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymGoerRepository extends JpaRepository<GymGoer, Integer> {

    GymGoer findByuserId(int id);

}