package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
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
    private Boolean trangThaiVP;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhieuMuon> phieuMuons;

    // Getters and Setters
}
