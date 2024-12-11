package com.example.Server.service;

import com.example.Server.entity.PhieuMuon;
import com.example.Server.entity.Sach;
import com.example.Server.repository.PhieuMuonRepository;
import com.example.Server.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PhieuMuonService {

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private SachRepository sachRepository;

    public ResponseEntity<?> createPhieuMuon( String maSach, Integer soLuong) {
        // Kiểm tra sách có tồn tại hay không
        Sach sach = sachRepository.findById(maSach).orElse(null);
        if (sach == null) {
            return ResponseEntity.badRequest().body("Sách không tồn tại.");
        }

        // Kiểm tra số lượng sách 
        if (sach.getSoLuong() < soLuong) {
            return ResponseEntity.badRequest().body("Số lượng sách trong kho không đủ.");
        }

        // Tạo phiếu mượn mới
        PhieuMuon phieuMuon = new PhieuMuon();
        phieuMuon.setMaPM("PM" + System.currentTimeMillis()); // Tạo mã phiếu mượn với thời gian hiện tại
        phieuMuon.setSoLuongMuon(soLuong);
        phieuMuon.setThoiHan(LocalDateTime.now().plusDays(7)); // Thời hạn là 7 ngày sau
        phieuMuon.setTrangThai(false); // Chưa trả

        // Lưu phiếu mượn vào cơ sở dữ liệu
        phieuMuonRepository.save(phieuMuon);

        // Trừ số lượng sách trong kho
        sach.setSoLuong(sach.getSoLuong() - soLuong);
        sachRepository.save(sach);

        // Trả về phản hồi thành công
        return ResponseEntity.ok("Tạo phiếu mượn thành công.");
    }
}
