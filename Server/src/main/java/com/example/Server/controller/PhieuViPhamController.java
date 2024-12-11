package com.example.Server.controller;

import com.example.Server.entity.PhieuViPham;
import com.example.Server.repository.PhieuViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phieu-vi-pham")
public class PhieuViPhamController {

    @Autowired
    private PhieuViPhamRepository phieuViPhamRepository;

    /**
     * API lấy danh sách tất cả phiếu vi phạm
     */
    @GetMapping("/danh-sach")
    public ResponseEntity<?> getDanhSachPhieuViPham() {
        // Lấy danh sách tất cả phiếu vi phạm từ cơ sở dữ liệu
        List<PhieuViPham> danhSachPhieuViPham = phieuViPhamRepository.findAll();
        return ResponseEntity.ok(danhSachPhieuViPham);
    }
}