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

INSERT INTO resumes (applicantId, name, categoryId, salary, isActive, createdDate, updateTime) VALUES
(1, 'Java Developer', 1, 3000.0, TRUE, NOW(), NOW()),
(1, 'Web Designer', 2, 2500.0, TRUE, NOW(), NOW());

INSERT INTO vacancies (name, description, categoryId, salary, expFrom, expTo, isActivate, authorId, createdDate, updateTime) VALUES
('Java Developer', 'Developing Java applications', 1, 3000.0, 2, 5, TRUE, 2, NOW(), NOW()),
('Web Designer', 'Creating user-friendly web interfaces', 2, 2500.0, 1, 3, TRUE, 2, NOW(), NOW());

INSERT INTO respondedApplications (resumeId, vacancyId, confirmation) VALUES
(1, 2, TRUE);

INSERT INTO contactTypes (type) VALUES
('Email'),
('Phone');

INSERT INTO contactInfos (typeId, resumeId, contactValue) VALUES
(1, 1, 'john@example.com'),
(2, 1, '123456789'),
(1, 2, 'jane@example.com'),
(2, 2, '987654321');

INSERT INTO educationInfos (resumeId, institution, program, startDate, endDate, degree) VALUES
(1, 'University of Java', 'Computer Science', '2010-09-01', '2014-06-30', 'Bachelor'),
(2, 'Web Design Institute', 'Web Design', '2005-09-01', '2008-06-30', 'Diploma');

INSERT INTO messages (respondedAppId, content, timestamp) VALUES
(1, 'Thank you for your application', NOW());
