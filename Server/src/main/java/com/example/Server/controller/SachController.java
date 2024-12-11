package com.example.Server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Server.service.SachService;
import com.example.Server.dto.SachDTO;

@CrossOrigin(origins ="http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/sach")
public class SachController {
    @Autowired
    private SachService sachService;

    @GetMapping
    public ResponseEntity<List<SachDTO>> getAllSach() {
        List<SachDTO> sachDTOList = sachService.getAllSach(null);
        return new ResponseEntity<>(sachDTOList, HttpStatus.OK);
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<SachDTO>> searchSach(@RequestBody String keyword) {
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
}
