package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Sach {

    @Id
    private String maSach;

    private String tenSach;
    private String nxb;
    private String nph;
    private Integer soLuong;
    private Integer soTrang;
    private String tacGia;

    @ManyToOne
    @JoinColumn(name = "maTL")
    private TheLoai theLoai;

    @OneToMany(mappedBy = "sach", cascade = CascadeType.ALL)
    private List<PhieuMuon> phieuMuons;

    // Getters and Setters
}
