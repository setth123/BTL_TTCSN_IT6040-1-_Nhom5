package com.example.Server.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
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

