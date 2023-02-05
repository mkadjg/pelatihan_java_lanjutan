package com.pub.course.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadSiswa {
    private String nim;
    private String nama;
    private String kelas;
    private String alamat;
    private Integer umur;
}
