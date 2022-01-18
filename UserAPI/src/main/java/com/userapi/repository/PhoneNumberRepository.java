package com.userapi.repository;

import com.userapi.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    PhoneNumber findById(int id);

}