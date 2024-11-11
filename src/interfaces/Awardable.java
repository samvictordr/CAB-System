public interface Awardable {
    /**
     * Awards a certification to a student for a specific course.
     * 
     * @param studentId The ID of the student to whom the certification is awarded.
     * @param courseId The ID of the course for which the certification is awarded.
     */
    void awardCertificate(int studentId, int courseId);

    /**
     * Grants a badge to a student for a specific achievement.
     * 
     * @param studentId The ID of the student to whom the badge is granted.
     * @param achievementId The ID of the achievement for which the badge is granted.
     */
    void grantBadge(int studentId, int achievementId);

    /**
     * Verifies if a student has met the criteria for a given achievement.
     * 
     * @param studentId The ID of the student.
     * @param achievementId The ID of the achievement.
     * @return true if the achievement criteria are met, false otherwise.
     */
    boolean verifyAchievement(int studentId, int achievementId);
}
