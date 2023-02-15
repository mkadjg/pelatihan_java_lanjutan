package com.pub.course.repository;

import com.pub.course.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("select u from Users u where u.username=:username")
    Optional<Users> findByUsername(String username);
}
