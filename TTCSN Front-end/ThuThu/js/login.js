document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const tenTK = document.getElementById('tenTK').value;
    const matKhau = document.getElementById('matKhau').value;

    const loginData = {
        tenTK: tenTK,
        matKhau: matKhau
    };

    try {
        const response = await fetch('http://localhost:8080/api/admin/login', {
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
        if (data) {
            alert('Đăng nhập thành công!');
            localStorage.setItem('accountName', data.maTK);
            localStorage.setItem('accountUsername', data.tenTT);
            window.location.href = 'TrangChu_ThuThu.html';  // Chuyển hướng sau khi đăng nhập thành công
            
        } else {
            alert('Đăng nhập thất bại');
        }

    } catch (error) {
        console.log(error.message);
        alert('Đã xảy ra lỗi trong quá trình đăng nhập: ' + error.message);
    }
});