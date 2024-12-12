async function fetchBooks() {
    try {
    const response = await fetch('http://localhost:8080/api/sach'); 
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    const books = await response.json();
    return books;
    } catch (error) {
    console.error('Fetch error:', error);
    }
}
async function getTheLoaiByCode(maTheLoai) {
    const response = await fetch('http://localhost:8080/api/tl/' + maTheLoai);
    const data = await response.json();
    return data.tenTL;
}
// Hàm hiển thị sách lên bảng
async function displayBooks() {
    const books = fetchBooks();
    const table = document.getElementById('availableBook');
    const tbody = table.getElementsByTagName('tbody')[0] || table.appendChild(document.createElement('tbody'));

    for (const [index, book] of books.entries()) {
    if (book.soLuong === 0) continue; // Bỏ qua sách có soLuong bằng 0
    const theloai = await getTheLoaiByCode(book.maTheLoai);

    const row = tbody.insertRow();

    const cellIndex = row.insertCell();
    cellIndex.textContent = index + 1;

    const cellMaSach = row.insertCell();
    cellMaSach.textContent = book.maSach;

    const cellTenSach = row.insertCell();
    cellTenSach.textContent = book.tenSach;

    const cellTheLoai = row.insertCell();
    cellTheLoai.textContent = theloai;

    const cellNhaXuatBan = row.insertCell();
    cellNhaXuatBan.textContent = book.nhaXuatBan;

    const cellSoTrang = row.insertCell();
    cellSoTrang.textContent = book.soTrang;

    const cellTacGia = row.insertCell();
    cellTacGia.textContent = book.tacGia;

    const cellHanhDong = row.insertCell();
    const borrowButton = document.createElement('button');
    borrowButton.textContent = 'Mượn';
    borrowButton.addEventListener('click', );
    cellHanhDong.appendChild(borrowButton);
    
}
}

// Giả định có hàm mượn sách
function borrowBook(maSach) {
    console.log(`Mượn sách có mã: ${maSach}`);
    // Thực hiện các xử lý khi mượn sách (gọi API, cập nhật giao diện, v.v.)
}

