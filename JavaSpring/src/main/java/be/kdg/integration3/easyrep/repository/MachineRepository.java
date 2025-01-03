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

   @Query("SELECT To_char(s.endSession,'mm') as date, count(e) as amount FROM Exercise e JOIN Session s ON (e.session.session_id = s.session_id) WHERE (e.machine.machineId = :machineId) AND (s.status = 'COMPLETED') GROUP BY date")
    List<Object[]> findAllSetsByMachineId(@Param("machineId") int machineId);

   @Query("SELECT m.name as machineName, count(e.machine) as usage FROM Exercise e JOIN Machine m ON (m.machineId=e.machine.machineId) WHERE (m.gym.gymId = :gymId) GROUP BY m.machineId ORDER BY usage DESC  FETCH FIRST 10 ROWS ONLY")
    List<Object[]> findMostUseMachineId(@Param("gymId") int gymId);

    @Query(value = "SELECT * FROM MACHINE " +
            "WHERE last_time_checked < CURRENT_DATE - (maintenance_period_in_days || ' days')::INTERVAL " +
            "AND gym_id = :gymId",
            nativeQuery = true)
    List<Machine> findOverdueMachinesForGym(@Param("gymId") int gymId);

    Machine findByMachineId(int id);

    Machine findMachineByName(String name);

}
