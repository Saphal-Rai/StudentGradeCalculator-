package groupassessment;
import java.io.*;
import java.util.*;

public class FileManager {
    private static final String STUDENTS_FILE = "students.txt";
    
    public static void saveStudent(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE, true))) {
            String line = student.getId() + "," + 
                         student.getName() + "," + 
                         student.getEmail() + "," + 
                         student.getSection() + "," + 
                         student.getPercentage() + "," + 
                         student.getGrade() + "," +
                         student.getMarks();
            writer.write(line);
            writer.newLine();
        }
    }
    
    public static void saveAllStudents(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student s : students) {
                String line = s.getId() + "," + s.getName() + "," + s.getEmail() + "," + 
                             s.getSection() + "," + s.getPercentage() + "," + s.getGrade() + "," + s.getMarks();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("All data  are saved successfully!");
        }
    }
    
    public static List<Student> loadStudents() throws IOException, InvalidMarkException {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENTS_FILE);
        
        if (!file.exists()) {
            return students;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Student student = new Student(parts[1], Integer.parseInt(parts[0]), parts[2], parts[3]);
                    
                    if (parts.length >= 7 && !parts[6].equals("[]")) {
                        String marksStr = parts[6].replace("[", "").replace("]", "");
                        if (!marksStr.isEmpty()) {
                            String[] marksArray = marksStr.split(", ");
                            for (String mark : marksArray) {
                                if (!mark.trim().isEmpty()) {
                                    student.addMark(Integer.parseInt(mark.trim()));
                                }
                            }
                            student.calculateResults();
                        }
                    }
                    students.add(student);
                }
            }
        }
        return students;
    }
    
    public static void generateReport(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("grade_report.txt"))) {
            writer.write("STUDENT GRADE REPORT!!");
            
            if (students.isEmpty()) {
                writer.write("No students in the system. Thank You!!");
            } else {
                double totalPercentage = 0;
                int passCount = 0;
                int distinctionCount = 0;
                
                for (Student s : students) {
                    writer.write("Student ID: " + s.getId() + " ");
                    writer.write("Name: " + s.getName() + " ");
                    writer.write("Email: " + s.getEmail() + " ");
                    writer.write("Section: " + s.getSection() + " ");
                    writer.write("Percentage: " + String.format("%.2f", s.getPercentage()) + "%");
                    writer.write("Grade: " + s.getGrade() + " ");
                    writer.write("Marks: " + s.getMarks() + " ");
                    writer.write("Remarks: " + GradeUtils.getRemarks(s.getPercentage()) + " ");
                    totalPercentage += s.getPercentage();
                    if (s.getPercentage() >= 33) passCount++;
                    if (s.getPercentage() >= 75) distinctionCount++;
                }
                
                writer.write(" SUMMARY STATISTICS: ");
                writer.write("Total Students: " + students.size() + "\n");
                writer.write("Average %: " + String.format("%.2f", totalPercentage / students.size()) + "% ");
                writer.write("Pass Count: " + passCount + " ");
                writer.write("Fail Count: " + (students.size() - passCount) + " ");
                writer.write("Distinctions (75%+): " + distinctionCount + " ");
            }
        }
        System.out.println("Generated Report: grade_report.txt");
    }
}