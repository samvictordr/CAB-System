-- Insert sample students
INSERT INTO Student (name, email, enrollment_date) VALUES
('Alice', 'alice@example.com', '2024-01-10'),
('Bob', 'bob@example.com', '2024-01-12');

-- Insert sample courses
INSERT INTO Course (course_name, description) VALUES
('Java Basics', 'Introduction to Java programming'),
('Database Management', 'Introduction to databases and SQL');

-- Insert sample achievements
INSERT INTO Achievement (achievement_name, description, course_id) VALUES
('Complete Java Basics', 'Complete all modules of Java Basics', 1),
('Pass Final Exam', 'Pass the final exam of Database Management', 2);

-- Insert sample badges
INSERT INTO Badge (badge_name, achievement_id, awarded_date, student_id) VALUES
('Java Basics Completion Badge', 1, '2024-02-15', 1),
('Database Exam Badge', 2, '2024-03-01', 2);

-- Insert sample certifications
INSERT INTO Certification (certification_name, course_id, awarded_date, student_id) VALUES
('Certified Java Programmer', 1, '2024-02-20', 1),
('Certified Database Manager', 2, '2024-03-05', 2);
