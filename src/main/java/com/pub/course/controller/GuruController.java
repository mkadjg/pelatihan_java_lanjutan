package com.pub.course.controller;

import com.pub.course.dto.GuruDto;
import com.pub.course.model.Guru;
import com.pub.course.repository.GuruRepository;
import com.pub.course.service.SpreadsheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    GuruRepository guruRepository;

    @Autowired
    SpreadsheetService spreadsheetService;

    @GetMapping("/find-all")
    public Object findAll() {
        return guruRepository.findAll();
    }

    @PostMapping("/")
    public Object create(@RequestBody GuruDto guruDto) {
        Guru guru = new Guru();
        guru.setNip(guruDto.getNip());
        guru.setNamaGuru(guruDto.getNamaGuru());
        guru.setPelajaran(guruDto.getPelajaran());
        guru.setNoTelepon(guruDto.getNoTelepon());
        return guruRepository.save(guru);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody GuruDto guruDto) {
        Guru guru = guruRepository.findById(id).orElse(null);
        if (guru == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }

        guru.setNip(guruDto.getNip());
        guru.setNamaGuru(guruDto.getNamaGuru());
        guru.setPelajaran(guruDto.getPelajaran());
        guru.setNoTelepon(guruDto.getNoTelepon());
        return guruRepository.save(guru);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        Guru guru = guruRepository.findById(id).orElse(null);
        if (guru == null) {
            return ResponseEntity.badRequest().body("id tidak ditemukan!");
        }

        guruRepository.delete(guru);
        return null;
    }

    @GetMapping("/spreadsheet")
    public void generate(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=\"file.xlsx\"");
        OutputStream out = response.getOutputStream();
        spreadsheetService.generate(out);
        out.flush();
        out.close();
    }

}
