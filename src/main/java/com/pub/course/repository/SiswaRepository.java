package com.pub.course.repository;

import com.pub.course.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiswaRepository extends JpaRepository<Siswa, Integer> {
}
