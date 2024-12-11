package com.example.Server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Server.dto.LoginRequest;
import com.example.Server.entity.ThuThu;
import com.example.Server.service.ThuThuService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api/admin")
public class ThuThuController {
    @Autowired
    private ThuThuService thuThuService;    
    @PostMapping("/login")
     public ResponseEntity<Object> login(@RequestBody LoginRequest admin){
        try {
            ThuThu a= thuThuService.loginAdmin(admin);
            return ResponseEntity.ok(a);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
     }
}
