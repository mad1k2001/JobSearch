<!DOCTYPE html>
<html>
<head>
    <title>Edit Resume</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="text-center mt-5">Edit Resume</h2>
    <form method="post" action="/resume/edit/${resume.id}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="${resume.name}" required>
        </div>
        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select class="form-control" id="categoryId" name="categoryId" required>
                <option value="" disabled selected>Select a category</option>
                <#list categories as category>
                    <option value="${category.id}" <#if resume.categoryId == resume.id>selected</#if>>${category.name}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <label for="salary">Expected salary level:</label>
            <input type="number" class="form-control" id="salary" name="salary" value="${resume.salary}" required>
        </div>

        <!-- Add fields for work experience here -->
        <h4 class="mt-5">Work Experience:</h4>
        <#list resume.workExperienceList as workExperience>
            <div class="border p-3 mb-3">
                <div class="form-group">
                    <label for="workExperience${workExperience.id}_years">Years:</label>
                    <input type="number" class="form-control" id="workExperience${workExperience.id}_years" name="workExperienceList[${workExperience_index}].years" value="${workExperience.years}">
                </div>
                <div class="form-group">
                    <label for="workExperience${workExperience.id}_company">Company:</label>
                    <input type="text" class="form-control" id="workExperience${workExperience.id}_company" name="workExperienceList[${workExperience_index}].company" value="${workExperience.company}">
                </div>
                <div class="form-group">
                    <label for="workExperience${workExperience.id}_position">Position:</label>
                    <input type="text" class="form-control" id="workExperience${workExperience.id}_position" name="workExperienceList[${workExperience_index}].position" value="${workExperience.position}">
                </div>
                <div class="form-group">
                    <label for="workExperience${workExperience.id}_responsibilities">Responsibilities:</label>
                    <textarea class="form-control" id="workExperience${workExperience.id}_responsibilities" name="workExperienceList[${workExperience_index}].responsibilities">${workExperience.responsibilities}</textarea>
                </div>
            </div>
        </#list>

        <!-- Add fields for education here -->
        <h4 class="mt-5">Education:</h4>
        <#list resume.educationList as education>
            <div class="border p-3 mb-3">
                <div class="form-group">
                    <label for="education${education.id}_institution">Institution:</label>
                    <input type="text" class="form-control" id="education${education.id}_institution" name="educationList[${education_index}].institution" value="${education.institution}">
                </div>
                <div class="form-group">
                    <label for="education${education.id}_degree">Degree:</label>
                    <input type="text" class="form-control" id="education${education.id}_degree" name="educationList[${education_index}].degree" value="${education.degree}">
                </div>
                <div class="form-group">
                    <label for="education${education.id}_startDate">Start Date:</label>
                    <input type="date" class="form-control" id="education${education.id}_startDate" name="educationList[${education_index}].startDate">
                </div>
                <div class="form-group">
                    <label for="education${education.id}_endDate">End Date:</label>
                    <input type="date" class="form-control" id="education${education.id}_endDate" name="educationList[${education_index}].endDate">
                </div>
            </div>
        </#list>

        <!-- Add fields for contact info here -->
        <h4 class="mt-5">Contact Info:</h4>
        <#list resume.contactInfo as contactInfo>
            <div class="border p-3 mb-3">
                <div class="form-group">
                    <label for="contact${contactInfo.id}_type">Contact Type:</label>
                    <input type="text" class="form-control" id="contact${contactInfo.id}_type" name="contactInfo[${contactInfo_index}].type" value="${contactInfo.type}">
                </div>
                <div class="form-group">
                    <label for="contact${contactInfo.id}_value">Contact Value:</label>
                    <input type="text" class="form-control" id="contact${contactInfo.id}_value" name="contactInfo[${contactInfo_index}].value" value="${contactInfo.value}">
                </div>
            </div>
        </#list>

        <div class="form-group">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="isActive" name="isActive" value="true" <#if resume.active>checked</#if>>
                <label class="custom-control-label" for="isActive">Active</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</body>
</html>
