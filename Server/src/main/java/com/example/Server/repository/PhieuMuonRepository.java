package com.example.Server.repository;

import com.example.Server.entity.PhieuMuon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, String> {
    List<PhieuMuon> findByNguoiDung_MaNguoiDung(String maNguoiDung);
}
