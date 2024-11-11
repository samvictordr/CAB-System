DELIMITER $$

CREATE TRIGGER after_achievement_insert
AFTER INSERT ON Achievement
FOR EACH ROW
BEGIN
    -- Automatically grant a badge to the student after an achievement is completed
    INSERT INTO Badge (badge_name, achievement_id, awarded_date, student_id)
    VALUES ('Achievement Badge', NEW.achievement_id, CURDATE(), NEW.student_id);
END $$

DELIMITER ;
