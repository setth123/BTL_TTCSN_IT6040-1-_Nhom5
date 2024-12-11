package com.example.Server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Server.entity.PhieuViPham;

public interface PhieuViPhamRepository extends JpaRepository<PhieuViPham,String>{
    List<PhieuViPham> findByPhieuMuon_NguoiDung_MaNguoiDung(String maNguoiDung);
}
