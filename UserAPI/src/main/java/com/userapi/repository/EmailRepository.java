package com.userapi.repository;

import com.userapi.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

    Email findById(int id);

}