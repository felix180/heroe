package com.felix180.person.repository;

import com.felix180.person.entity.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long> {

    Heroe findByName(String name);
}
