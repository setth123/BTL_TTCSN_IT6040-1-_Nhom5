document.getElementById('registrationForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const hoTen = document.getElementById('hoTen').value;
    const soDienThoai = document.getElementById('soDienThoai').value;
    const diaChi = document.getElementById('diaChi').value;
    const tenTK = document.getElementById('tenTK').value;
    const gioiTinh = document.getElementById('gioiTinh').value;
    const matKhau = document.getElementById('matKhau').value;
    const xacNhanMatKhau = document.getElementById('xacNhanMatKhau').value;

    const userData = {
        hoTen: hoTen,
        soDienThoai: soDienThoai,
        diaChi: diaChi,
        tenTK: tenTK,
        gioiTinh: gioiTinh,
        matKhau: matKhau,
        xacNhanMatKhau: xacNhanMatKhau
    };

    try {
        const response = await fetch('http://localhost:8080/api/nguoi-dung/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            // Nếu phản hồi không thành công, xử lý lỗi và lấy văn bản lỗi trả về
            const errorText = await response.text();
            throw new Error(errorText); // Ném lỗi với thông báo trả về từ backend
        }

        const data = await response.text(); // Trả về dữ liệu dạng text từ backend

        // Kiểm tra dữ liệu trả về từ backend
        if (data.includes('Đăng ký thành công')) {
            alert('Đăng ký thành công!');
            window.location.href = 'login.html';
        } else {
            // Nếu không phải thông báo thành công, hiển thị thông báo lỗi
            alert('Đăng ký thất bại: ' + data);
        }
    } catch (error) {
        console.log(error.message);
        alert('Đã xảy ra lỗi trong quá trình đăng ký: ' + error.message);
    }
});


document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const tenTK = document.getElementById('loginAccount').value;
    const matKhau = document.getElementById('loginPassword').value;

    const loginData = {
        tenTK: tenTK,
        matKhau: matKhau
    };

    try {
        const response = await fetch('http://localhost:8080/api/nguoi-dung/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        if (!response.ok) {
            // Nếu phản hồi không thành công, xử lý lỗi và lấy văn bản lỗi trả về
            const errorText = await response.text();
            throw new Error(errorText); // Ném lỗi với thông báo trả về từ backend
        }

        const data = await response.json(); // Nhận dữ liệu dạng JSON từ backend

        // Kiểm tra dữ liệu trả về (ví dụ, nếu đăng nhập thành công)
        if (data && data.maNguoiDung) { // Kiểm tra nếu có dữ liệu người dùng (maNguoiDung là không rỗng)
            alert('Đăng nhập thành công!');
            localStorage.setItem('accountName', data.maNguoiDung);
            localStorage.setItem('accountUsername', data.hoTen);
            window.location.href = 'TrangChu_NguoiDung.html';  // Chuyển hướng sau khi đăng nhập thành công
            
        } else {
            alert('Đăng nhập thất bại');
        }

    } catch (error) {
        console.log(error.message);
        alert('Đã xảy ra lỗi trong quá trình đăng nhập: ' + error.message);
    }
});
