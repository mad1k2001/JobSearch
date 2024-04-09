INSERT INTO accountType (accountType)
values ('EMPLOYER'),
       ('APPLICANT');

INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, enabled, accountType)
VALUES
    ('John', 'Doe', 30, 'john@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '123456789', 'avatar1.jpg', TRUE, (select id from accountType where accountType = 'EMPLOYER')),
    ('Jane', 'Smith', 35, 'jane@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '987654321', 'avatar2.jpg', TRUE, (select id from accountType where accountType = 'APPLICANT')),
    ('Jack', 'Black', 25, 'jack@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '111111111', 'avatar3.jpg', TRUE, (select id from accountType where accountType = 'APPLICANT')),
    ('Emily', 'White', 28, 'emily@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '222222222', 'avatar4.jpg', TRUE, (select id from accountType where accountType = 'APPLICANT')),
    ('Michael', 'Brown', 40, 'michael@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '333333333', 'avatar5.jpg', TRUE, (select id from accountType where accountType = 'EMPLOYER')),
    ('Emma', 'Green', 32, 'emma@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '444444444', 'avatar6.jpg', TRUE, (select id from accountType where accountType = 'EMPLOYER'));

INSERT INTO categories (name, parentId)
VALUES
    ('IT', NULL),
    ('Design', NULL),
    ('Marketing', NULL),
    ('Management', NULL);

INSERT INTO resumes (applicantId, name, categoryId, salary, isActive, createdDate, updateTime)
VALUES
    ((SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), 'Backend Developer', (SELECT id FROM categories WHERE name = 'IT'), 3500.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), 'Database Administrator', (SELECT id FROM categories WHERE name = 'IT'), 3200.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), 'System Analyst', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), 'UI/UX Designer', (SELECT id FROM categories WHERE name = 'Design'), 2800.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith'), 'Graphic Designer', (SELECT id FROM categories WHERE name = 'Design'), 2600.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black'), 'Software Developer', (SELECT id FROM categories WHERE name = 'IT'), 4000.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black'), 'Database Administrator', (SELECT id FROM categories WHERE name = 'IT'), 3700.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black'), 'System Architect', (SELECT id FROM categories WHERE name = 'IT'), 4200.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black'), 'UI/UX Designer', (SELECT id FROM categories WHERE name = 'Design'), 3200.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black'), 'Graphic Artist', (SELECT id FROM categories WHERE name = 'Design'), 3000.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Emily' AND surname = 'White'), 'Web Developer', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Emily' AND surname = 'White'), 'Software Tester', (SELECT id FROM categories WHERE name = 'IT'), 3500.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Emily' AND surname = 'White'), 'UX Designer', (SELECT id FROM categories WHERE name = 'Design'), 3000.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Emily' AND surname = 'White'), 'Marketing Specialist', (SELECT id FROM categories WHERE name = 'Marketing'), 3800.0, TRUE, NOW(), NOW()),
    ((SELECT id FROM users WHERE name = 'Emily' AND surname = 'White'), 'Content Writer', (SELECT id FROM categories WHERE name = 'Marketing'), 3200.0, TRUE, NOW(), NOW());

INSERT INTO vacancies (name, description, categoryId, salary, expFrom, expTo, isActivate, authorId, createdDate, updateTime)
VALUES
    ('Frontend Developer', 'Developing user interfaces', (SELECT id FROM categories WHERE name = 'IT'), 3500.0, 2, 5, TRUE, (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
    ('Mobile Developer', 'Developing mobile applications', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, 3, 6, TRUE, (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
    ('Network Administrator', 'Managing network infrastructure', (SELECT id FROM categories WHERE name = 'IT'), 3200.0, 1, 4, TRUE, (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
    ('Web Developer', 'Creating websites', (SELECT id FROM categories WHERE name = 'Design'), 3000.0, 2, 5, TRUE, (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
    ('UI/UX Designer', 'Designing user interfaces and experiences', (SELECT id FROM categories WHERE name = 'Design'), 2800.0, 1, 3, TRUE, (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe'), NOW(), NOW()),
    ('Full Stack Developer', 'Development of full stack web applications', (SELECT id FROM categories WHERE name = 'IT'), 4000.0, 3, 6, TRUE, (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown'), NOW(), NOW()),
    ('Network Engineer', 'Designing and implementing computer networks', (SELECT id FROM categories WHERE name = 'IT'), 4200.0, 4, 8, TRUE, (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown'), NOW(), NOW()),
    ('Frontend Developer', 'Creating user interfaces for websites', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, 2, 5, TRUE, (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown'), NOW(), NOW()),
    ('UX Designer', 'Designing user experiences for digital products', (SELECT id FROM categories WHERE name = 'Design'), 3500.0, 2, 4, TRUE, (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown'), NOW(), NOW()),
    ('Marketing Specialist', 'Developing and implementing marketing strategies', (SELECT id FROM categories WHERE name = 'Marketing'), 4000.0, 3, 6, TRUE, (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown'), NOW(), NOW()),
    ('Backend Developer', 'Developing server-side logic and databases', (SELECT id FROM categories WHERE name = 'IT'), 3800.0, 2, 5, TRUE, (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green'), NOW(), NOW()),
    ('Software Engineer', 'Designing and developing software applications', (SELECT id FROM categories WHERE name = 'IT'), 4000.0, 3, 6, TRUE, (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green'), NOW(), NOW()),
    ('UI Designer', 'Creating visually appealing user interfaces', (SELECT id FROM categories WHERE name = 'Design'), 3200.0, 1, 3, TRUE, (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green'), NOW(), NOW()),
    ('Marketing Manager', 'Overseeing marketing campaigns and strategies', (SELECT id FROM categories WHERE name = 'Marketing'), 4200.0, 5, 8, TRUE, (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green'), NOW(), NOW()),
    ('Project Manager', 'Managing and coordinating project teams and tasks', (SELECT id FROM categories WHERE name = 'Management'), 4500.0, 4, 7, TRUE, (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green'), NOW(), NOW());

INSERT INTO respondedApplications (resumeId, vacancyId, confirmation)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Backend Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), (SELECT id FROM vacancies WHERE name = 'Frontend Developer' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), (SELECT id FROM vacancies WHERE name = 'Mobile Developer' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'System Analyst' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), (SELECT id FROM vacancies WHERE name = 'Network Administrator' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Backend Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), (SELECT id FROM vacancies WHERE name = 'Frontend Developer' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), (SELECT id FROM vacancies WHERE name = 'Mobile Developer' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'System Analyst' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), (SELECT id FROM vacancies WHERE name = 'Network Administrator' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Software Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), (SELECT id FROM vacancies WHERE name = 'Full Stack Developer' AND authorId = (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), (SELECT id FROM vacancies WHERE name = 'Network Engineer' AND authorId = (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'System Architect' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), (SELECT id FROM vacancies WHERE name = 'Frontend Developer' AND authorId = (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Web Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), (SELECT id FROM vacancies WHERE name = 'Backend Developer' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Software Tester' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), (SELECT id FROM vacancies WHERE name = 'Software Engineer' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), (SELECT id FROM vacancies WHERE name = 'UI Designer' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Marketing Specialist' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), (SELECT id FROM vacancies WHERE name = 'Marketing Manager' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')), TRUE),
    ((SELECT id FROM resumes WHERE name = 'Content Writer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), (SELECT id FROM vacancies WHERE name = 'Project Manager' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')), TRUE);

INSERT INTO contactTypes (type)
VALUES
    ('Telegram'),
    ('Email'),
    ('Phone'),
    ('Facebook'),
    ('Linkedin');

INSERT INTO contactInfos (typeId, resumeId, contactValue)
VALUES
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'UI/UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 'jane@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'UI/UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), '987654321'),
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'Graphic Artist' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 'jack@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'Graphic Artist' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), '111111111'),
    ((SELECT id FROM contactTypes WHERE type = 'Email'), (SELECT id FROM resumes WHERE name = 'Marketing Specialist' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 'emily@example.com'),
    ((SELECT id FROM contactTypes WHERE type = 'Phone'), (SELECT id FROM resumes WHERE name = 'Marketing Specialist' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), '222222222');


INSERT INTO educationInfos (resumeId, institution, program, startDate, endDate, degree)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Backend Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 'University of Java', 'Computer Science', '2010-09-01', '2014-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 'University of SQL', 'Database Management', '2011-09-01', '2015-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'System Analyst' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 'System Analysis Institute', 'Systems Engineering', '2009-09-01', '2013-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'UI/UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 'Design University', 'UI/UX Design', '2007-09-01', '2011-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Graphic Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 'Graphic Design School', 'Graphic Design', '2008-09-01', '2012-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Software Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 'University of Java', 'Software Engineering', '2011-09-01', '2015-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 'University of SQL', 'Database Management', '2012-09-01', '2016-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'System Architect' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 'System Architecture Institute', 'System Architecture', '2010-09-01', '2014-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'UI/UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 'Design University', 'UI/UX Design', '2008-09-01', '2012-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Graphic Artist' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 'Graphic Art School', 'Graphic Art', '2009-09-01', '2013-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Web Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 'Web Development Institute', 'Web Development', '2012-09-01', '2016-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Software Tester' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 'Software Testing School', 'Software Testing', '2013-09-01', '2017-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 'UX Design Institute', 'UX Design', '2011-09-01', '2015-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Marketing Specialist' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 'Marketing Institute', 'Marketing', '2012-09-01', '2016-06-30', 'Bachelor'),
    ((SELECT id FROM resumes WHERE name = 'Content Writer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 'Writing School', 'Writing', '2013-09-01', '2017-06-30', 'Bachelor');

INSERT INTO messages (respondedAppId, content, timestamp)
VALUES
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Backend Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Frontend Developer' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Mobile Developer' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'System Analyst' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Network Administrator' AND authorId = (SELECT id FROM users WHERE name = 'John' AND surname = 'Doe')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Software Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Full Stack Developer' AND authorId = (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Network Engineer' AND authorId = (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'System Architect' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Frontend Developer' AND authorId = (SELECT id FROM users WHERE name = 'Michael' AND surname = 'Brown')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Web Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Backend Developer' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Software Tester' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Software Engineer' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'UI Designer' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Marketing Specialist' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Marketing Manager' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')) LIMIT 1), 'Thank you for your application', NOW()),
    ((SELECT id FROM respondedApplications WHERE resumeId = (SELECT id FROM resumes WHERE name = 'Content Writer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')) AND vacancyId = (SELECT id FROM vacancies WHERE name = 'Project Manager' AND authorId = (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Green')) LIMIT 1), 'Thank you for your application', NOW());

INSERT INTO workExperienceInfo (resumeId, years, companyName, position, responsibility)
VALUES
    ((SELECT id FROM resumes WHERE name = 'Backend Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 3, 'ABC Company', 'Software Engineer', 'Developed and maintained software applications'),
    ((SELECT id FROM resumes WHERE name = 'UI/UX Designer' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 2, 'XYZ Corporation', 'Web Developer', 'Designed and implemented web applications'),
    ((SELECT id FROM resumes WHERE name = 'Database Administrator' AND applicantId = (SELECT id FROM users WHERE name = 'Jane' AND surname = 'Smith')), 4, 'DEF Industries', 'Database Administrator', 'Managed and optimized databases'),
    ((SELECT id FROM resumes WHERE name = 'Software Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 5, 'GHI Corporation', 'Senior Software Developer', 'Led development teams and implemented complex solutions'),
    ((SELECT id FROM resumes WHERE name = 'System Architect' AND applicantId = (SELECT id FROM users WHERE name = 'Jack' AND surname = 'Black')), 6, 'JKL Enterprises', 'System Architect', 'Designed and architected large-scale systems'),
    ((SELECT id FROM resumes WHERE name = 'Web Developer' AND applicantId = (SELECT id FROM users WHERE name = 'Emily' AND surname = 'White')), 3, 'MNO Tech', 'Frontend Developer', 'Developed user interfaces for web applications');
