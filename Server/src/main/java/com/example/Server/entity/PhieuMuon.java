package com.example.Server.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="PhieuMuon")
public class PhieuMuon {

    @Id
    @Column(name="maPM")
    private String maPM;
    @Column(name="thoiHan")
    private LocalDateTime thoiHan;
    @Column(name="trangThai")
    private Boolean trangThai;
    @Column(name="soLuongMuon")
    private Integer soLuongMuon;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "maSach")
    private Sach sach;

    // Getters and Setters
}
