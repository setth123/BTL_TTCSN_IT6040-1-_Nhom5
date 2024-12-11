package com.example.Server.controller;

import com.example.Server.entity.NguoiDung;
import com.example.Server.repository.NguoiDungRepository;
import com.example.Server.service.NguoiDungService;

import jakarta.validation.Valid;

import com.example.Server.dto.LoginRequest;
import com.example.Server.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nguoi-dung")
public class NguoiDungController {
    @Autowired
    private NguoiDungRepository ndr;

    @Autowired
    private NguoiDungService nguoiDungService;
    
    // Đăng ký người dùng
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
        // Kiểm tra lỗi xác thực
        if (bindingResult.hasErrors()) {
            // Tạo thông báo lỗi từ bindingResult
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        try {
            nguoiDungService.registerUser(request);
            return ResponseEntity.ok("Đăng ký thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // đăng nhập người dùng
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            NguoiDung nd= nguoiDungService.loginUser(loginRequest);
            return ResponseEntity.ok(nd);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //find users by keyword, if keyword equal to '' get all users
    @GetMapping("/search")
    public List<NguoiDung> searchUsers(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            // Nếu không có từ khoá, lấy tất cả người dùng
            return ndr.findAll();
        }
        // Nếu có từ khoá, tìm kiếm người dùng theo tên hoặc các thuộc tính khác
        return ndr.findByHoTenContainingIgnoreCase(keyword); // Giả sử bạn tìm theo tên
    }
    @PutMapping("/{maNguoiDung}/{state}")
    public List<NguoiDung> setVP(@PathVariable String maNguoiDung, @PathVariable String state){
        Optional<NguoiDung> userOptional=ndr.findById(maNguoiDung);
        if(userOptional.isPresent()){
            NguoiDung user = userOptional.get();
            user.setTrangThaiTK(Boolean.parseBoolean(state));
            ndr.save(user);
        }
        return searchUsers(null);
    }

}
