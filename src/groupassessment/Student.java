package groupassessment;
import java.util.ArrayList;
import java.util.Arrays;

public class Student extends Person {
    private ArrayList<Integer> marks;
    private int[] marksArray;
    private double percentage;
    private String grade;
    private String section;
    
    public Student(String name, int id, String email, String section) {
        super(name, id, email);
        this.marks = new ArrayList<>();
        this.marksArray = new int[0];
        this.section = section;
        this.percentage = 0;
        this.grade = "N/A";
    }
    
    public void addMark(int mark) throws InvalidMarkException {
        if (mark < 0 || mark > 100) {
            throw new InvalidMarkException("Mark " + mark + " is invalid! Must be between 0-100");
        }
        marks.add(mark);
        updateMarksArray();
    }
    
    private void updateMarksArray() {
        marksArray = new int[marks.size()];
        for (int i = 0; i < marks.size(); i++) {
            marksArray[i] = marks.get(i);
        }
    }
    
    public void calculateResults() {
        if (marks.isEmpty()) {
            percentage = 0;
            grade = "N/A";
            return;
        }
        
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        percentage = (double) total / marks.size();
        grade = GradeUtils.getGrade(percentage);
    }
    
    public void updateInfo(String name, String email, String section) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (email != null && !email.trim().isEmpty()) {
            this.email = email;
        }
        if (section != null && !section.trim().isEmpty()) {
            this.section = section.toUpperCase();
        }
    }
    
    public double getPercentage() { return percentage; }
    public String getGrade() { return grade; }
    public ArrayList<Integer> getMarks() { return marks; }
    public int[] getMarksArray() { return marksArray; }
    public String getSection() { return section; }
   
    public void displayInfo() {
        System.out.printf(" Student ID: %-30d ", id);
        System.out.printf(" Name: %-33s ", name);
        System.out.printf(" Email: %-32s ", email);
        System.out.printf(" Section: %-30s ", section);
        System.out.printf(" Percentage: %-28.2f%% ", percentage);
        System.out.printf(" Grade: %-32s ", grade);
        System.out.printf(" Marks: %-32s ", marks.toString());
    }
    
    public String getRole() {
        return "Student";
    }
    
    public String toFileString() {
        return id + " " + name + " " + email + " " + section + " " + percentage + " " + grade + " " + marks.toString();
    }
}