package com.ukrposhta.repository;

import com.ukrposhta.model.Project;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Override
    @Query("SELECT p FROM Project p "
            + "LEFT JOIN FETCH p.programmers LEFT JOIN FETCH p.managers WHERE p.id = :id")
    Optional<Project> findById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Project p SET p.status = :status WHERE p.id = :id")
    Project updateStatus(@Param("id") Long id, @Param("status") String status);

    @Query("SELECT p FROM Project p "
            + "LEFT JOIN FETCH p.programmers e LEFT JOIN FETCH p.managers WHERE e.id = :engineerId")
    Set<Project> findAllByProgrammerId(@Param("engineerId") Long engineerId);

    @Query("SELECT p FROM Project p "
            + "LEFT JOIN FETCH p.programmers LEFT JOIN FETCH p.managers m WHERE m.id = :engineerId")
    Set<Project> findAllByManagerId(@Param("engineerId") Long engineerId);
}
