package com.pub.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "siswa")
public class Siswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nim", length = 10, nullable = false)
    private String nim;

    @Column(name = "nama", length = 500)
    private String nama;

    @Column(name = "kelas", length = 10)
    private String kelas;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "umur")
    private Integer umur;

}
