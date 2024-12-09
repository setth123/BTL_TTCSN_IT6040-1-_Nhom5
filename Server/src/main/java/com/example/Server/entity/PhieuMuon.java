package com.example.Server.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PhieuMuon {

    @Id
    private String maPM;

    private LocalDateTime thoiHan;
    private Boolean trangThai;
    private Integer soLuongMuon;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "maSach")
    private Sach sach;

    // Getters and Setters
}
