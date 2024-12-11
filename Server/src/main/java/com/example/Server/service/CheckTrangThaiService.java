package com.example.Server.service;

import com.example.Server.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckTrangThaiService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;


    public String checkTrangThai(String maNguoiDung) {
        // Kiểm tra người dùng có tồn tại không
        if (nguoiDungRepository.findById(maNguoiDung).isEmpty()) {
            return "Người dùng không tồn tại.";
        }
        //kiểm tra trạng thái vi phạm của người dùng: 
        return "Ổn";
    }
}
