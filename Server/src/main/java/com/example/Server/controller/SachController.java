package com.example.Server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Server.dto.SachDTO;
import com.example.Server.entity.Sach;
import com.example.Server.repository.SachRepository;
import com.example.Server.service.SachService;


@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/sach")
public class SachController {
    @Autowired
    private SachService sachService;

    @Autowired
    private SachRepository sr;
    @GetMapping
    public ResponseEntity<List<SachDTO>> getAllSach() {
        List<SachDTO> sachDTOList = sachService.getAllSach(null);
        return new ResponseEntity<>(sachDTOList, HttpStatus.OK);
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<SachDTO>> searchSach(@PathVariable String keyword) {
        List<SachDTO> sachDTOList = sachService.getAllSach(keyword);
        return new ResponseEntity<>(sachDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<SachDTO>> createSach(@RequestBody SachDTO sachDTO) {
        List<SachDTO> sachDTOList = sachService.createSach(sachDTO);
        return new ResponseEntity<>(sachDTOList, HttpStatus.CREATED);
    }

    @PutMapping("/{maSach}")
    public ResponseEntity<List<SachDTO>> updateSach(@PathVariable("maSach") String maSach, @RequestBody SachDTO sachDTO) {
        List<SachDTO> sachDTOList = sachService.updateSach(maSach, sachDTO);
        return new ResponseEntity<>(sachDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{maSach}")
    public ResponseEntity<List<SachDTO>> deleteSach(@PathVariable("maSach") String maSach) {
        List<SachDTO> sachDTOList = sachService.deleteSach(maSach);
        return new ResponseEntity<>(sachDTOList, HttpStatus.OK);
    }
    @GetMapping("/id/{maSach}")
    public ResponseEntity<Sach> getSachbyId(@PathVariable("maSach") String maSach) {
        Optional<Sach> sachOptional = sr.findById(maSach);
        if (sachOptional.isPresent()) {
            return new ResponseEntity<>(sachOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Nếu không tìm thấy sách
        }   
    }
}
