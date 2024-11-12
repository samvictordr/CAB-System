CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    enrollment_date DATE
);

CREATE TABLE Course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE Achievement (
    achievement_id INT AUTO_INCREMENT PRIMARY KEY,
    achievement_name VARCHAR(100) NOT NULL,
    description TEXT,
    course_id INT,
    student_id INT,
    completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE
);

CREATE TABLE Badge (
    badge_id INT AUTO_INCREMENT PRIMARY KEY,
    badge_name VARCHAR(100) NOT NULL,
    achievement_id INT,
    awarded_date DATE,
    student_id INT,
    FOREIGN KEY (achievement_id) REFERENCES Achievement(achievement_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE
);

CREATE TABLE Certification (
    certification_id INT AUTO_INCREMENT PRIMARY KEY,
    certification_name VARCHAR(100) NOT NULL,
    course_id INT,
    awarded_date DATE,
    student_id INT,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE
);
