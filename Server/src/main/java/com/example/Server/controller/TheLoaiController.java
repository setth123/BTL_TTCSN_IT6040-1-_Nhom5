package com.example.Server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Server.entity.TheLoai;
import com.example.Server.repository.TheLoaiRepository;

@RestController
@RequestMapping("/api/tl")
public class TheLoaiController {
    @Autowired
    private TheLoaiRepository tlr;        
    @GetMapping
    public ResponseEntity<List<TheLoai>> getAllTL() {
        List<TheLoai> tls = tlr.findAll();
        return new ResponseEntity<>(tls, HttpStatus.OK);
    }
}
