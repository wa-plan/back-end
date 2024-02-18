package com.example.waplan.repository;

import com.example.waplan.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String userId);
    boolean existsByUserId(String userId);
}
