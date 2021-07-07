package com.felix180.person.repository;

import com.felix180.person.entity.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long> {

    @Query("SELECT h FROM Heroe h WHERE lower(h.name) LIKE concat('%', :name,'%')  ")
    List<Heroe> findByName(@Param("name")String name);
}
