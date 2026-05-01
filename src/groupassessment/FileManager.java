package groupassessment;
import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "students.txt";
    
    public static void saveStudent(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(student.getId() + "," + student.getName() + "," + 
                        student.getEmail() + "," + student.getSection() + "," +
                        student.getPercentage() + "," + student.getGrade() + "," +
                        student.getMarks());
            writer.newLine();
        }
    }
    
    public static List<String[]> loadStudents() throws IOException {
        List<String[]> records = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return records;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line.split(","));
            }
        }
        return records;
    }
    
    public static void generateReport(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write("=== STUDENT GRADE REPORT ===\n\n");
            for (Student s : students) {
                writer.write("ID: " + s.getId() + " | Name: " + s.getName() + "\n");
                writer.write("Percentage: " + s.getPercentage() + "% | Grade: " + s.getGrade() + "\n");
                writer.write("Remarks: " + GradeUtils.getRemarks(s.getPercentage()) + "\n");
                writer.write("---------------------------\n");
            }
        }
        System.out.println("Report saved to report.txt");
    }
}