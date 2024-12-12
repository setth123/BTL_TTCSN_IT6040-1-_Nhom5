const navbar = document.querySelector('nav');
navbar.addEventListener('click', (event) => {
    if (event.target.tagName === 'a') {
        event.preventDefault();
        const href = event.target.href;
        window.location.href = href;
    }
});


// Hàm lấy dữ liệu sách từ API getSach
async function getSach() {
    const response = await fetch('http://localhost:8080/api/sach'); // Thay API_URL_getSach bằng URL thực tế
    const data = await response.json();
    return data;
}

// Hàm lấy dữ liệu thể loại từ API getTheLoai
async function getTheLoai(maTheLoai) {
    const response = await fetch('http://localhost:8080/api/tl/' + maTheLoai); // Thay API_URL_getTheLoai bằng URL thực tế
    const data = await response.json();
    return data.tenTL;
}

// Hàm chèn dữ liệu vào bảng HTML
async function insertDataIntoTable() {
    // Lấy dữ liệu sách
    const sachData = await getSach();
    
    // Lấy bảng có id là 'newBook'
    const table = document.getElementById('newBook');
    const row=table.insertRow();
    row.insertCell(0).outerHTML= "<th>Tên sách</th>";
    row.insertCell(1).outerHTML = "<th>Nhà xuất bản</th>";
    row.insertCell(2).outerHTML = "<th>Số lượng</th>";
    row.insertCell(3).outerHTML = "<th>Số trang</th>";
    row.insertCell(4).outerHTML = "<th>Tác giả</th>";
    row.insertCell(5).outerHTML = "<th>Thể loại</th>";
    // Duyệt qua dữ liệu sách và thêm từng dòng vào bảng
    for (let sach of sachData.slice(-4)) {
        // Lấy tên thể loại từ mã thể loại
        const tenTheLoai = await getTheLoai(sach.maTheLoai);

        // Tạo dòng mới cho bảng
        const row = table.insertRow();
        row.insertCell(0).innerText = sach.tenSach;
        row.insertCell(1).innerText = sach.nxb;
        row.insertCell(2).innerText = sach.soLuong;
        row.insertCell(3).innerText = sach.soTrang;
        row.insertCell(4).innerText = sach.tacGia;
        row.insertCell(5).innerText = tenTheLoai; // Thay mã thể loại bằng tên thể loại
    }
}

