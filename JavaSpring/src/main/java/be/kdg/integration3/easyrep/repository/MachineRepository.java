package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Machine;
import be.kdg.integration3.easyrep.model.UserCredentials;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
    @Transactional
    void delete(Machine machine);

    @Query("SELECT m FROM Machine m WHERE (m.machineId <= :LastId)")
    List<Machine> findByIdLessThan(int LastId);

    Machine findMachineByName(String name);
    Machine findById(int id);
}
