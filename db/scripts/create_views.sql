CREATE VIEW StudentAchievements AS
SELECT 
    s.student_id,
    s.name AS student_name,
    c.certification_name,
    b.badge_name,
    c.awarded_date AS certification_awarded_date,
    b.awarded_date AS badge_awarded_date
FROM 
    Student s
LEFT JOIN Certification c ON s.student_id = c.student_id
LEFT JOIN Badge b ON s.student_id = b.student_id;
