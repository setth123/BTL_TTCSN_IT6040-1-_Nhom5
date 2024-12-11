package com.example.Server.controller;

import com.example.Server.entity.PhieuViPham;
import com.example.Server.repository.PhieuViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phieu-vi-pham")
public class PhieuViPhamController {

    @Autowired
    PhieuViPhamRepository pvpr;

    /**
     * API tìm kiếm phiếu vi phạm theo mã phiếu vi phạm
     */
    @GetMapping("/{maND}")
    public ResponseEntity<?> getPhieuViPhamByNguoiDung(@PathVariable String maND) {
        List<PhieuViPham> phieu= pvpr.findByPhieuMuon_NguoiDung_MaNguoiDung(maND);
        
        if (phieu.isEmpty()) {
            return ResponseEntity.badRequest().body("Không có phiếu vi phạm cho người dùng này.");
        }
        
        return ResponseEntity.ok(phieu); // Trả về danh sách phiếu vi phạm
    }

    @GetMapping
    public List<PhieuViPham> getPVP() {
        return pvpr.findAll();
    }
    @PutMapping("/{maPhieuVP}/{state}")
    public List<PhieuViPham> setVP(@PathVariable String maPhieuVP, @PathVariable String state){
        Optional<PhieuViPham> pvp=pvpr.findById(maPhieuVP);
        if(pvp.isPresent()){
            PhieuViPham violation = pvp.get();
            violation.setTrangThai(!Boolean.parseBoolean(state));
            pvpr.save(violation);
        }
            return getPVP();
    }
}
