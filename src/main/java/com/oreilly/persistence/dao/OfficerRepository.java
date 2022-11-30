package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.OfficerEntity;
import com.oreilly.persistence.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficerRepository extends JpaRepository<OfficerEntity,Integer> {
    List<OfficerEntity> findAllByRankAndLastNameContaining(Rank rank, String lastName);
    
}
