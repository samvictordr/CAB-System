import java.util.Date;

public class Certification {
    private int id;
    private String name;
    private int courseId;
    private Date awardedDate;
    private int studentId;

    public Certification(int id, String name, int courseId, Date awardedDate, int studentId) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.awardedDate = awardedDate;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCourseId() {
        return courseId;
    }

    public Date getAwardedDate() {
        return awardedDate;
    }

    public int getStudentId() {
        return studentId;
    }
}
