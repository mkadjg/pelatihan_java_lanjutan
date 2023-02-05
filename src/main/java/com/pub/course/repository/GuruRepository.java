package com.pub.course.repository;

import com.pub.course.model.Guru;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruRepository extends JpaRepository<Guru, Integer> {
}
