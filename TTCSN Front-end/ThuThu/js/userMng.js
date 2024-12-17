async function getUsers() {
    const response = await fetch('http://localhost:8080/api/nguoi-dung/search');
    data = await response.json();
    const tbody = document.querySelector("#user tbody");
    tbody.innerHTML = "";
    data.forEach((item, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${item.maNguoiDung}</td>
            <td>${(item.hoTen).toLocaleString()}</td>
            <td>${item.soDienThoai}</td>
            <td>${item.gioiTinh}</td>
            <td>${item.tenTK}</td>
            <td>${item.soLanViPham}</td>
            <td>${item.trangThaiVP ? "Vi phạm" : "Không vi phạmphạm"}</td>
            <td>
                ${item.trangThaiTK ? `<button id="ban" onclick="handlePunish(${index})">Khóa</button>` : `<button id="ban" onclick="handlePunish(${index})">Mở khóa</button>`}
            </td>
        `;

        tbody.appendChild(row);
    });
}

function handlePunish(index) {
    localStorage.setItem('userID', data[index].maNguoiDung);
    localStorage.setItem('userState', data[index].trangThaiTK);
    openModal();

    const confirmButton = document.querySelector(".btn-dongy");
    confirmButton.onclick = function () {
        confirmPunish(); 
    };
}




function openModal() {
    document.getElementById("myModal").style.display = "block";
}

function closeModal() {
    document.getElementById("myModal").style.display = "none";
}

async function confirmPunish() {
    const maND = localStorage.getItem('userID');
    const state = localStorage.getItem('userState');
    const response = await fetch ('http://localhost:8080/api/nguoi-dung/' + maND + '/' + state, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    if (response.ok) {
        const dt = await response.json();
        const tbody = document.querySelector("#user tbody");
        tbody.innerHTML = ""; 
        
        dt.forEach((item, index) => {
            const row = document.createElement("tr");
            
            row.innerHTML = `
            <td>${item.maNguoiDung}</td>
            <td>${(item.hoTen).toLocaleString()}</td>
            <td>${item.soDienThoai}</td>
            <td>${item.gioiTinh}</td>
            <td>${item.tenTK}</td>
            <td>${item.soLanViPham}</td>
            <td>${item.trangThaiVP ? "Vi phạm" : "Không vi phạmphạm"}</td>
            <td>
                ${item.trangThaiTK ? `<button id="ban" onclick="handlePunish(${index})">Khóa</button>` : `<button id="ban" onclick="handlePunish(${index})">Mở khóa</button>`}
            </td>
        `;
            
            tbody.appendChild(row);
        });
    }
    alert("Tài khoản đã bị khóa!");
    closeModal();
}

window.onclick = function(event) {
    if (event.target == document.getElementById("myModal")) {
        closeModal();
    }
}

window.onload = getUsers;