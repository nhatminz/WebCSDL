function showAddMajorForm() {
    document.getElementById('addMajorForm').style.display = 'block';
    document.getElementById('majorsList').style.display = 'none';
}

function backToTable() {
    document.getElementById('addMajorForm').style.display = 'none';
    document.getElementById('majorsList').style.display = 'block';
}
function searchMajors() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    fetch(`/Majors/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#majorsTable tbody");
            tableBody.innerHTML = ''; // Xóa nội dung cũ

            if (data.length > 0) {
                data.forEach(major => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${major.id}</td>
                        <td>${major.majorName}</td>
                        <td>${major.description}</td>
                        <td>${major.departmentName || 'N/A'}</td>
                        <td>
                            <a href="/Majors/update/${major.id}" class="btn btn-primary">Update</a>
                            <a href="/deleteMajor/${major.id}" class="btn btn-danger">Delete</a>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = `<td colspan="5">No results found</td>`;
                tableBody.appendChild(row);
            }
        })
        .catch(error => {
            console.error("Error searching majors:", error);
        });
}

