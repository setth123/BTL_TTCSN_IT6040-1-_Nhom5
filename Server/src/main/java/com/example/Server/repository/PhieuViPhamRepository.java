package com.example.Server.repository;

import com.example.Server.entity.PhieuViPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuViPhamRepository extends JpaRepository<PhieuViPham, String> {
}
