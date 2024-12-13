let selectedId;
let selectedBook;
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
    const books = await fetchBooks();
    const table = document.getElementById('availableBook');
    const tbody = table.getElementsByTagName('tbody')[0] || table.appendChild(document.createElement('tbody'));

    for (const [index, book] of books.entries()) {
        if (book.soLuong === 0) continue; // Bỏ qua sách có soLuong bằng 0
        const theloai = await getTheLoaiByCode(book.maTheLoai);

        const row = tbody.insertRow();

        const cellIndex = row.insertCell();
        cellIndex.textContent = index + 1;

        const cellTenSach = row.insertCell();
        cellTenSach.textContent = book.tenSach;

        const cellTheLoai = row.insertCell();
        cellTheLoai.textContent = theloai;

        const cellNhaXuatBan = row.insertCell();
        cellNhaXuatBan.textContent = book.nxb;

        const cellSoLuong=row.insertCell();
        cellSoLuong.textContent=book.soLuong;

        const cellSoTrang = row.insertCell();
        cellSoTrang.textContent = book.soTrang;

        const cellTacGia = row.insertCell();
        cellTacGia.textContent = book.tacGia;

        const cellHanhDong = row.insertCell();
        const borrowButton = document.createElement('button');
        borrowButton.textContent = 'Mượn';
        borrowButton.dataset.maSach = book.maSach;
        borrowButton.dataset.tenSach = book.tenSach;
        borrowButton.addEventListener('click', async ()=>{
            selectedId = borrowButton.dataset.maSach;
            selectedBook = borrowButton.dataset.tenSach;
            localStorage.setItem('selectedBook', selectedBook);
            localStorage.setItem('selectedId', selectedId);
            const userId = localStorage.getItem('accountName');
            
            try {
                const userResponse = await fetch('http://localhost:8080/api/nguoi-dung/' + userId);
                if (!userResponse.ok) {
                    throw new Error('Network response was not ok');
                }
                const user = await userResponse.json();
                if (user.trangThaiVP === false) {
                    window.location.href = './Tao_Phieu_Muon.html';
                } else {
                    alert('Bạn không thể mượn sách do đang vi phạm');
                }
            } catch (error) {
                console.error('Fetch user error:', error);
                alert('Có lỗi xảy ra khi kiểm tra trạng thái người dùng');
            }
            
        });
        cellHanhDong.appendChild(borrowButton);
        
    }
}


