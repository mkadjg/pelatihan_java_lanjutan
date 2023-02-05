package com.pub.course.controller;

import com.pub.course.dto.SiswaDto;
import com.pub.course.model.Siswa;
import com.pub.course.payload.DaftarSiswa;
import com.pub.course.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siswa")
public class SiswaController {

    @Autowired
    SiswaRepository siswaRepository;

    @GetMapping("/find-all")
    public Object findAll() {
        return siswaRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody SiswaDto dto) {
        // convert to siswa model
        Siswa siswa = new Siswa();
        siswa.setNim(dto.getNim());
        siswa.setNama(dto.getNama());
        siswa.setKelas(dto.getKelas());
        siswa.setAlamat(dto.getAlamat());
        siswa.setUmur(dto.getUmur());

        // save and return
        return ResponseEntity.ok(siswaRepository.save(siswa));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,
                         @RequestBody SiswaDto dto) {

        // check if id exist
        Siswa siswa = siswaRepository.findById(id).orElse(null);
        if (siswa == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }

        siswa.setNim(dto.getNim());
        siswa.setNama(dto.getNama());
        siswa.setKelas(dto.getKelas());
        siswa.setAlamat(dto.getAlamat());
        siswa.setUmur(dto.getUmur());

        return ResponseEntity.ok(siswaRepository.save(siswa));
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        // check if id exist
        Siswa siswa = siswaRepository.findById(id).orElse(null);
        if (siswa == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }

        siswaRepository.delete(siswa);
        return null;
    }

}
