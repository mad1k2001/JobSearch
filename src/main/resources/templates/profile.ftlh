<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous"></head>
<body>
<!-- Навигационная панель Bootstrap -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">Job Search</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Vacancies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1 class="mt-5">User Profile</h1>
    <div class="row">
        <div class="col-md-3">
            <img src="${user.avatar}" alt="User Avatar" class="img-fluid rounded-circle mb-3">
        </div>
        <div class="col-md-9">
            <p><strong>Name:</strong> ${user.name} ${user.surName}</p>
            <p><strong>Age:</strong> ${user.age}</p>
        </div>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editUserModal">
            Edit User
        </button>
    </div>

    <div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editUserModalLabel">Edit User</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/profile" method="post">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" value="${user.name}" required>
                        <label for="surName">Surname:</label>
                        <input type="text" id="surName" name="surName" value="${user.surName}" required>
                        <label for="surName">Age:</label>
                        <input type="text" id="surName" name="surName" value="${user.age}" required>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <h2 class="mt-5">Resumes</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Resume Name</th>
            <th>Last Updated</th>
        </tr>
        </thead>
        <tbody>
        <#list resumes as resume>
            <tr>
                <td>${resume.name}</td>
                <td>${resume.updateTime}</td>
                <button type="button" class="btn btn-primary">Update</button>
                <button type="button" class="btn btn-secondary">Edit</button>
            </tr>
        </#list>
        </tbody>
    </table>

    <h2 class="mt-5">Vacancies</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Vacancy Name</th>
            <th>Last Updated</th>
        </tr>
        </thead>
        <tbody>
        <#list vacancies as vacancy>
            <tr>
                <td>${vacancy.name}</td>
                <td>${vacancy.updateTime}</td>
                <button type="button" class="btn btn-primary">Update</button>
                <button type="button" class="btn btn-secondary">Edit</button>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<!-- Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script></body>
</html>
