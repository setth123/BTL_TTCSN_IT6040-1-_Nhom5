package com.example.Server.controller;

import com.example.Server.dto.CheckRequest;
import com.example.Server.dto.PhieuMuonRequest;
import com.example.Server.entity.PhieuMuon;
import com.example.Server.repository.PhieuMuonRepository;
import com.example.Server.service.CheckTrangThaiService;
import com.example.Server.service.PhieuMuonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phieu-muon")
public class PhieuMuonController {

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private CheckTrangThaiService checkTrangThaiService;

    @GetMapping("/danh-sach")
    public ResponseEntity<?> getDanhSachPhieuMuon() {
        // Lấy danh sách tất cả phiếu mượn từ cơ sở dữ liệu
        List<PhieuMuon> danhSachPhieuMuon = phieuMuonRepository.findAll();

        return ResponseEntity.ok(danhSachPhieuMuon);
    }
    @GetMapping("/{maND}")
    public ResponseEntity<?> getPhieuMuonByNguoiDung(@PathVariable String maND) {
        List<PhieuMuon> phieuMuons = phieuMuonRepository.findByNguoiDung_MaNguoiDung(maND);
        
        if (phieuMuons.isEmpty()) {
            return ResponseEntity.badRequest().body("Không có phiếu mượn cho người dùng này.");
        }
        
        return ResponseEntity.ok(phieuMuons); // Trả về danh sách phiếu mượn
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkTrangThai(@RequestBody CheckRequest request) {
        String maNguoiDung = request.getMaNguoiDung();
    
        // Kiểm tra trạng thái người dùng thông qua service
        String trangThai = checkTrangThaiService.checkTrangThai(maNguoiDung);
    
        // Trả về thông báo tương ứng với trạng thái
        if (trangThai.equals("Người dùng không tồn tại.")) {
            return ResponseEntity.badRequest().body(trangThai);
        } else if (trangThai.equals("Người dùng đang trong trạng thái vi phạm.")) {
            return ResponseEntity.badRequest().body(trangThai);
        } else {
            return ResponseEntity.ok(trangThai);  // Nếu trạng thái không vi phạm
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPhieuMuon(@RequestBody PhieuMuonRequest request) {
        // Lấy các thông tin từ request
        String maSach = request.getMaSach();
        Integer soLuong = request.getSoLuongMuon();
        String maNguoiDung = request.getMaNguoiDung();

        // Gọi service để xử lý
        return phieuMuonService.createPhieuMuon(maSach, soLuong, maNguoiDung);
    }
}
