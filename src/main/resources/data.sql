CREATE TABLE IF NOT EXISTS users (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255),
    avatar VARCHAR(255),
    accountType VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS categories (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentId LONG,
    FOREIGN KEY (parentId) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS resumes (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    applicantId LONG NOT NULL,
    name VARCHAR(255) NOT NULL,
    categoryId LONG NOT NULL,
    salary DOUBLE NOT NULL,
    isActive BOOLEAN,
    createdDate TIMESTAMP,
    updateTime TIMESTAMP,
    FOREIGN KEY (applicantId) REFERENCES users(id),
    FOREIGN KEY (categoryId) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS vacancies (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    categoryId LONG NOT NULL,
    salary DOUBLE NOT NULL,
    expFrom INT NOT NULL,
    expTo INT NOT NULL,
    isActivate BOOLEAN,
    authorId LONG NOT NULL,
    createdDate TIMESTAMP,
    updateTime TIMESTAMP,
    FOREIGN KEY (authorId) REFERENCES users(id),
    FOREIGN KEY (categoryId) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS respondedApplications (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    resumeId LONG NOT NULL,
    vacancyId LONG NOT NULL,
    confirmation BOOLEAN,
    FOREIGN KEY (resumeId) REFERENCES resumes(id),
    FOREIGN KEY (vacancyId) REFERENCES vacancies(id)
);

CREATE TABLE IF NOT EXISTS contactTypes (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS contactInfos (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    typeId LONG NOT NULL,
    resumeId LONG NOT NULL,
    contactValue VARCHAR(255) NOT NULL,
    FOREIGN KEY (typeId) REFERENCES contactTypes(id),
    FOREIGN KEY (resumeId) REFERENCES resumes(id)
);

CREATE TABLE IF NOT EXISTS educationInfos (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    resumeId LONG NOT NULL,
    institution VARCHAR(255) NOT NULL,
    program VARCHAR(255) NOT NULL,
    startDate DATE,
    endDate DATE,
    degree VARCHAR(255) NOT NULL,
    FOREIGN KEY (resumeId) REFERENCES resumes(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    respondedAppId LONG NOT NULL,
    content VARCHAR(255) NOT NULL ,
    timestamp TIMESTAMP,
    FOREIGN KEY (respondedAppId) REFERENCES respondedApplications(id)
);

INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType) VALUES
    ('John', 'Doe', 30, 'john@example.com', 'password', '123456789', 'avatar1.jpg', 'applicant'),
    ('Jane', 'Smith', 35, 'jane@example.com', 'password', '987654321', 'avatar2.jpg', 'employer');

INSERT INTO categories (name, parentId) VALUES
    ('IT', NULL),
    ('Design', NULL);

INSERT INTO resumes (applicantId, name, categoryId, salary, isActive, createdDate, updateTime)
VALUES
    ((SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 'Backend Developer', (SELECT id FROM categories WHERE name = 'IT'), 3500.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 'Database Administrator', (SELECT id FROM categories WHERE name = 'IT'), 3200.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 'System Analyst', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 'UI/UX Designer', (SELECT id FROM categories WHERE name = 'Design'), 2800.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), 'Graphic Designer', (SELECT id FROM categories WHERE name = 'Design'), 2600.0, TRUE, NOW(), NOW());

INSERT INTO vacancies (name, description, categoryId, salary, expFrom, expTo, isActivate, authorId, createdDate, updateTime)
VALUES
    ('Frontend Developer', 'Developing user interfaces', (SELECT id FROM categories WHERE name = 'IT'), 3500.0, 2, 5, TRUE, (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW()),
    ('Mobile Developer', 'Developing mobile applications', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, 3, 6, TRUE, (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW()),
    ('Network Administrator', 'Managing network infrastructure', (SELECT id FROM categories WHERE name = 'IT'), 3200.0, 1, 4, TRUE, (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW()),
    ('Web Developer', 'Creating websites', (SELECT id FROM categories WHERE name = 'Design'), 3000.0, 2, 5, TRUE, (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW()),
    ('UI/UX Designer', 'Designing user interfaces and experiences', (SELECT id FROM categories WHERE name = 'Design'), 2800.0, 1, 3, TRUE, (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), NOW(), NOW());

INSERT INTO respondedApplications (resumeId, vacancyId, confirmation)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Backend Developer' AND applicantId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), (SELECT id FROM vacancies WHERE name = 'Frontend Developer' AND authorId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), (SELECT id FROM vacancies WHERE name = 'Mobile Developer' AND authorId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'System Analyst' AND applicantId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), (SELECT id FROM vacancies WHERE name = 'Network Administrator' AND authorId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), TRUE);

INSERT INTO contactTypes (type) VALUES
    ('Telegram'),
    ('Email'),
    ('Phone'),
    ('Facebook'),
    ('Linkedin');

INSERT INTO contactInfos (typeId, resumeId, contactValue)
VALUES
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'Java Developer'), 'john@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'Java Developer'), '123456789'),
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'Web Designer'), 'jane@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'Web Designer'), '987654321');

INSERT INTO educationInfos (resumeId, institution, program, startDate, endDate, degree)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Backend Developer'), 'University of Java', 'Computer Science', '2010-09-01', '2014-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator'), 'University of SQL', 'Database Management', '2011-09-01', '2015-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'System Analyst'), 'System Analysis Institute', 'Systems Engineering', '2009-09-01', '2013-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'UI/UX Designer'), 'Design University', 'UI/UX Design', '2007-09-01', '2011-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Graphic Designer'), 'Graphic Design School', 'Graphic Design', '2008-09-01', '2012-06-30', 'Bachelor');

INSERT INTO messages (respondedAppId, content, timestamp)
VALUES
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Backend Developer') AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Frontend Developer')), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Database Administrator') AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Mobile Developer')), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'System Analyst') AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Network Administrator')), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'UI/UX Designer') AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Web Developer')), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Graphic Designer') AND vacancyId = (SELECT id FROM vacancies WHERE name = 'UI/UX Designer')), 'Thank you for your application', NOW());
