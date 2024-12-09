package com.example.Server.controller;

import com.example.Server.entity.NguoiDung;
import com.example.Server.repository.NguoiDungRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nguoi-dung")
public class NguoiDungController {
    @Autowired
    private NguoiDungRepository ndr;

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
