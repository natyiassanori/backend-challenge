package com.userapi.repository;

import com.userapi.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    PhoneNumber findByNumberAndUserId(String phoneNumber, int userId);

}