package com.example.Server.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ThuThu {

    @Id
    private String maTK;

    private String tenTT;
    private String matKhau;

    // Getters and Setters
}
