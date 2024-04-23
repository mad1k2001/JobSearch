<!DOCTYPE html>
<html>
<head>
    <title>Create Resume</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .input-group-text {
            background-color: #f8f9fa;
            border-color: #ced4da;
        }
    </style>
</head>
<body>
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
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h2 class="text-center mt-5">Create Resume</h2>
    <form method="post" action="/resume">
        <div class="form-group">
            <label for="name">Name:</label>
            <input placeholder="Enter the name" type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select class="form-control" id="categoryId" name="categoryId" required onchange="updateCategoryId(this)">
                <option value="" disabled selected>Select a category</option>
                <#list categories as category>
                    <option value="${category.id}">${category.name}</option>
                </#list>
            </select>
            <input type="hidden" id="categoryId" name="categoryId">
        </div>
        <div class="form-group">
            <label for="salary">Expected salary level:</label>
            <input placeholder="Enter the salary" type="number" class="form-control" id="salary" name="salary" required>
        </div>

        <h4 class="m-4">About me:</h4>
        <div class="card mb-4" id="workExp">
            <h5 class="card-title text-center">Work Experience</h5>
            <div id="work" class="d-flex justify-content-center">
                <button id="add-work-btn" class="btn btn-secondary my-3" type="button">Add work experience</button>
            </div>
        </div>

        <div class="card mb-4" id="education">
            <h5 class="card-title text-center">Education</h5>
            <div id="educationInfo" class="d-flex justify-content-center">
                <button id="add-education-btn" class="btn btn-secondary my-3" type="button">Add education info</button>
            </div>
        </div>

        <div class="card mb-4" id="addContacts">
            <div class="card-body">
                <h5 class="card-title">Contacts info</h5>
                <div class="mb-3">
                    <label for="instagram" class="form-label">Contact type: </label>
                    <input type="text" value="Instagram" class="form-control" id="instagram" name="contacts[0].type">
                </div>
                <div class="mb-3">
                    <label for="instagramValue" class="form-label">Contact Value</label>
                    <input type="text" value="Instagram" class="form-control" id="instagramValue" name="contacts[0].infoValue">
                </div>
            </div>
            <div id="contacts" class="d-flex justify-content-center">
                <button id="add-contacts-btn" class="btn btn-secondary my-3" type="button">add more</button>
            </div>
        </div>


        <div class="form-group">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="isActive" name="isActive" value="true">
                <label class="custom-control-label" for="isActive">Active</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block mb-3">Submit</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="/js/createResume.js"></script>
</body>
</html>
