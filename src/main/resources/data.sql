CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255),
    avatar VARCHAR(255),
    accountType VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parentId INT
);

CREATE TABLE IF NOT EXISTS contactInfos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    typeId INT,
    resumeId INT,
    value VARCHAR(255),
    FOREIGN KEY (typeId) REFERENCES contactTypes(id),
    FOREIGN KEY (resumeId) REFERENCES resumes(id)
);

CREATE TABLE IF NOT EXISTS contactTypes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS educationInfos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resumeId INT,
    institution VARCHAR(255),
    program VARCHAR(255),
    startDate DATE,
    endDate DATE,
    degree VARCHAR(255),
    FOREIGN KEY (resumeId) REFERENCES resumes(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    respondedAppId INTEGER,
    content TEXT,
    timestamp DATE,
    FOREIGN KEY (respondedAppId) REFERENCES respondedApplications(id)
);

CREATE TABLE IF NOT EXISTS respondedApplications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resumeId INTEGER,
    vacancyId INTEGER,
    confirmation BOOLEAN,
    FOREIGN KEY (resumeId) REFERENCES resumes(id),
    FOREIGN KEY (vacancyId) REFERENCES vacancies(id)
);

CREATE TABLE IF NOT EXISTS resumes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    applicantId INTEGER,
    name VARCHAR(255) NOT NULL,
    categoryId INTEGER,
    salary DOUBLE,
    isActive BOOLEAN,
    createdDate DATE,
    updateTime DATE,
    FOREIGN KEY (applicantId) REFERENCES users(id),
    FOREIGN KEY (categoryId) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS workExperienceInfos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resumeId INTEGER,
    years INTEGER,
    companyName VARCHAR(255),
    position VARCHAR(255),
    responsibility TEXT,
    FOREIGN KEY (resumeId) REFERENCES resumes(id)
);

CREATE TABLE IF NOT EXISTS vacancies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    categoryId INTEGER,
    salary DOUBLE,
    expFrom INTEGER,
    expTo INTEGER,
    isActivate BOOLEAN,
    authorId INTEGER,
    createdDate DATE,
    updateTime DATE,
    FOREIGN KEY (categoryId) REFERENCES categories(id),
    FOREIGN KEY (authorId) REFERENCES users(id)
);

INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES
('John', 'Doe', 30, 'john.doe@example.com', 'password', '123456789', '', 'applicant'),
('Jane', 'Smith', 25, 'jane.smith@example.com', 'password', '987654321', '', 'employer');

INSERT INTO categories (name, parentId) VALUES
('IT', null),
('Web Development', 1);

INSERT INTO contactTypes (type) VALUES
('Phone'),
('Email');

INSERT INTO contactInfos (typeId, resumeId, value) VALUES
(1, 1, '123456789'),
(2, 1, 'john.doe@example.com'),
(1, 2, '987654321'),
(2, 2, 'jane.smith@example.com');

INSERT INTO educationInfos (resumeId, institution, program, startDate, endDate, degree) VALUES
(1, 'University of Example', 'Computer Science', '2015-09-01', '2019-06-30', 'Bachelor'),
(2, 'College of Example', 'Web Development', '2017-09-01', '2019-06-30', 'Diploma');

INSERT INTO messages (respondedAppId, content, timestamp) VALUES
(1, 'Hello, we are interested in your resume.', '2022-03-05');

INSERT INTO respondedApplications (resumeId, vacancyId, confirmation) VALUES
(1, 1, true);

INSERT INTO resumes (applicantId, name, categoryId, salary, isActive, createdDate, updateTime) VALUES
(1, 'John Doe Resume', 1, 3000.0, true, '2022-03-05', '2022-05-05'),
(2, 'Jane Smith Resume', 2, 2500.0, true, '2023-11-01', '2023-11-01');

INSERT INTO workExperienceInfos (resumeId, years, companyName, position, responsibility) VALUES
(1, 5, 'Tech Company', 'Software Engineer', 'Developed web applications.'),
(2, 3, 'Design Agency', 'Web Designer', 'Designed user interfaces.');

INSERT INTO vacancies (name, description, categoryId, salary, expFrom, expTo, isActivate, authorId, createdDate, updateTime) VALUES
('Java Developer', 'Developing Java applications', 1, 3000.0, 2, 5, true, 2, '2022-03-05', '2022-05-05'),
('Web Designer', 'Creating user-friendly web interfaces', 2, 2500.0, 1, 3, true, 2, '2023-11-01', '2023-11-01');
