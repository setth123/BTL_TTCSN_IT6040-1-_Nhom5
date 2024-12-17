async function displayBorrowList() {
    const maND = localStorage.getItem('accountName');
    const response = await fetch('http://localhost:8080/phieu-muon/' + maND);
    const datas = await response.json();

    const table = document.getElementById('borrow');
    
    const tbody = table.getElementsByTagName('tbody')[0] || table.appendChild(document.createElement('tbody'));

    // Xóa các dòng cũ trong bảng trước khi thêm mới
    tbody.innerHTML = '';

    for (const [index, data] of datas.entries()) {
        const row = tbody.insertRow();

        // Cột số thứ tự
        const cellIndex = row.insertCell();
        cellIndex.textContent = index + 1;

        // Cột mã phiếu mượn
        const cellmaPM = row.insertCell();
        cellmaPM.textContent = data.maPM.trim();

        // Cột tên sách
        const celltenSach = row.insertCell();
        celltenSach.textContent = data.sach.tenSach;

        // Cột thời hạn (định dạng lại ngày tháng)
        const cellThoiHan = row.insertCell();
        const thoiHanDate = new Date(data.thoiHan);
        cellThoiHan.textContent = thoiHanDate.toLocaleDateString('vi-VN');

        // Cột số lượng mượn
        const cellSoLuong = row.insertCell();
        cellSoLuong.textContent = data.soLuongMuon;

        // Cột trạng thái
        const cellTrangThai = row.insertCell();
        cellTrangThai.textContent = data.trangThai ? 'Đã trả' : 'Đang mượn';
    }
}

async function getViPham() {
    const maND = localStorage.getItem('accountName');
    const response = await fetch('http://localhost:8080/phieu-vi-pham/' + maND);
    const datas = await response.json();

    const table = document.getElementById('violation');
    
    const tbody = table.getElementsByTagName('tbody')[0] || table.appendChild(document.createElement('tbody'));
    tbody.innerHTML = '';

    for (const [index, data] of datas.entries()) {
        const row = tbody.insertRow();

        const cellIndex = row.insertCell();
        cellIndex.textContent = index + 1;

        // Cột mã phiếu mượn
        const cellmaVP = row.insertCell();
        cellmaVP.textContent = data.maPhieuVP.trim();

        // Cột tên sách
        const celltenSach = row.insertCell();
        celltenSach.textContent = data.phieuMuon.sach.tenSach;

        // Cột thời hạn (định dạng lại ngày tháng)
        const cellQuaHan = row.insertCell();
        cellQuaHan.textContent = data.soNgayQuaHan;

        // Cột số lượng mượn
        const cellPhat = row.insertCell();
        cellPhat.textContent = data.soTienPhat.toLocaleString();

        // Cột trạng thái
        const cellTrangThai = row.insertCell();
        cellTrangThai.textContent = data.trangThai ? 'Đã thanh toán' : 'Chưa thanh toán';
    }
}

document.addEventListener('DOMContentLoaded',async function () {
    await displayBorrowList();
    await getViPham();
});
