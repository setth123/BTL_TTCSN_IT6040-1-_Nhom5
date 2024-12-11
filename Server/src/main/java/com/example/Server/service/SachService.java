package com.example.Server.service;

import com.example.Server.dto.SachDTO;
import com.example.Server.entity.Sach;
import com.example.Server.entity.TheLoai;
import com.example.Server.repository.SachRepository;
import com.example.Server.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SachService {

    @Autowired
    private SachRepository sachRepository;

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    // 1. Lấy tất cả sách
    public List<SachDTO> getAllSach(String keyword) {
        List<Sach> sachList = sachRepository.findAll();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sachList = sachRepository.findByTenSachContainingIgnoreCase(keyword);
        }
        else sachList=sachRepository.findAll();
        // Chuyển đổi từ Entity sang DTO trực tiếp
        return sachList.stream().map(sach -> new SachDTO(
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getNxb(),
                sach.getNph(),
                sach.getSoLuong(),
                sach.getSoTrang(),
                sach.getTacGia(),
                sach.getTheLoai() != null ? sach.getTheLoai().getMaTL() : null
        )).collect(Collectors.toList());
    }

    // 2. Thêm sách mới
    public List<SachDTO> createSach(SachDTO sachDTO) {
        // Lấy thể loại từ DB
        TheLoai theLoai = theLoaiRepository.findById(sachDTO.getMaTheLoai())
                .orElseThrow(() -> new RuntimeException("Thể loại không tồn tại"));

        // Chuyển đổi DTO sang Entity
        Sach sach = new Sach();
        sach.setMaSach("S_" + System.currentTimeMillis());
        sach.setTenSach(sachDTO.getTenSach());
        sach.setNxb(sachDTO.getNxb());
        sach.setNph(sachDTO.getNph());
        sach.setSoLuong(sachDTO.getSoLuong());
        sach.setSoTrang(sachDTO.getSoTrang());
        sach.setTacGia(sachDTO.getTacGia());
        sach.setTheLoai(theLoai);

        // Lưu sách và trả về DTO
        sachRepository.save(sach);
        return getAllSach();
    }

    // 3. Cập nhật sách
    public List<SachDTO> updateSach(String maSach, SachDTO sachDTO) {
        // Kiểm tra sách có tồn tại không
        Sach sach = sachRepository.findById(maSach)
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại"));
        sach.setTenSach(sachDTO.getTenSach());
        sach.setNxb(sachDTO.getNxb());
        sach.setNph(sachDTO.getNph());
        sach.setSoLuong(sachDTO.getSoLuong());
        sach.setSoTrang(sachDTO.getSoTrang());
        sach.setTacGia(sachDTO.getTacGia());

        TheLoai theLoai = theLoaiRepository.findById(sachDTO.getMaTheLoai())
            .orElseThrow(() -> new RuntimeException("Thể loại không tồn tại"));
        sach.setTheLoai(theLoai);
        sachRepository.save(sach);
        return getAllSach();
    }

    public List<SachDTO> deleteSach(String maSach){
        Sach sach = sachRepository.findById(maSach)
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại"));
        sachRepository.delete(sach);
        return getAllSach();
    }
}
      
