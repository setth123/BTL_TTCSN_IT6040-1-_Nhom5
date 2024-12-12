package com.example.Server.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "TheLoai")
public class TheLoai {

    @Id
    @Column(name="maTL")
    private String maTL;
    @Column(name="tenTL")
    private String tenTL;

    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sach> sachs;

    // Getters and Setters
}
