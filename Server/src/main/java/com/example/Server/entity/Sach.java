package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private LocalDateTime nph;
    private Integer soLuong;
    private Integer soTrang;
    private String tacGia;

    @ManyToOne
    @JoinColumn(name = "maTL")
    private TheLoai theLoai;

    @OneToMany(mappedBy = "sach", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhieuMuon> phieuMuons;

    // Getters and Setters
}
