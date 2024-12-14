package com.example.Server.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Getter
@Setter
public class SachDTO {
    private String maSach;
    private String tenSach;
    private String nxb;
    private LocalDateTime nph;
    private Integer soLuong;
    private Integer soTrang;
    private String tacGia;
    private String maTheLoai; // Chỉ lưu mã thể loại

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public SachDTO(String maSach, String tenSach, String nxb, String nph, Integer soLuong, Integer soTrang, String tacGia, String maTheLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.nxb = nxb;
        this.nph = LocalDateTime.parse(nph, formatter);
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.tacGia = tacGia;
        this.maTheLoai = maTheLoai;
    }
}
