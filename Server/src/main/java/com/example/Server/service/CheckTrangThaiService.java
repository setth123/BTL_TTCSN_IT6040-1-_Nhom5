package com.example.Server.service;

import com.example.Server.repository.SachRepository;
import com.example.Server.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckTrangThaiService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private SachRepository sachRepository;

    public String checkTrangThai(String maNguoiDung, String maSach) {
        // Kiểm tra người dùng có tồn tại không
        if (nguoiDungRepository.findById(maNguoiDung).isEmpty()) {
            return "Người dùng không tồn tại.";
        }

        // Kiểm tra sách có tồn tại không
        if (sachRepository.findById(maSach).isEmpty()) {
            return "Sách không tồn tại.";
        }

        // Kiểm tra số lượng sách có lớn hơn 0 không
        if (sachRepository.findById(maSach).get().getSoLuong() <= 0) {
            return "Số lượng sách không đủ.";
        }

        return "Ổn";
    }
}
