package com.pub.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guru_kelas")
public class GuruKelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guru_kelas_id", nullable = false)
    private Integer guruKelasId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guru_id", referencedColumnName = "id")
    private Guru guru;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_id", referencedColumnName = "id")
    private Siswa siswa;


}
