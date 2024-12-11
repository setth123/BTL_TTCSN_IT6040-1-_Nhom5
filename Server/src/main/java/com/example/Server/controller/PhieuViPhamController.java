package com.example.Server.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Server.entity.PhieuViPham;
import com.example.Server.repository.PhieuViPhamRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/api/vipham")
public class PhieuViPhamController {
    @Autowired
    PhieuViPhamRepository pvpr;

    //find users by keyword, if keyword equal to '' get all users
    @GetMapping
    public List<PhieuViPham> getPVP() {
        return pvpr.findAll();
    }
    @PutMapping("/{maPhieuVP}/{state}")
    public List<PhieuViPham> setVP(@PathVariable String maPhieuVP, @PathVariable String state){
        Optional<PhieuViPham> pvp=pvpr.findById(maPhieuVP);
        if(pvp.isPresent()){
            PhieuViPham violation = pvp.get();
            violation.setTrangThai(!Boolean.parseBoolean(state));
            pvpr.save(violation);
        }
            return getPVP();
    }
}
