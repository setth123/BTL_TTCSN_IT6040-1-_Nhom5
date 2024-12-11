package com.example.Server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Server.dto.LoginRequest;
import com.example.Server.dto.RegisterRequest;
import com.example.Server.entity.NguoiDung;
import com.example.Server.repository.NguoiDungRepository;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public void registerUser(RegisterRequest request) {
        // Kiểm tra tính hợp lệ của mật khẩu
        if (!request.getMatKhau().equals(request.getXacNhanMatKhau())) {
            throw new IllegalArgumentException("Mật khẩu không trùng khớp");
        }

        // Kiểm tra tính duy nhất của tên tài khoản
        if (nguoiDungRepository.findByTenTK(request.getTenTK()).isPresent()) {
            throw new IllegalArgumentException("Tên tài khoản đã có người sử dụng");
        }

        // Tạo đối tượng NguoiDung
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMaNguoiDung("ND_" + System.currentTimeMillis()); // Sinh mã ngẫu nhiên
        nguoiDung.setHoTen(request.getHoTen());
        nguoiDung.setSoDienThoai(request.getSoDienThoai());
        nguoiDung.setGioiTinh(request.getGioiTinh());
        nguoiDung.setDiaChi(request.getDiaChi());
        nguoiDung.setMatKhau(request.getMatKhau()); // Lưu ý: Nên mã hóa mật khẩu
        nguoiDung.setTenTK(request.getTenTK());
        nguoiDung.setSoLanViPham(0); // Mặc định số lần vi phạm là 0
        nguoiDung.setTrangThaiTK(true); // không bị khóa
        nguoiDung.setTrangThaiVP(false); // không vi phạm 
        // Lưu thông tin người dùng vào cơ sở dữ liệu
        nguoiDungRepository.save(nguoiDung);
    }

     public NguoiDung loginUser(LoginRequest loginRequest) {
        // Kiểm tra tên tài khoản
        NguoiDung nguoiDung = nguoiDungRepository.findByTenTK(loginRequest.getTenTK())
                .orElseThrow(() -> new IllegalArgumentException("Tên tài khoản không tồn tại"));

        // Kiểm tra mật khẩu
        if (!nguoiDung.getMatKhau().equals(loginRequest.getMatKhau())) {
            throw new IllegalArgumentException("Mật khẩu không đúng");
        }

        // Nếu đăng nhập thành công
        return nguoiDung;  
    }
}
