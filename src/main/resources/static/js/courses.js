function goBack() {
    document.getElementById('addCourseForm').style.display = 'none';
    document.getElementById('coursesList').style.display = 'block';
}

function showAddCourseForm() {
    document.getElementById('addCourseForm').style.display = 'flex';
    document.getElementById('coursesList').style.display = 'none';
}

function searchCourses() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    fetch(`/Courses/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#coursesTable tbody");
            tableBody.innerHTML = '';

            if (data.length > 0) {
                data.forEach(course => {
                    const row = document.createElement('tr');
                    row.id = `row-${course.id}`;
                    row.innerHTML = `
                    <td>${course.id}</td>
                    <td>${course.courseName}</td>
                    <td>${course.courseCode}</td>
                    <td>${course.credits}</td>
                    <td>${course.majorName}</td>
                    <td>${course.teacherName}</td>
                    <td class="action-buttons">
                        <a href="/Courses/update/${course.id}" class="update-btn">Update</a>
                    <a href="/deleteCourse/${course.id}" class="btn btn-danger">Delete</a>
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
            console.error("Error searching courses:", error);
        });
}
let sortDirectionCredits = 'asc';

function sortTableByCredits() {
    const table = document.getElementById("coursesTable");
    const rows = Array.from(table.querySelectorAll("tbody tr"));
    rows.sort((rowA, rowB) => {
        const creditsA = parseInt(rowA.cells[3].textContent.trim()) || 0;
        const creditsB = parseInt(rowB.cells[3].textContent.trim()) || 0;

        return sortDirectionCredits === 'asc' ? creditsA - creditsB : creditsB - creditsA;
    });
    sortDirectionCredits = sortDirectionCredits === 'asc' ? 'desc' : 'asc';
    const tableBody = table.querySelector("tbody");
    tableBody.innerHTML = "";
    rows.forEach(row => tableBody.appendChild(row));
}
