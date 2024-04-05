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
<!-- Bootstrap Navigation Bar -->
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
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h2 class="h2 text-center p-4">User profile
        <#if user.accountType == "APPLICANT">
            Applicant
        <#elseif user.accountType == "EMPLOYER">
            Employer
        </#if>
    </h2>
    <div class="row gx-4">
        <div class="user-info col-6 bg-secondary bg-opacity-25 rounded row p-4">
            <div class="avatar border rounded-circle col-6">
                <img src="${user.avatar}" alt="avatar">
            </div>
            <div class="bg-gray bg-opacity-25 col-6">
                <h4>${user.name} ${user.surName}</h4>
                <p>Age: ${user.age}</p>
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#editUserModal">
                    Edit User
                </button>
            </div>
        </div>
        <div class="col-1"></div>
        <div class="more row col-5 gx-2">
            <div class="create col-5 bg-danger border bg-opacity-25 rounded h-50 pt-2">
                <h4 class="text-center">+</h4>
                <h6 class="text-center p-auto">Create <#if user.accountType == "APPLICANT">resume<#else>vacancy</#if></h6>
            </div>
            <div class="col-1"></div>
            <div class="responds col-5 bg-secondary bg-opacity-25 rounded h-50 d-flex justify-content-center align-items-center">
                <h6 class="text-center p-auto">Responses</h6>
            </div>
        </div>
    </div>
    <div class="extra-info row">
        <#if user.accountType == "APPLICANT">
            <h3 class="h3 my-3 text-center">Resumes:</h3>
            <#if resumes??>
                <#if resumes?size != 0>
                    <#list resumes as resume>
                        <div class="col-5">
                            <a href="/resumes/${resume.id}" class="text-decoration-none text-dark">
                                <div class="card bg-secondary bg-opacity-25 mb-3">
                                    <div class="card-header">
                                        <h5 class="card-title">${resume.name}</h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text"><#if resume.updatedDate??>
                                        <h6>Updated Date: ${resume.updatedDate}</h6>
                                        <#else><h6> Created Date: ${resume.createdDate}</h6>
                                        </#if>
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <a href="#" class="btn btn-success bg-opacity-25">Update</a>
                                        <a href="#" class="btn btn-warning bg-opacity-25">Edit</a>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-2"></div>
                    </#list>
                <#else>
                    <div class="alert alert-info" role="alert">
                        You have no resumes yet
                    </div>
                </#if>
            <#else>
            </#if>
        <#else>
            <h3 class="h3 my-3 text-center">Vacancies:</h3>
            <#if vacancies??>
                <#if vacancies?size != 0>
                    <#list vacancies as vacancy>
                        <div class="col-5 ">
                            <a href="/vacancies/${vacancy.id}" class="text-decoration-none text-dark">
                                <div class="card bg-secondary bg-opacity-25 mb-3">
                                    <div class="card-header">
                                        <h5 class="card-title">${vacancy.name}</h5>
                                    </div>
                                    <div class="card-body h-100">
                                        <p class="card-text">
                                            <#if vacancy.updatedTime??>
                                        <h6>Updated Date: ${vacancy.updatedTime}</h6>
                                        <#else>
                                            <h6>Created Date: ${vacancy.createdDate}</h6>
                                        </#if>
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <a href="#" class="btn btn-success bg-opacity-25">Update</a>
                                        <a href="#" class="btn btn-warning bg-opacity-25">Edit</a>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-2"></div>
                    </#list>
                <#else>
                    <div class="alert alert-info" role="alert">
                        You have no vacancies yet
                    </div>
                </#if>
            <#else>
            </#if>
        </#if>
    </div>
</div>
<div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editUserModalLabel">Edit User</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/profile" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name:</label>
                        <input type="text" id="name" name="name" value="${user.name}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="surName" class="form-label">Surname:</label>
                        <input type="text" id="surName" name="surName" value="${user.surName}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="age" class="form-label">Age:</label>
                        <input type="text" id="age" name="age" value="${user.age}" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="avatar" class="form-label">Avatar:</label>
                        <input type="file" id="avatar" name="avatar" accept="image/*" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Save Changes</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script></body>
</html>
