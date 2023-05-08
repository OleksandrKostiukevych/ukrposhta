package com.ukrposhta.repository;

import com.ukrposhta.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM programmers_technologies et "
            + "WHERE et.technology_id = :id", nativeQuery = true)
    void deleteProgrammers(@Param("id") Long id);
}
