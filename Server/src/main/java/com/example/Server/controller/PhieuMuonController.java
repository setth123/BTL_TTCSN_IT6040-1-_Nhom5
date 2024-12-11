package com.example.Server.controller;

import com.example.Server.dto.CheckRequest;
import com.example.Server.dto.PhieuMuonRequest;
import com.example.Server.entity.NguoiDung;
import com.example.Server.entity.PhieuMuon;
import com.example.Server.entity.PhieuViPham;
import com.example.Server.entity.Sach;
import com.example.Server.repository.NguoiDungRepository;
import com.example.Server.repository.PhieuMuonRepository;
import com.example.Server.repository.PhieuViPhamRepository;
import com.example.Server.repository.SachRepository;
import com.example.Server.service.CheckTrangThaiService;
import com.example.Server.service.PhieuMuonService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/phieu-muon")
public class PhieuMuonController {

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private PhieuViPhamRepository phieuViPhamRepository;

    @Autowired
    private NguoiDungRepository ndr;
    @Autowired
    private SachRepository sr;
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

    @PutMapping("/return/{maPhieuMuon}")
    public ResponseEntity<?> returnBook(@PathVariable String maPhieuMuon) {
        Optional<PhieuMuon> pmOpt=phieuMuonRepository.findById(maPhieuMuon);
        if(pmOpt.isPresent()){
            PhieuMuon pm=pmOpt.get();
            pm.setTrangThai(true);
            phieuMuonRepository.save(pm);
            Sach s=pm.getSach();
            s.setSoLuong(s.getSoLuong()+pm.getSoLuongMuon());
            sr.save(s);
            //create violation slip
            int soNgayQuaHan =(int) ChronoUnit.DAYS.between(pm.getThoiHan(), LocalDateTime.now());

            if (soNgayQuaHan > 0) { // Chỉ tạo phiếu phạt nếu quá hạn
                PhieuViPham vs = new PhieuViPham();
                vs.setMaPhieuVP("VP_"+ System.currentTimeMillis());
                vs.setPhieuMuon(pm);
                vs.setSoNgayQuaHan(soNgayQuaHan);
                vs.setSoTienPhat(tinhTienPhat(soNgayQuaHan)); // Hàm tính tiền phạt
                vs.setTrangThai(false);
                phieuViPhamRepository.save(vs); // Lưu phiếu phạt vào cơ sở dữ liệu
                
                NguoiDung nd=pm.getNguoiDung();
                nd.setSoLanViPham(nd.getSoLanViPham()+1);
                nd.setTrangThaiVP(true);
                ndr.save(nd);
            }

        }
        //return borrow slip list
        return getDanhSachPhieuMuon();
    }
    private double tinhTienPhat(int soNgayQuaHan) {
        if (soNgayQuaHan <= 14) {
            // Từ ngày thứ 8 đến ngày thứ 14, 5.000 đồng/ngày
            return (soNgayQuaHan ) * 5000;
        } else {
            // Sau ngày thứ 15, tăng thêm 2.000 đồng/ngày so với mức trước đó
            int ngayTu8Den14 = 14 - 7; // 7 ngày đầu tiên phạt 5.000 đồng/ngày
            int ngaySau14 = soNgayQuaHan - 14; // Các ngày sau ngày thứ 14
            return (ngayTu8Den14 * 5000) + (ngaySau14 * 7000);
        }
    }
}
