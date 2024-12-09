package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Arduino;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArduinoRepository extends JpaRepository<Arduino, Integer> {

    List<Arduino> findAll();

    Arduino findById(int arduinoId);

    @Query("SELECT a FROM Arduino a ORDER BY a.arduinoId DESC LIMIT 1")
    Arduino getLastArduino();
}
