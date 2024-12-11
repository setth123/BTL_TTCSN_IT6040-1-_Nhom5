package com.example.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.Server.entity.ThuThu;

@Repository
public interface ThuThuRepository extends JpaRepository<ThuThu, String> {
    // Tìm người dùng theo tên tài khoản
    Optional<ThuThu> findByTenTT(String tenTK);

}