package com.example.Server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/{maTheLoai}")
    public ResponseEntity<TheLoai> getTheLoai(@PathVariable String maTheLoai){
        Optional<TheLoai> tl=tlr.findById(maTheLoai);
        if(tl.isPresent()){
            TheLoai theLoai = tl.get();
            return ResponseEntity.ok(theLoai);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        
    }
}
