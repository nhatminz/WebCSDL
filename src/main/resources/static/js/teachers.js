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

    fetch(`/Teachers/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#teachersTable tbody");
            tableBody.innerHTML = ''; // Xóa nội dung cũ

            if (data.length > 0) {
                data.forEach(teacher => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${teacher.id}</td>
                        <td>${teacher.firstName}</td>
                        <td>${teacher.lastName}</td>
                        <td>${teacher.email || 'N/A'}</td>
                        <td>${teacher.phoneNumber || 'N/A'}</td>
                        <td>${teacher.departmentName || 'N/A'}</td>
                        <td>
                            <a href="/Teachers/update/${teacher.id}" class="btn btn-primary">Update</a>
                            <a href="/deleteTeacher/${teacher.id}" class="btn btn-danger">Delete</a>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = `<td colspan="7">No results found</td>`;
                tableBody.appendChild(row);
            }
        })
        .catch(error => {
            console.error("Error searching teachers:", error);
        });
}
function exportToPDF() {
    fetch('/exportTeachers', { method: 'GET' })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to generate PDF');
            }
            return response.blob();
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'teachers.pdf'; // Tên file PDF khi tải về
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error exporting PDF:', error);
        });
}

