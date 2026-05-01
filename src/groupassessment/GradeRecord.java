package groupassessment;
import java.util.Date;

public class GradeRecord {
    private int studentId;
    private String studentName;
    private double oldPercentage;
    private double newPercentage;
    private Date timestamp;
    
    public GradeRecord(int id, String name, double oldPerc, double newPerc) {
        this.studentId = id;
        this.studentName = name;
        this.oldPercentage = oldPerc;
        this.newPercentage = newPerc;
        this.timestamp = new Date();
    }
    
    public String toString() {
        return String.format("[%s] %s (ID: %d): %.2f%% → %.2f%%", 
            timestamp, studentName, studentId, oldPercentage, newPercentage);
    }
}