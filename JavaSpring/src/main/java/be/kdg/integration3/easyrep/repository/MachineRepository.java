package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

    Machine findByName(String machineName);

    Machine findById(int id);
}
