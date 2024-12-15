package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findByGymId(int Id);

  /*  @Query("SELECT s.startSession, s.endSession, (s.endSession-s.startSession) as timeBetween FROM Session s JOIN Exercise e ON (e.session.session_id=s.session_id) JOIN Machine m ON (m.machineId=e.machine.machineId) WHERE (m.gym.gymId = :gymId) and s.startSession IS NOT NULL ORDER BY s.startSession DESC")
//    @Query("SELECT count(s.startSession), s.startSession FROM Session s JOIN Exercise e ON (e.session.session_id=s.session_id) JOIN Machine m ON (m.machineId=e.machine.machineId) WHERE (m.gym.gymId = :gymId) and s.startSession IS NOT NULL GROUP BY To_char(s.startSession,'hh'), s.startSession ORDER BY s.startSession DESC")
    List<Object[]> findUsageDuringSpecificDayOfTheWeek(@Param("gymId") int gymId);

    @Query("SELECT  AVG(EXTRACT(EPOCH FROM (s.endSession - s.startSession)))  FROM Exercise e JOIN Session s ON (e.session.session_id = s.session_id) WHERE (e.machine.gym.gymId = :gymId) AND s.startSession IS NOT NULL GROUP BY s.startSession, s.endSession")
    List<Object[]> avarageTimePerSession(@Param("gymId") int gymId);*/

    @Query("SELECT s.startSession, s.endSession FROM Exercise e JOIN Session s ON (e.session.session_id = s.session_id) WHERE (e.machine.gym.gymId = :gymId) AND s.startSession IS NOT NULL GROUP BY s.startSession, s.endSession")
    List<Object[]> findUsageDuringSpecificDayOfTheWeek(@Param("gymId") int gymId);

    @Query("SELECT To_char(s.startSession,'MM'), count(s.gymGoerId) FROM Session s WHERE s.session_id IN (SELECT e.session.session_id FROM Exercise e WHERE (e.machine.gym.gymId = :gymId)) AND s.startSession IS NOT NULL GROUP BY s.gymGoerId.userId, To_char(s.startSession,'MM') ORDER BY To_char(s.startSession,'MM') DESC")
    List<Object[]> findGymGoersFromTheGym(@Param("gymId") int gymId);
}
