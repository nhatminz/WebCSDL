function showAddCourseScheduleForm() {
    document.getElementById('addCourseScheduleForm').style.display = 'block';
    document.getElementById('courseSchedulesList').style.display = 'none';
}

function searchCourseSchedules() {
    const searchTerm = document.getElementById('searchInput').value.trim();

    fetch(`/CourseSchedule/search?query=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#courseSchedulesTable tbody");
            tableBody.innerHTML = ''; // Xóa nội dung cũ

            if (data.length > 0) {
                data.forEach(schedule => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${schedule.id}</td>
                        <td>${schedule.courseName}</td>
                        <td>${schedule.classroomId}</td>
                        <td>${schedule.dayOfWeek}</td>
                        <td>${schedule.startTime}</td>
                        <td>${schedule.endTime}</td>
                        <td class="action-buttons">
                            <a href="/CourseSchedules/update/${schedule.id}" class="update-btn">Update</a>
                            <a href="/deleteCourseSchedule/${schedule.id}" class="btn btn-danger">Delete</a>
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
            console.error("Error searching course schedules:", error);
        });
}

let sortDirection = 'asc';
function sortTableByClassroom() {
    const table = document.getElementById('courseSchedulesTable');
    const rows = Array.from(table.querySelectorAll('tbody tr'));

    rows.sort((rowA, rowB) => {
        const classroomA = parseInt(rowA.cells[2].textContent.trim());
        const classroomB = parseInt(rowB.cells[2].textContent.trim());

        return sortDirection === 'asc' ? classroomA - classroomB : classroomB - classroomA;
    });

    sortDirection = sortDirection === 'asc' ? 'desc' : 'asc';

    const tableBody = table.querySelector('tbody');
    tableBody.innerHTML = '';
    rows.forEach(row => tableBody.appendChild(row));
}
function backToTable() {
    document.getElementById('addCourseScheduleForm').style.display = 'none';
    document.getElementById('courseSchedulesList').style.display = 'block';
}
