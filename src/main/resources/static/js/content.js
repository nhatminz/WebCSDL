function toggleDropdown() {
    const dropdown = document.getElementById('dropdown-menu');
    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}

function logout() {
    fetch('/logout', { method: 'POST' })
        .then(response => {
            if (response.ok) {
                window.location.href = 'req/login';
            } else {
                console.error('Đăng xuất không thành công');
            }
        })
        .catch(error => {
            console.error('Lỗi khi đăng xuất:', error);
        });
}

document.addEventListener("DOMContentLoaded", function () {
    fetch("/current-user")
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                console.error("Failed to fetch username");
                return "Guest";
            }
        })
        .then(username => {
            document.getElementById("username").textContent = username;
        })
        .catch(error => {
            console.error("Error fetching username:", error);
        });
});

function loadRandomAvatar() {
    fetch('https://randomuser.me/api/')
        .then(response => response.json())
        .then(data => {
            const avatarImage = data.results[0].picture.large;
            document.getElementById('avatar').src = avatarImage;
        })
        .catch(error => {
            console.error('Error loading avatar:', error);
        });
}

// Call the loadRandomAvatar function when the page loads
window.onload = loadRandomAvatar;