package com.ukrposhta.repository;

import com.ukrposhta.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM projects_managers pm WHERE pm.manager_id = :id", nativeQuery = true)
    void removeProjects(@Param("id") Long id);
}
