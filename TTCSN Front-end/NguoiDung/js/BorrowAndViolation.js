async function displayBorrowList() {
    const maND = localStorage.getItem('accountName');
    const response = await fetch('http://localhost:8080/phieu-muon/' + maND); // Thay 'url' bằng URL thật
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



document.addEventListener('DOMContentLoaded', function () {
    displayBorrowList();
});
