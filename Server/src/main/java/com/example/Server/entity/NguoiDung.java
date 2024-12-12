package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="NguoiDung")
public class NguoiDung {

    @Id
    @Column(name="maNguoiDung")
    private String maNguoiDung;
    @Column(name="hoTen")
    private String hoTen;
    @Column(name="soDienThoai")
    private String soDienThoai;
    @Column(name="gioiTinh")
    private String gioiTinh;
    @Column(name="diaChi")
    private String diaChi;
    @Column(name="matKhau")
    private String matKhau;
    @Column(name="tenTK")
    private String tenTK;
    @Column(name="soLanViPham")
    private Integer soLanViPham;
    @Column(name="trangThaiTK")
    private Boolean trangThaiTK;
    @Column(name="trangThaiVP")
    private Boolean trangThaiVP;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhieuMuon> phieuMuons;

    // Getters and Setters
}
