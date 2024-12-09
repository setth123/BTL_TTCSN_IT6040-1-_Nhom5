package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class TheLoai {

    @Id
    private String maTL;

    private String tenTL;

    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL)
    private List<Sach> sachs;

    // Getters and Setters
}
