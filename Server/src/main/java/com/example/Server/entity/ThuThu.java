package com.example.Server.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="ThuThu")
public class ThuThu {

    @Id
    @Column(name="maTK")
    private String maTK;
    @Column(name="tenTT")
    private String tenTT;
    @Column(name="matKhau")
    private String matKhau;

    // Getters and Setters
}
