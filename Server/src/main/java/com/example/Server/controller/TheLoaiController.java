package com.example.Server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Server.dto.SachDTO;
import com.example.Server.entity.TheLoai;
import com.example.Server.repository.TheLoaiRepository;

@RestController
@RequestMapping("/api/tl")
public class TheLoaiController {
    //@GetMapping
    // public ResponseEntity<List<TheLoai>> getAllTL() {
    //     List<TheLoai> tl = TheLoaiRepository.
    //     return new ResponseEntity<>(sachDTOList, HttpStatus.OK);
    // }
}
