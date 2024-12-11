package com.example.Server.repository;

import com.example.Server.entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SachRepository extends JpaRepository<Sach, String> {
    List<Sach> findByTenSachContainingIgnoreCase(@Param("keyword") String keyword);
}
