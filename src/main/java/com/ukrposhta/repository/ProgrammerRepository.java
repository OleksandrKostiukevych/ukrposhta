package com.ukrposhta.repository;

import com.ukrposhta.model.Programmer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {
    @Override
    @Query("SELECT e FROM Programmer e LEFT JOIN FETCH e.technologies WHERE e.id = :id")
    Optional<Programmer> findById(@Param("id") Long id);

    @Override
    @EntityGraph(attributePaths = {"technologies"})
    List<Programmer> findAllById(Iterable<Long> longs);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM projects_engineers pe "
            + "WHERE pe.engineer_id = :id", nativeQuery = true)
    void removeProjects(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM engineers_technologies et "
            + "WHERE et.engineer_id = :id", nativeQuery = true)
    void removeTechnologies(@Param("id") Long id);
}
