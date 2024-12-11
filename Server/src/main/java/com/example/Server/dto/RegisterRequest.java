package com.example.Server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Họ tên không được để trống.")
    private String hoTen;

    @NotBlank(message = "Số điện thoại không được để trống.")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải gồm 10 chữ số.")
    private String soDienThoai;

    @NotBlank(message = "Giới tính không được để trống.")
    private String gioiTinh;

    @NotBlank(message = "Địa chỉ không được để trống.")
    private String diaChi;

    @NotBlank(message = "Tên tài khoản không được để trống.")
    @Size(min = 4, max = 20, message = "Tên tài khoản phải từ 4 đến 20 ký tự.")
    private String tenTK;

    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự.")
    private String matKhau;

    @NotBlank(message = "Xác nhận mật khẩu không được để trống.")
    private String xacNhanMatKhau;
}
