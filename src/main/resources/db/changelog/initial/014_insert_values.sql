INSERT INTO authorities (authority)
values('FULL'), ('READ_ONLY'), ('EDITOR');

INSERT INTO accountType (accountType, authority_id)
values ('ADMIN', (select id from authorities where authority = 'FULL')),
       ('GUEST', (select id from authorities where authority = 'READ_ONLY')),
       ('EMPLOYER', (select id from authorities where authority = 'EDITOR')),
       ('APPLICANT', (select id from authorities where authority = 'EDITOR'));

INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, enabled, accountType)
VALUES
    ('John', 'Doe', 30, 'john@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '123456789', 'avatar1.jpg', TRUE, (select id from accountType where accountType = 'EMPLOYER')),
    ('Jane', 'Smith', 35, 'jane@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '987654321', 'avatar2.jpg', TRUE, (select id from accountType where accountType = 'APPLICANT')),
    ('qqwe', 'qwe', 24, 'qwe@qwe', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '999999999','avatar3.jpg', TRUE, (select id from accountType where accountType = 'ADMIN')),
    ('ewqq', 'ewq', 24, 'ewq@ewq.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '999999991','avatar4.jpg', TRUE, (select id from accountType where accountType = 'ADMIN'));

INSERT INTO categories (name, parentId)
VALUES
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

INSERT INTO contactTypes (type)
VALUES
    ('Telegram'),
    ('Email'),
    ('Phone'),
    ('Facebook'),
    ('Linkedin');

INSERT INTO contactInfos (typeId, resumeId, contactValue)
VALUES
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'Backend Developer'), 'john@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'Backend Developer'), '123456789'),
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'UI/UX Designer'), 'jane@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'UI/UX Designer'), '987654321');

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
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'System Analyst') AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Network Administrator')), 'Thank you for your application', NOW());

INSERT INTO workExperienceInfo (resumeId, years, companyName, position, responsibility)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Backend Developer'), 3, 'ABC Company', 'Software Engineer', 'Developed and maintained software applications'),
    ((SELECT id FROM resumes WHERE name = 'UI/UX Designer'), 2, 'XYZ Corporation', 'Web Developer', 'Designed and implemented web applications'),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator'), 4, 'DEF Industries', 'Database Administrator', 'Managed and optimized databases');
