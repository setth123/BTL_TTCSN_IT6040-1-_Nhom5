package com.example.Server.repository;

import com.example.Server.entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, String> {
    // Bạn có thể định nghĩa các phương thức truy vấn tùy chỉnh ở đây nếu cần
}
