package com.example.Server.entity;

import jakarta.persistence.*;

@Entity
public class PhieuViPham {

    @Id
    private String maPhieuVP;

    private Double soTienPhat;
    private Integer soNgayQuaHan;
    private Boolean trangThai;

    @OneToOne
    @JoinColumn(name = "maPM")
    private PhieuMuon phieuMuon;

    // Getters and Setters
}

