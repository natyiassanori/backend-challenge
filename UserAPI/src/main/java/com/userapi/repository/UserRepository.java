package com.userapi.repository;

import com.userapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    List<User> findByFirstNameIgnoreCase(String first);

    List<User> findByLastNameIgnoreCase(String lastName);
}