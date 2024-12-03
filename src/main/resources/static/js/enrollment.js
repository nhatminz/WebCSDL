function showAddEnrollmentForm() {
    document.getElementById('addEnrollmentForm').style.display = 'block';
    document.getElementById('enrollmentList').style.display = 'none';
}
function backToTable() {
    document.getElementById('addEnrollmentForm').style.display = 'none';
    document.getElementById('enrollmentList').style.display = 'block';
}

function searchEnrollments() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    fetch(`/Enrollment/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#enrollmentsTable tbody");
            tableBody.innerHTML = ''; // Xóa nội dung cũ

            if (data.length > 0) {
                data.forEach(enrollment => {
                    const row = document.createElement('tr');
                    row.id = `row-${enrollment.studentId}-${enrollment.courseId}`;
                    row.innerHTML = `
                        <td>${enrollment.studentId}</td>
                        <td>${enrollment.studentName}</td>
                        <td>${enrollment.courseId}</td>
                        <td>${enrollment.courseName}</td>
                        <td>${enrollment.enrollmentDate}</td>
                        <td>${enrollment.grade || 'N/A'}</td>
                        <td class="action-buttons">
                            <a href="/Enrollments/update/${enrollment.studentId}/${enrollment.courseId}" class="update-btn">Update</a>
                            <a href="/deleteEnrollment/${enrollment.studentId}/${enrollment.courseId}" class="btn btn-danger">Delete</a>
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
            console.error("Error searching enrollments:", error);
        });
}