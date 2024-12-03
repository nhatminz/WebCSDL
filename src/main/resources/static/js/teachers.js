function showAddTeacherForm() {
    document.getElementById('addTeacherForm').style.display = 'block';
    document.getElementById('teachersList').style.display = 'none';
}

function backToTable() {
    document.getElementById('addTeacherForm').style.display = 'none';
    document.getElementById('teachersList').style.display = 'block';
}
function searchTeachers() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    // Kiểm tra nếu không có nội dung nhập vào
    if (!searchTerm) {
        return;
    }

    fetch(`/Teachers/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.querySelector("#teachersTable tbody");
            tableBody.innerHTML = ''; // Xóa dữ liệu cũ trong bảng

            if (data.length > 0) {
                data.forEach(teacher => {
                    const row = document.createElement('tr');
                    row.id = `row-${teacher.id}`;
                    row.innerHTML = `
                        <td>${teacher.id}</td>
                        <td>${teacher.firstName}</td>
                        <td>${teacher.lastName}</td>
                        <td>${teacher.email}</td>
                        <td>${teacher.phoneNumber}</td>
                        <td>${teacher.department?.departmentName || 'N/A'}</td>
                        <td class="action-buttons">
                            <a href="/Teachers/update/${teacher.id}" class="update-btn">Update</a>
                            <a href="/deleteTeacher/${teacher.id}" class="btn btn-danger">Delete</a>
                        </td>
                    `;
                    tableBody.appendChild(row); // Thêm hàng mới vào bảng
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = `<td colspan="7">No results found</td>`; // Nếu không có kết quả
                tableBody.appendChild(row);
            }
        })
        .catch(error => {
            console.error("Error searching teachers:", error);
        });
}
