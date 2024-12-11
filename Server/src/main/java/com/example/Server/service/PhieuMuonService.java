package com.example.Server.service;

import com.example.Server.entity.NguoiDung;
import com.example.Server.entity.PhieuMuon;
import com.example.Server.entity.Sach;
import com.example.Server.repository.NguoiDungRepository;
import com.example.Server.repository.PhieuMuonRepository;
import com.example.Server.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PhieuMuonService {

    @Autowired
    PhieuMuonRepository phieuMuonRepository;

    @Autowired
    SachRepository sachRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    public ResponseEntity<?> createPhieuMuon(String maSach, Integer soLuong, String maNguoiDung) {
        // Kiểm tra người dùng có tồn tại không
        Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findById(maNguoiDung);
        if (!nguoiDungOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại.");
        }

        // Kiểm tra sách có tồn tại không
        Optional<Sach> sachOpt = sachRepository.findById(maSach);
        if (!sachOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Sách không tồn tại.");
        }

        // Kiểm tra số lượng sách có đủ không
        Sach sach = sachOpt.get();
        if (sach.getSoLuong() < soLuong) {
            return ResponseEntity.badRequest().body("Số lượng sách không đủ.");
        }

        // Tạo phiếu mượn mới
        PhieuMuon phieuMuon = new PhieuMuon();
        phieuMuon.setMaPM("PM" + System.currentTimeMillis()); // Tạo mã phiếu mượn với thời gian hiện tại
        phieuMuon.setThoiHan(LocalDateTime.now().plusDays(7)); // Thời hạn là 7 ngày
        phieuMuon.setTrangThai(false); // Chưa trả
        phieuMuon.setSoLuongMuon(soLuong);
        phieuMuon.setNguoiDung(nguoiDungOpt.get());
        phieuMuon.setSach(sach);

        // Lưu phiếu mượn vào cơ sở dữ liệu
        phieuMuonRepository.save(phieuMuon);

        // Trừ số lượng sách trong kho
        sach.setSoLuong(sach.getSoLuong() - soLuong);
        sachRepository.save(sach);

        // Trả về phản hồi thành công
        return ResponseEntity.ok("Tạo phiếu mượn thành công.");
    }
}
