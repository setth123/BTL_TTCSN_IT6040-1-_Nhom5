package com.example.Server.repository;

import com.example.Server.entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SachRepository extends JpaRepository<Sach, String> {
    // Custom queries nếu cần
}
