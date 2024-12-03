function showAddClassForm() {
    document.getElementById('addClassForm').style.display = 'flex';
    document.getElementById('classesList').style.display = 'none';
}
function searchClasses() {
    const searchTerm = document.getElementById('searchInput').value.trim();
    fetch(`/SchoolClass/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#classesTable tbody");
            tableBody.innerHTML = '';

            if (data.length > 0) {
                data.forEach(schoolClass => {
                    const row = document.createElement('tr');
                    row.id = `row-${schoolClass.id}`;
                    row.innerHTML = `
                            <td>${schoolClass.id}</td>
                            <td>${schoolClass.className}</td>
                            <td>${schoolClass.classDescription}</td>
                            <td class="action-buttons">
                            <a href="/SchoolClass/update/${schoolClass.id}" class="update-btn">Update</a>
                            <a href="/deleteSchoolClass/${schoolClass.id}" class="btn btn-danger">Delete</a>
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
            console.error("Error searching classes:", error);
        });
}
function backToTable() {
    document.getElementById('addClassForm').style.display = 'none';
    document.getElementById('classesList').style.display = 'block';
}