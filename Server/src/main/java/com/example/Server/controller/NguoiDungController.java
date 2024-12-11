package com.example.Server.controller;

import com.example.Server.entity.NguoiDung;
import com.example.Server.repository.NguoiDungRepository;
import com.example.Server.service.NguoiDungService;
import com.example.Server.dto.LoginRequest;
import com.example.Server.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    //đăng ký người dùng
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
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
    public String setVP(@PathVariable String maNguoiDung, @PathVariable String state){
        Optional<NguoiDung> userOptional=ndr.findById(maNguoiDung);
        if(userOptional.isPresent()){
            NguoiDung user = userOptional.get();
            user.setTrangThaiTK(Boolean.parseBoolean(state));
            ndr.save(user);
            return "success";
        }
        else{
            return "fail";
        }
    }
    //if user TrangThaiVP equal to false change its to true and vice versa and

}
