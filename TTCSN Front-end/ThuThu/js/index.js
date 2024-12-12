const navbar = document.querySelector('nav');
navbar.addEventListener('click', (event) => {
    if (event.target.tagName === 'a') {
        event.preventDefault();
        const href = event.target.href;
        window.location.href = href;
    }
});

async function getSach() {
    const response = await fetch('http://localhost:8080/api/sach');
    const data = await response.json();
    return data;
}
async function getTheLoai(maTheLoai) {
    const response = await fetch('http://localhost:8080/api/tl/' + maTheLoai);
    const data = await response.json();
    return data.tenTL;
}

async function insertDataIntoTable() {
    
    const sachData = await getSach();
    
    
    const table = document.getElementById('newBook');
    for (let sach of sachData.slice(-5)) {
        const tenTheLoai = await getTheLoai(sach.maTheLoai);
        const row = table.insertRow();
        row.insertCell(0).innerText = sach.maSach;
        row.insertCell(1).innerText = sach.tenSach;
        row.insertCell(2).innerText = tenTheLoai;
        row.insertCell(3).innerText = sach.soLuong;
    }
}

async function getUsers() {
    const response = await fetch('http://localhost:8080/api/nguoi-dung/search');
    const data = await response.json();
    return data;
}

async function insertUsers() {
    const usersData = await getUsers();
    const table = document.getElementById('newUser');
    for (let user of usersData.slice(-5)){
        const row = table.insertRow();
        row.insertCell(0).innerText = user.maNguoiDung;
        row.insertCell(1).innerText = user.hoTen;
        row.insertCell(2).innerText = user.soDienThoai;
        row.insertCell(3).innerText = user.tenTK;
    }
}