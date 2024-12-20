function showAddScholarshipForm() {
    document.getElementById('addScholarshipForm').style.display = 'block';
    document.getElementById('scholarshipsList').style.display = 'none';
}

function backToScholarshipsList() {
    document.getElementById('addScholarshipForm').style.display = 'none';
    document.getElementById('scholarshipsList').style.display = 'block';
}

function searchScholarships() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    fetch(`/Scholarships/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#scholarshipsTable tbody");
            tableBody.innerHTML = ''; // Xóa nội dung cũ

            if (data.length > 0) {
                data.forEach(scholarship => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${scholarship.id}</td>
                        <td>${scholarship.scholarshipName}</td>
                        <td>${scholarship.amount}</td>
                        <td>${scholarship.studentName || 'N/A'}</td>
                        <td>
                            <a href="/Scholarships/update/${scholarship.id}" class="btn btn-primary">Update</a>
                            <a href="/deleteScholarship/${scholarship.id}" class="btn btn-danger">Delete</a>
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
            console.error("Error searching scholarships:", error);
        });
}

let sortDirection = 'asc';
function sortTable() {
    const table = document.getElementById("scholarshipsTable");
    const rows = Array.from(table.querySelectorAll("tbody tr"));
    rows.sort((rowA, rowB) => {
        const amountA = parseFloat(rowA.cells[2].textContent.trim()) || 0;
        const amountB = parseFloat(rowB.cells[2].textContent.trim()) || 0;

        return sortDirection === 'asc' ? amountA - amountB : amountB - amountA;
    });
    sortDirection = sortDirection === 'asc' ? 'desc' : 'asc';
    const tableBody = table.querySelector("tbody");
    tableBody.innerHTML = "";
    rows.forEach(row => tableBody.appendChild(row));
}

function exportToPDF() {
    fetch('/exportScholarships', { method: 'GET' })
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
            a.download = 'scholarships.pdf'; // Tên file PDF khi tải về
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error exporting PDF:', error);
        });
}

