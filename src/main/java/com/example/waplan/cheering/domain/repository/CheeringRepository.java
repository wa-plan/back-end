package com.example.waplan.cheering.domain.repository;

import com.example.waplan.cheering.domain.Cheering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheeringRepository extends JpaRepository<Cheering, Long> {

}
