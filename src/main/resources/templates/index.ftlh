<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>
    <!-- Подключение стилей Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>

<!-- Навигационная панель Bootstrap -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Job Search</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/register">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <#if user.accountType == "Applicant">
        <h1 class="text-center  mt-5 mb-4">Vacancies</h1>
        <#if vacancies??>
            <#if vacancies?size != 0>
                <div class="row row-cols-1 row-cols-md-2 g-4">
                    <#list vacancies as vacancy>
                        <div class="col">
                            <a href="/vacancies/${vacancy.id}" class="text-decoration-none text-dark">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <h5 class="card-title">${vacancy.name}</h5>
                                        <p class="card-text">${vacancy.description}</p>
                                        <p class="card-text">Salary: ${vacancy.salary}</p>
                                        <p class="card-text">Experience: ${vacancy.expFrom} - ${vacancy.expTo} years</p>
                                        <p class="card-text">Author: ${vacancy.authorId}</p>
                                        <p class="card-text">Created Date: ${vacancy.createdDate}</p>
                                        <p class="card-text">Update Time: ${vacancy.updateTime}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </#list>
                </div>
            <#else>
                <p class="text-center mt-5">No vacancy found.</p>
            </#if>
        </#if>
    <#else>
        <h1 class="text-center mt-5 mb-4">Resumes</h1>
        <#if resumes??>
            <#if resumes?size != 0>
                <div class="row row-cols-1 row-cols-md-2 g-4">
                    <#list resumes as resume>
                        <div class="col">
                            <a href="/resumes/${resume.id}" class="text-decoration-none text-dark">
                                <div class="card shadow">
                                    <div class="card-body">
                                        <h5 class="card-title">${resume.name}</h5>
                                        <p class="card-text">Category: ${resume.categoryId}</p>
                                        <p class="card-text">Salary: ${resume.salary}</p>
                                        <p class="card-text">Active: ${resume.isActive?string('Yes', 'No')}</p>
                                        <p class="card-text">Author: ${resume.applicantId}</p>
                                        <p class="card-text">Created Date: ${resume.createdDate}</p>
                                        <p class="card-text">Update Time: ${resume.updateTime}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </#list>
                </div>
            <#else>
                <p class="text-center mt-5">No resumes found.</p>
            </#if>
        </#if>
    </#if>
</div>

<!-- Подключение скриптов Bootstrap (необходимо для работы некоторых компонентов) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
</body>
</html>
