package groupassessment;

import java.util.ArrayList;
import java.util.Arrays;

public class Student extends Person {
    private ArrayList<Integer> marks;      // ArrayList
    private int[] marksArray;               // Array
    private double percentage;
    private String grade;
    private String section;
    private static int totalStudents = 0;
    
    public Student(String name, int id, String email, String section) {
        super(name, id, email);
        this.marks = new ArrayList<>();
        this.marksArray = new int[0];
        this.section = section;
        totalStudents++;
    }
    
    public void addMark(int mark) throws InvalidMarkException {
        if (mark < 0 || mark > 100) {
            throw new InvalidMarkException("Mark " + mark + " is invalid!");
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
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        percentage = (double) total / marks.size();
        grade = GradeUtils.getGrade(percentage);
    }
    
    // Getters
    public double getPercentage() { return percentage; }
    public String getGrade() { return grade; }
    public ArrayList<Integer> getMarks() { return marks; }
    public int[] getMarksArray() { return marksArray; }
    public String getSection() { return section; }
    public static int getTotalStudents() { return totalStudents; }
    
  
    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name + " | Section: " + section);
        System.out.println("   Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("   Grade: " + grade);
        System.out.println("   Marks: " + marks);
        System.out.println("   Marks Array: " + Arrays.toString(marksArray));
    }
    
    public String getRole() {
        return "Student";
    }
}