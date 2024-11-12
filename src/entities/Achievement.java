public class Achievement {
    private int id;
    private String description;
    private int studentId;
    private boolean completed;

    public Achievement(int id, String description, int studentId, boolean completed) {
        this.id = id;
        this.description = description;
        this.studentId = studentId;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getStudentId() {
        return studentId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
