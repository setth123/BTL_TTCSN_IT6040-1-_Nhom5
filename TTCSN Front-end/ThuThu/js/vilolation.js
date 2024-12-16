let data = [];

async function getPhieuViPham() {
    const response = await fetch('http://localhost:8080/phieu-vi-pham');
    data = await response.json();
    const tbody = document.querySelector("#violation tbody");
    tbody.innerHTML = "";

    data.forEach((item, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${item.maPhieuVP}</td>
            <td>${(item.soTienPhat).toLocaleString()}</td>
            <td>${item.soNgayQuaHan}</td>
            <td>${item.trangThai ? "Đã thanh toán" : "Chưa thanh toán"}</td>
            <td>${item.phieuMuon.maPM}</td>
            <td>
                ${item.trangThai ? "" : `<button id="thanhtoan" onclick="handlePayment(${index})">Thanh toán</button>`}
            </td>
        `;

        tbody.appendChild(row);
    });
}

function handlePayment(index) {
    localStorage.setItem('violationID', data[index].maPhieuVP);
    localStorage.setItem('violationState', data[index].trangThai);
    openModal();

    const confirmButton = document.querySelector(".btn-dongy");
    confirmButton.onclick = function () {
        confirmPayment(); 
    };
}

function openModal() {
    document.getElementById("myModal").style.display = "block";
}

function closeModal() {
    document.getElementById("myModal").style.display = "none";
}

async function confirmPayment() {
    const maPhieuVP = localStorage.getItem('violationID');
    const state = localStorage.getItem('violationState');
    const response = await fetch ('http://localhost:8080/phieu-vi-pham/' + maPhieuVP + state, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    if (response.ok) {
        const dt = await response.json();
        const tbody = document.querySelector("#violation tbody");
        tbody.innerHTML = ""; 
        
        dt.forEach((item, index) => {
            const row = document.createElement("tr");
            
            row.innerHTML = `
            <td>${item.maPhieuVP}</td>
            <td>${(item.soTienPhat).toLocaleString()}</td>
            <td>${item.soNgayQuaHan}</td>
            <td>${item.trangThai ? "Đã thanh toán" : "Chưa thanh toán"}</td>
            <td>${item.phieuMuon.maPM}</td>
            <td>
                ${item.trangThai ? "" : `<button onclick="handlePayment(${index})">Thanh toán</button>`}
            </td>
            `;
            
            tbody.appendChild(row);
        });
        alert("Thanh toán đã thành công!");
        closeModal();
    } else {
        alert("Đã xảy ra lỗi khi cập nhật thanh toán phiếu vi phạm");
    }
}

// Đóng modal khi nhấn ra ngoài
window.onclick = function(event) {
    if (event.target == document.getElementById("myModal")) {
        closeModal();
    }
}

window.onload = getPhieuViPham;