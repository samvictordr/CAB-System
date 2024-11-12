DELIMITER $$

CREATE PROCEDURE AwardCertification (IN studentId INT, IN courseId INT)
BEGIN
    -- Updated logic to check course completion
    DECLARE courseCompleted INT;
    SELECT COUNT(*) INTO courseCompleted
    FROM CourseCompletion
    WHERE student_id = studentId AND course_id = courseId;

    IF courseCompleted > 0 THEN
        INSERT INTO Certification (certification_name, course_id, awarded_date, student_id)
        VALUES ('Course Completion Certification', courseId, CURDATE(), studentId);
    END IF;
END $$

CREATE PROCEDURE GrantBadge (IN studentId INT, IN achievementId INT)
BEGIN
    -- Grant a badge for the specific achievement to the student
    INSERT INTO Badge (badge_name, achievement_id, awarded_date, student_id)
    VALUES ('Achievement Badge', achievementId, CURDATE(), studentId);
END $$

DELIMITER ;
