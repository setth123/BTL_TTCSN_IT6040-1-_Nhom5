package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class NguoiDung {

    @Id
    private String maNguoiDung;

    private String hoTen;
    private String soDienThoai;
    private String gioiTinh;
    private String diaChi;
    private String matKhau;
    private String tenTK;
    private Integer soLanViPham;
    private Boolean trangThaiTK;
    private Boolean trangThaiND;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private List<PhieuMuon> phieuMuons;

    // Getters and Setters
}
