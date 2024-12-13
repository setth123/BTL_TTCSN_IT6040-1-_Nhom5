// Fetch danh sách sách theo từ khóa
async function getSachbyKeyword(keyword) {
    try {
        const response = await fetch('http://localhost:8080/api/sach/' + keyword);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return await response.json();
    } catch (error) {
        console.error('Fetch error:', error);
        return []; // Trả về mảng rỗng nếu lỗi
    }
}

// Hiển thị danh sách sách
async function displaySachList() {
    const keyword = localStorage.getItem('keyword');
    const sachList = await getSachbyKeyword(keyword);

    const bookList = document.getElementById('bookList');
    bookList.innerHTML = ''; // Xóa nội dung cũ

    if (sachList.length === 0) {
        bookList.innerHTML = `<p>Không tìm thấy sách nào với từ khóa "${keyword}".</p>`;
    } else {
        sachList.forEach(sach => {
            const bookItem = document.createElement('div');
            bookItem.className = 'book-item';
            bookItem.textContent = sach.tenSach;

            const detailButton = document.createElement('button');
            detailButton.textContent = 'Xem chi tiết';
            detailButton.addEventListener('click', () => displayBookDetails(sach));

            bookItem.appendChild(detailButton);
            bookList.appendChild(bookItem);
        });
    }
}

// Lấy thể loại sách theo mã thể loại
async function getTheLoaiByCode(maTheLoai) {
    try {
        const response = await fetch('http://localhost:8080/api/tl/' + maTheLoai);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        return data.tenTL;
    } catch (error) {
        console.error('Fetch error:', error);
        return 'Không xác định';
    }
}


// Hiển thị thông tin chi tiết về một sách
async function displayBookDetails(book) {
    const theloai = await getTheLoaiByCode(book.maTheLoai);
    const table = document.getElementById('availableBook');
    const tbody = table.getElementsByTagName('tbody')[0] || table.appendChild(document.createElement('tbody'));

    tbody.innerHTML = ''; // Clear existing rows

    const row = tbody.insertRow();

    const cellIndex = row.insertCell();
    cellIndex.textContent = book.maSach;

    const cellTenSach = row.insertCell();
    cellTenSach.textContent = book.tenSach;

    const cellTheLoai = row.insertCell();
    cellTheLoai.textContent = theloai;

    const cellNhaXuatBan = row.insertCell();
    cellNhaXuatBan.textContent = book.nxb;

    const cellSoTrang = row.insertCell();
    cellSoTrang.textContent = book.soTrang;

    const cellTacGia = row.insertCell();
    cellTacGia.textContent = book.tacGia;

    const cellHanhDong = row.insertCell();
    const borrowButton = document.createElement('button');
    borrowButton.textContent = 'Mượn';
    borrowButton.dataset.maSach = book.maSach;
    borrowButton.dataset.tenSach = book.tenSach;
    borrowButton.addEventListener('click', async () => {
        const selectedId = borrowButton.dataset.maSach;
        const selectedBook = borrowButton.dataset.tenSach;
        localStorage.setItem('selectedBook', selectedBook);
        localStorage.setItem('selectedId', selectedId);
        const userId = localStorage.getItem('accountName');

        try {
            const userResponse = await fetch('http://localhost:8080/api/nguoi-dung/' + userId);
            if (!userResponse.ok) {
                throw new Error('Network response was not ok');
            }
            const user = await userResponse.json();
            if (!user.trangThaiVP) {
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


// Khi trang được tải, hiển thị danh sách sách
document.addEventListener('DOMContentLoaded', function () {
    displaySachList();
});
