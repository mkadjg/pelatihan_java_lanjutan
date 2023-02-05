package com.pub.course.repository;

import com.pub.course.model.GuruKelas;
import com.pub.course.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuruKelasRepository extends JpaRepository<GuruKelas, Integer> {

    @Query("select gk.siswa from GuruKelas gk where gk.guru.id =:guruId")
    public List<Siswa> findSiswaByGuru(Integer guruId);

}
