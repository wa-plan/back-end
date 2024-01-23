package com.example.waplan.web.repository;

import com.example.waplan.web.domain.User_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User_, Long> {

    List<User_> findUserEntityByUserId(String userId);
}
