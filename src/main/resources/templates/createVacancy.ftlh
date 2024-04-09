<!DOCTYPE html>
<html>
<head>
    <title>Create Vacancy</title>
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
    <h2 class="text-center mt-5">Create Vacancy</h2>
    <form method="post" action="/create-vacancy">
        <div class="form-group">
            <label for="name">Name:</label>
            <input placeholder="Enter the name" type="text" class="form-control" id="name" name="name" required minlength="2" maxlength="50">
        </div>
        <div class="form-group">
            <label for="category">Category:</label>
            <select class="form-control" id="category" name="category" required onchange="updateCategoryId(this)">
                <option value="" disabled selected>Select a category</option>
                <#list categories as category>
                    <option value="${category.id}">${category.name}</option>
                </#list>
            </select>
            <input type="hidden" id="categoryId" name="categoryId">
        </div>

        <div class="form-group">
            <label for="salary">Salary:</label>
            <input placeholder="Enter the salary" type="number" class="form-control" id="salary" name="salary" required min="0.0" step="0.01">
        </div>
        <div class="form-group">
            <label for="description">Vacancy description:</label>
            <textarea class="form-control" id="description" name="description" required minlength="2" maxlength="50" rows="5"></textarea>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="expFrom" class="mr-2">From:</label>
                <input type="number" class="form-control" id="expFrom" name="expFrom" required min="0">
            </div>
            <div class="form-group col-md-6">
                <label for="expTo" class="mr-2">To:</label>
                <input type="number" class="form-control" id="expTo" name="expTo" required min="0" max="60">
            </div>
        </div>

        <div class="form-group">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="isActive" name="isActive" value="true" onchange="updateIsActivate()">
                <label class="custom-control-label" for="isActive">Active</label>
            </div>
        </div>
        <input type="hidden" id="isActivateHidden" name="isActivate">

        <button type="submit" class="btn btn-primary btn-block mb-3">Submit</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
