package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class TheLoai {

    @Id
    private String maTL;

    private String tenTL;

    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL)
    private List<Sach> sachs;

    // Getters and Setters
}
