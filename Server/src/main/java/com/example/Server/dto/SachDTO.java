package com.example.Server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SachDTO {
    private String maSach;
    private String tenSach;
    private String nxb;
    private String nph;
    private Integer soLuong;
    private Integer soTrang;
    private String tacGia;
    private String maTheLoai; // Chỉ lưu mã thể loại

    public SachDTO(String maSach, String tenSach, String nxb, String nph, Integer soLuong, Integer soTrang, String tacGia, String maTheLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.nxb = nxb;
        this.nph = nph;
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.tacGia = tacGia;
        this.maTheLoai = maTheLoai;
    }
}
