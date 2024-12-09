package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Machine;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
    @Transactional
    void delete(Machine machine);

    @Query("SELECT m FROM Machine m WHERE (m.machineId <= :LastId)")
    List<Machine> findByIdLessThan(@Param("LastId")int LastId);

    @Query("SELECT To_char(s.endSession,'dd-MM') as date,count(e) as amount FROM Exercise e JOIN Session s ON (e.session.session_id = s.session_id) WHERE (e.machine.machineId = :machineId) GROUP BY s.endSession")
    List<Object[]> findAllSetsByMachineId(@Param("machineId") int machineId);

    Machine findByMachineId(int id);

    Machine findMachineByName(String name);

}
