package com.example.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Server.dto.LoginRequest;
import com.example.Server.entity.ThuThu;
import com.example.Server.repository.ThuThuRepository;

@Service
public class ThuThuService {
    @Autowired
    private ThuThuRepository ttr;
    public ThuThu loginAdmin(LoginRequest loginRequest) {
        // Kiểm tra tên tài khoản
        ThuThu admin = ttr.findByTenTT(loginRequest.getTenTK())
                .orElseThrow(() -> new IllegalArgumentException("Tên tài khoản không tồn tại"));

        // Kiểm tra mật khẩu
        if (!admin.getMatKhau().equals(loginRequest.getMatKhau())) {
            throw new IllegalArgumentException("Mật khẩu không đúng");
        }

        // Nếu đăng nhập thành công
        return admin;  
    }
}
