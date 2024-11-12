import java.util.Date;

public class Badge {
    private int id;
    private String name;
    private int achievementId;
    private Date awardedDate;
    private int studentId;

    public Badge(int id, String name, int achievementId, Date awardedDate, int studentId) {
        this.id = id;
        this.name = name;
        this.achievementId = achievementId;
        this.awardedDate = awardedDate;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public Date getAwardedDate() {
        return awardedDate;
    }

    public int getStudentId() {
        return studentId;
    }
}
