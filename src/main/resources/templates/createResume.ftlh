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
    <form method="post" action="/create-resume">
        <div class="form-group">
            <label for="name">Name:</label>
            <input placeholder="Enter the name" type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="category">Category:</label>
            <select class="form-control" id="category" name="category" required>
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
        <!-- Experience modules -->
        <div class="border p-3">
            <div class="form-group">
                <div class="row">
                    <div class="col-md-3">
                        <label for="experience1_years">Years:</label>
                    </div>
                    <div class="col-md-9">
                        <input type="number" class="form-control" id="experience1_years" name="experience1_years">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-3">
                        <label for="experience1_company">Company:</label>
                    </div>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="experience1_company" name="experience1_company">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-3">
                        <label for="experience1_position">Position:</label>
                    </div>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="experience1_position" name="experience1_position">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-3">
                        <label for="experience1_responsibilities">Responsibilities:</label>
                    </div>
                    <div class="col-md-9">
                        <textarea class="form-control" id="experience1_responsibilities" name="experience1_responsibilities"></textarea>
                    </div>
                </div>
            </div>
        </div>

        <!-- Education modules -->
        <div class="border p-3 mb-3 mt-3">
            <div class="education-modules">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label for="education1_institution">Institution:</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="education1_institution" name="education1_institution">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label for="education1_degree">Degree:</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="education1_degree" name="education1_degree">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label for="education1_startDate">Start Date:</label>
                        </div>
                        <div class="col-md-9">
                            <input type="date" class="form-control" id="education1_startDate" name="education1_startDate">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3">
                            <label for="education1_endDate">End Date:</label>
                        </div>
                        <div class="col-md-9">
                            <input type="date" class="form-control" id="education1_endDate" name="education1_endDate">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Contact types -->
        <h4 class="mt-5 mb-3">Links to social networks:</h4>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="telegram">Telegram:</label>
                    <div class="input-group">
                        <input type="telegram" class="form-control" id="telegram" name="telegram">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-telegram"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="email" name="email">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <div class="input-group">
                        <input type="tel" class="form-control" id="phone" name="phone">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-phone"></i></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="facebook">Facebook:</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="facebook" name="facebook">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-facebook"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="linkedin">LinkedIn:</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="linkedin" name="linkedin">
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-linkedin"></i></span>
                        </div>
                    </div>
                </div>
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
</body>
</html>
