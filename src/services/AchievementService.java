package services;

// Remove the old import
// import interfaces.Awardable;

// Add any necessary imports
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AchievementService implements Awardable {
    private Connection dbConnection;

    public AchievementService(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Awards a certification to a student for completing a course.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     */
    @Override
    public void awardCertification(int studentId, int courseId) {
        // Updated method name and SQL statement
        try (Connection conn = dbConnection) {
            String sql = "INSERT INTO Certification (student_id, course_id, awarded_date) VALUES (?, ?, CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
            System.out.println("Certification awarded to student ID " + studentId + " for course ID " + courseId);
        } catch (SQLException e) {
            System.err.println("Error awarding certification: " + e.getMessage());
        }
    }

    /**
     * Grants a badge to a student for completing an achievement.
     *
     * @param studentId The ID of the student.
     * @param achievementId The ID of the achievement.
     */
    @Override
    public void grantBadge(int studentId, int achievementId) {
        try (Connection conn = dbConnection) {
            String sql = "INSERT INTO Badge (student_id, achievement_id, awarded_date) VALUES (?, ?, CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, achievementId);
            stmt.executeUpdate();
            System.out.println("Badge granted to student ID " + studentId + " for achievement ID " + achievementId);
        } catch (SQLException e) {
            System.err.println("Error granting badge: " + e.getMessage());
        }
    }

    /**
     * Verifies if a student has met the criteria for a given achievement.
     *
     * @param studentId The ID of the student.
     * @param achievementId The ID of the achievement.
     * @return true if the achievement criteria are met, false otherwise.
     */
    @Override
    public boolean verifyAchievement(int studentId, int achievementId) {
        // Updated SQL query to match database schema
        try (Connection conn = dbConnection) {
            String sql = "SELECT * FROM AchievementCompletion WHERE student_id = ? AND achievement_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, achievementId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a completed achievement is found
        } catch (SQLException e) {
            System.err.println("Error verifying achievement: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifies if a student has completed a specific course.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @return true if the course is completed, false otherwise.
     */
    public boolean verifyCourseCompletion(int studentId, int courseId) {
        try {
            String sql = "SELECT * FROM CourseCompletion WHERE student_id = ? AND course_id = ?";
            PreparedStatement stmt = dbConnection.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if the course completion record exists
        } catch (SQLException e) {
            System.err.println("Error verifying course completion: " + e.getMessage());
            return false;
        }
    }

    public void viewStudentAchievements(int studentId) {
        // Added method to view student achievements
        try (Connection conn = dbConnection) {
            String sql = "SELECT * FROM StudentAchievements WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String certificationName = rs.getString("certification_name");
                String badgeName = rs.getString("badge_name");
                System.out.println("Certification: " + certificationName + ", Badge: " + badgeName);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving achievements: " + e.getMessage());
        }
    }
}
