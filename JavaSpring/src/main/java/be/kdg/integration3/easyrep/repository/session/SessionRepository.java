package be.kdg.integration3.easyrep.repository.session;

import be.kdg.integration3.easyrep.model.sessions.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session findById(int id);
}
