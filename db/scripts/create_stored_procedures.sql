DELIMITER $$

CREATE PROCEDURE AwardCertification (IN studentId INT, IN courseId INT)
BEGIN
    DECLARE achievementExists INT;

    -- Check if the student has completed the achievement for this course
    SELECT COUNT(*) INTO achievementExists
    FROM Achievement a
    JOIN Badge b ON a.achievement_id = b.achievement_id
    WHERE a.course_id = courseId AND b.student_id = studentId;

    -- Award certification if the achievement exists
    IF achievementExists > 0 THEN
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
