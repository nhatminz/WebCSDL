function showAddStudentForm() {
    document.getElementById('addStudentForm').style.display = 'block';
    document.getElementById('studentsList').style.display = 'none';
}

function backToTable() {
    document.getElementById('addStudentForm').style.display = 'none';
    document.getElementById('studentsList').style.display = 'block';
}

function searchStudents() {
    const searchTerm = document.getElementById('searchInput').value.trim();
    // if (!searchTerm) {
    //     return;
    // }
    fetch(`/Student/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#studentsTable tbody");
            tableBody.innerHTML = '';

            if (data.length > 0) {
                data.forEach(student => {
                    const row = document.createElement('tr');
                    row.id = `row-${student.id}`;
                    row.innerHTML = `
                        <td>${student.id}</td>
                        <td>${student.firstName} </td>
                        <td>${student.lastName}</td>
                        <td>${student.dateOfBirth}</td>
                        <td>${student.email}</td>
                        <td>${student.phoneNumber}</td>
                        <td>${student.address}</td>
                        <td>${student.className}</td>
                        <td>${student.majorName}</td>
                        <td>${student.gpa}</td>
                        <td class="action-buttons">
                    <a href="/Students/update/${student.id}" class="update-btn">Update</a>
                    <a href="/deleteStudent/${student.id}" class="btn btn-danger">Delete</a>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = `<td colspan="6">No results found</td>`;
                tableBody.appendChild(row);
            }
        })
        .catch(error => {
            console.error("Error searching students:", error);
        });
}
let sortDirectionGPA = 'asc';

function sortTableByGPA() {
    const table = document.getElementById("studentsTable");
    const rows = Array.from(table.querySelectorAll("tbody tr"));
    rows.sort((rowA, rowB) => {
        const gpaA = parseFloat(rowA.cells[9].textContent.trim()) || 0;
        const gpaB = parseFloat(rowB.cells[9].textContent.trim()) || 0;

        return sortDirectionGPA === 'asc' ? gpaA - gpaB : gpaB - gpaA;
    });
    sortDirectionGPA = sortDirectionGPA === 'asc' ? 'desc' : 'asc';
    const tableBody = table.querySelector("tbody");
    tableBody.innerHTML = "";
    rows.forEach(row => tableBody.appendChild(row));
}
