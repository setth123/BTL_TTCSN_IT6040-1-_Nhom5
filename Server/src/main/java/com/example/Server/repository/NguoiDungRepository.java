package com.example.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.example.Server.entity.NguoiDung;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    // Tìm người dùng theo tên tài khoản
    Optional<NguoiDung> findByTenTK(String tenTK);

    List<NguoiDung> findByHoTenContainingIgnoreCase(String keyword);
}

