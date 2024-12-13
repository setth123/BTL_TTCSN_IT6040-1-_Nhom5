let data = []; // Biến toàn cục để lưu danh sách phiếu mượn

async function getPhieuMuon() {
    const response = await fetch('http://localhost:8080/phieu-muon/danh-sach');
    data = await response.json(); // Lưu dữ liệu vào biến toàn cục
    const tbody = document.querySelector("#borrowNote tbody");
    tbody.innerHTML = ""; // Xóa nội dung cũ

    data.forEach((item, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${item.maPM}</td>
            <td>${new Date(item.thoiHan).toLocaleDateString()}</td>
            <td>${item.trangThai ? "Đã trả" : "Chưa trả"}</td>
            <td>${item.soLuongMuon}</td>
            <td>${item.nguoiDung.maNguoiDung}</td>
            <td>${item.sach.maSach}</td>
            <td>
                ${item.trangThai ? "" : `<button onclick="handleReturn(${index})">Trả sách</button>`}
            </td>
        `;

        tbody.appendChild(row);
    });
}

function handleReturn(index) {
    localStorage.setItem('borrowID', data[index].maPM);
    // Hiển thị modal
    openModal();

    // Thêm sự kiện xác nhận
    const confirmButton = document.querySelector(".btn-dongy");
    confirmButton.onclick = function () {
        confirmReturn(); // Gọi hàm xác nhận trả sách
    };
}

function openModal() {
    document.getElementById("myModal").style.display = "block";
}

function closeModal() {
    document.getElementById("myModal").style.display = "none";
}

async function confirmReturn() {
    const maPM = localStorage.getItem('borrowID');
    const response = await fetch('http://localhost:8080/phieu-muon/return/' + maPM, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const dt = await response.json();
        const tbody = document.querySelector("#borrowNote tbody");
        tbody.innerHTML = ""; // Xóa nội dung cũ
        
        dt.forEach((item, index) => {
            const row = document.createElement("tr");
            
            row.innerHTML = `
            <td>${item.maPM}</td>
            <td>${new Date(item.thoiHan).toLocaleDateString()}</td>
            <td>${item.trangThai ? "Đã trả" : "Chưa trả"}</td>
            <td>${item.soLuongMuon}</td>
            <td>${item.nguoiDung.maNguoiDung}</td>
            <td>${item.sach.maSach}</td>
            <td>
            ${item.trangThai ? "" : `<button onclick="handleReturn(${index})">Trả sách</button>`}
            </td>
            `;
            
            tbody.appendChild(row);
        });
        alert("Sách đã được trả thành công!");
        closeModal();
    } else {
        alert("Đã xảy ra lỗi khi cập nhật trạng thái phiếu mượn.");
    }
}

// Đóng modal khi nhấn ra ngoài
window.onclick = function(event) {
    if (event.target == document.getElementById("myModal")) {
        closeModal();
    }
}

// Hiển thị bảng khi trang được tải
window.onload = getPhieuMuon;
