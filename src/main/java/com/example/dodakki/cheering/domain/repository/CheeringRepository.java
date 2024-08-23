package com.example.dodakki.cheering.domain.repository;

import com.example.dodakki.cheering.domain.Cheering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheeringRepository extends JpaRepository<Cheering, Long> {

}
