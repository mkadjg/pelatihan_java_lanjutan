package com.pub.course.controller;

import com.pub.course.payload.DaftarSiswa;
import com.pub.course.payload.PayloadSiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/root")
public class RootController {
    @Autowired
    DaftarSiswa daftarSiswa;

    @GetMapping("/")
    public List<PayloadSiswa> getExample() {
        return daftarSiswa.getSiswaList();
    }


}
