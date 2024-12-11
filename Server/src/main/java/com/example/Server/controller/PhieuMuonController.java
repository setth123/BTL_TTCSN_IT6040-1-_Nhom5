package com.example.Server.controller;

import com.example.Server.dto.CheckRequest;
import com.example.Server.service.CheckTrangThaiService;
import com.example.Server.service.PhieuMuonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phieu-muon")
public class PhieuMuonController {

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private CheckTrangThaiService checkTrangThaiService;

    @PostMapping("/check")
    public ResponseEntity<?> checkTrangThai(@RequestBody CheckRequest request) {
        String maSach = request.getMaSach();
        String maNguoiDung = request.getMaNguoiDung();

        String trangThai = checkTrangThaiService.checkTrangThai(maNguoiDung, maSach);
        if ("Ổn".equals(trangThai)) {
            return ResponseEntity.ok("Mọi thứ ổn.");
        }
        return ResponseEntity.badRequest().body(trangThai);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPhieuMuon(@RequestBody String maNguoiDung, @RequestBody String maSach, 
                                              @RequestBody int soLuong) {
        return phieuMuonService.createPhieuMuon(maNguoiDung, maSach, soLuong);
    }
}
