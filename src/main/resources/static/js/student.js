document.querySelectorAll('.delete-btn').forEach(button => {
    button.addEventListener('click', () => {
        alert('Student deleted!');
    });
});

document.querySelectorAll('.update-btn').forEach(button => {
    button.addEventListener('click', () => {
        alert('Update student details!');
    });
});
