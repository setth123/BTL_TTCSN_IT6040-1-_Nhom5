package com.example.Server.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="PhieuViPham")
public class PhieuViPham {

    @Id
    @Column(name="maPhieuVP")
    private String maPhieuVP;
    @Column(name="soTienPhat")
    private Double soTienPhat;
    @Column(name="soNgayQuaHan")
    private Integer soNgayQuaHan;
    @Column(name="trangThai")
    private Boolean trangThai;

    @OneToOne
    @JoinColumn(name = "maPM")
    private PhieuMuon phieuMuon;

    // Getters and Setters
}

