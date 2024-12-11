package com.example.Server.service;

import com.example.Server.entity.NguoiDung;
import com.example.Server.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class CheckTrangThaiService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;


    public String checkTrangThai(String maNguoiDung) {
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findById(maNguoiDung);
        
        if (nguoiDungOptional.isEmpty()) {
            return "Người dùng không tồn tại.";
        }
    
        NguoiDung nguoiDung = nguoiDungOptional.get();
    
        // Kiểm tra trạng thái vi phạm của người dùng
        if (nguoiDung.getTrangThaiVP() != null && nguoiDung.getTrangThaiVP()==true) {
            return "Người dùng đang trong trạng thái vi phạm.";
        }
    
        return "Người dùng không vi phạm.";
    }
    
}
