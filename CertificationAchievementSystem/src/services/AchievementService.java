import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AchievementService implements Awardable {
    /**
     * Awards a certification to a student for completing a course.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     */
    @Override
    public void awardCertificate(int studentId, int courseId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO certifications (student_id, course_id) VALUES (?, ?)";
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
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO badges (student_id, achievement_id) VALUES (?, ?)";
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
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM achievements WHERE student_id = ? AND achievement_id = ? AND completed = true";
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
}
