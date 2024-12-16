function showAddDepartmentForm() {
    document.getElementById('addDepartmentForm').style.display = 'block';
    document.getElementById('departmentList').style.display = 'none';
}
function searchDepartments() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    fetch(`/Department/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#departmentTable tbody");
            tableBody.innerHTML = '';

            if (data.length > 0) {
                data.forEach(department => {
                    const row = document.createElement('tr');
                    row.id = `row-${department.id}`;
                    row.innerHTML = `
                        <td>${department.id}</td>
                        <td>${department.departmentName}</td>
                        <td>${department.location}</td>
                        <td class="action-buttons">
                            <a href="/Department/update/${department.id}" class="btn btn-primary">Update</a>
                            <a href="/deleteDepartment/${department.id}" class="btn btn-danger">Delete</a>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = `<td colspan="4">No results found</td>`;
                tableBody.appendChild(row);
            }
        })
        .catch(error => {
            console.error("Error searching departments:", error);
        });
}
function backToTable() {
    document.getElementById('addDepartmentForm').style.display = 'none';
    document.getElementById('departmentList').style.display = 'block';
}

function exportToPDF() {
    fetch('/departments/export', { method: 'GET' })
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
            a.download = 'department.pdf'; // Tên file PDF khi tải về
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error exporting PDF:', error);
        });
}