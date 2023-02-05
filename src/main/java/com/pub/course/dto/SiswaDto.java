package com.pub.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiswaDto {
    String nim;
    String nama;
    String kelas;
    String alamat;
    Integer umur;
}
