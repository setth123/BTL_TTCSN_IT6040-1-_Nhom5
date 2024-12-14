package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
 
@Entity
@Getter
@Setter
@Table(name="Sach")
public class Sach {

    @Id
    @Column(name="maSach")
    private String maSach;
    @Column(name="tenSach")
    private String tenSach;
    @Column(name="nxb")
    private String nxb;
    @Column(name="nph")
    private LocalDateTime nph;
    @Column(name="soLuong")
    private Integer soLuong;
    @Column(name="soTrang")
    private Integer soTrang;
    @Column(name="tacGia")
    private String tacGia;

    @ManyToOne
    @JoinColumn(name = "maTL")
    private TheLoai theLoai;

    @OneToMany(mappedBy = "sach", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhieuMuon> phieuMuons;

    // Getters and Setters
}
