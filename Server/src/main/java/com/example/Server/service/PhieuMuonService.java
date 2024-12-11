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

import java.time.LocalDateTime;

@Service
public class PhieuMuonService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private SachRepository sachRepository;

    @Autowired
    private CheckTrangThaiService checkTrangThaiService;

    public ResponseEntity<?> createPhieuMuon(String maNguoiDung, String maSach, int soLuong) {
        // Kiểm tra trạng thái (người dùng, sách, số lượng)
        String trangThai = checkTrangThaiService.checkTrangThai(maNguoiDung, maSach);
        if (!"Ổn".equals(trangThai)) {
            return ResponseEntity.badRequest().body(trangThai);
        }

        // Kiểm tra sách có tồn tại hay không
        Sach sach = sachRepository.findById(maSach).orElse(null);
        if (sach == null) {
            return ResponseEntity.badRequest().body("Sách không tồn tại.");
        }

        // Kiểm tra người dùng có tồn tại hay không
        NguoiDung nguoiDung = nguoiDungRepository.findById(maNguoiDung).orElse(null);
        if (nguoiDung == null) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại.");
        }

        // Kiểm tra số lượng sách có >0 không
        if (sach.getSoLuong() < soLuong) {
            return ResponseEntity.badRequest().body("Số lượng sách không đủ.");
        }

        // Tạo phiếu mượn mới
        PhieuMuon phieuMuon = new PhieuMuon();

        // Gán các giá trị vào phiếu mượn
        phieuMuon.setNguoiDung(nguoiDung);
        phieuMuon.setSach(sach);
        phieuMuon.setMaPM("PM"+2);
        phieuMuon.setSoLuongMuon(soLuong);
        phieuMuon.setThoiHan(LocalDateTime.now());
        phieuMuon.setTrangThai(false);

        // Lưu phiếu mượn vào cơ sở dữ liệu
        phieuMuonRepository.save(phieuMuon);

        // Trừ số lượng sách trong kho
        sach.setSoLuong(sach.getSoLuong() - soLuong);
        sachRepository.save(sach);

        // Trả về phản hồi thành công
        return ResponseEntity.ok("Tạo phiếu mượn thành công.");
    }
}
