package be.kdg.integration3.easyrep.repository;

import be.kdg.integration3.easyrep.model.sessions.Set;

import java.util.List;

public interface SetRepository {
    Set createSet(Set set);

    List<Set> getSets();

    void emptyList();
}
