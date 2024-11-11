public class AwardService {
    private AchievementService achievementService;

    public AwardService() {
        this.achievementService = new AchievementService();
    }

    /**
     * Awards a certification to a student if they meet the course completion requirements.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     */
    public void awardCertificationIfEligible(int studentId, int courseId) {
        boolean eligible = achievementService.verifyAchievement(studentId, courseId);
        if (eligible) {
            achievementService.awardCertificate(studentId, courseId);
        } else {
            System.out.println("Student ID " + studentId + " has not met the requirements for course ID " + courseId);
        }
    }

    /**
     * Grants a badge to a student if they have completed the specific achievement.
     *
     * @param studentId The ID of the student.
     * @param achievementId The ID of the achievement.
     */
    public void grantBadgeIfEligible(int studentId, int achievementId) {
        boolean eligible = achievementService.verifyAchievement(studentId, achievementId);
        if (eligible) {
            achievementService.grantBadge(studentId, achievementId);
        } else {
            System.out.println("Student ID " + studentId + " has not completed achievement ID " + achievementId);
        }
    }
}
