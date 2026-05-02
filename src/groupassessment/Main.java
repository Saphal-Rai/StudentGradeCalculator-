package groupassessment;
import java.io.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentLinkedList studentList;
    private static StudentProcessingQueue queue;
    private static GradeHistoryStack stack;
    private static GradeDistributionTree tree;
    
    public static void main(String[] args) {
        initializeSystem();
        
        printWelcomeScreen();
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: viewAllStudents(); break;
                    case 3: searchStudent(); break;
                    case 4: updateStudent(); break;
                    case 5: deleteStudent(); break;
                    case 6: addMarksToStudent(); break;
                    case 7: viewStudentDetails(); break;
                    case 8: sortStudents(); break;
                    case 9: processQueue(); break;
                    case 10: viewGradeHistory(); break;
                    case 11: showGradeDistribution(); break;
                    case 12: generateReport(); break;
                    case 13: saveAndExit(); return;
                    default: 
                        System.out.println("Invalid! Please enter (1-13)");
                        pressEnterToContinue();
                }
            } catch (Exception e) {
                System.out.println("Error!!: " + e.getMessage());
                pressEnterToContinue();
            }
        }
    }
    
    private static void initializeSystem() {
        studentList = new StudentLinkedList();
        queue = new StudentProcessingQueue();
        stack = new GradeHistoryStack();
        tree = new GradeDistributionTree();
     
        try {
            List<Student> loadedStudents = FileManager.loadStudents();
            for (Student s : loadedStudents) {
                studentList.add(s);
                if (s.getPercentage() > 0) {
                    tree.insert(s.getGrade());
                }
            }
            if (loadedStudents.size() > 0) {
                System.out.println(" Loaded " + loadedStudents.size() + " existing students");
            }
        } catch (Exception e) {

        }
    }
    
    private static void printWelcomeScreen() {
        System.out.println("                   STUDENT GRADE MANAGEMENT APPLICATION                                 ");
    }
    
    private static void displayMainMenu() {;
        System.out.println("1.  Add New Student ");
        System.out.println("2.  View All Students ");
        System.out.println("3.  Search Students   ");
        System.out.println("4.  Update Student Info ");
        System.out.println("5.  Delete Student     ");
        System.out.println("6.  Add Marks & Calculate Grade   ");
        System.out.println("7.  View Student Details with Grades   ");
        System.out.println("8.  Sort Students by %    ");
        System.out.println("9.  Process Student Queue");
        System.out.println("10. View Grade History ");
        System.out.println("11. Show Grade Distribution  ");
        System.out.println("12. Generate Report");
        System.out.println("13. Save & Exit ");
    }
    
    private static void addStudent() throws IOException {
        System.out.println("ADD NEW STUDENT");
        
        System.out.print("│  Enter name: ");
        String name = scanner.next();
        
        System.out.print("Enter ID: ");
        int id = getIntInput("");
        
        System.out.print("Enter email: ");
        String email = scanner.next();
        
        System.out.print("Enter section (A/B/C): ");
        String section = scanner.next().toUpperCase();
        
        Student student = new Student(name, id, email, section);
        studentList.add(student);
        queue.enqueue(student);
        FileManager.saveStudent(student);
        
        System.out.println("Student added successfully!");
        System.out.printf("Total students: %-26d", studentList.size());
        pressEnterToContinue();
    }
    
    private static void viewAllStudents() {
        studentList.displayAll();
        pressEnterToContinue();
    }
    
    private static void searchStudent() {
        System.out.println("SEARCH STUDENT");
        System.out.print("Enter Student ID: ");
        int id = getIntInput("");
        
        try {
            studentList.displayStudentDetails(id);
        } catch (StudentNotFoundException e) {
            System.out.println("  " + String.format("%-36s", e.getMessage()) + "");
        }
        pressEnterToContinue();
    }
    
    private static void updateStudent() throws IOException, StudentNotFoundException {
        System.out.println("UPDATE STUDENT DATA");
        System.out.print("Enter Student ID to update: ");
        int id = getIntInput("");
        
        Student student = studentList.find(id);
        if (student == null) {
            System.out.println("Student not found!! Error");
            pressEnterToContinue();
            return;
        }
        
        System.out.println(" Information:   ");
        System.out.printf("Name: %-33s", student.getName());
        System.out.printf("Email: %-32s", student.getEmail());
        System.out.printf("Section: %-30s", student.getSection());
        
        System.out.print("Enter new name |press Enter to keep|: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        
        System.out.print("Enter new email |press Enter to keep|: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter new section |press Enter to keep|: ");
        String section = scanner.nextLine();
        
        studentList.update(id, name.isEmpty() ? null : name, 
                          email.isEmpty() ? null : email, 
                          section.isEmpty() ? null : section);
        
        FileManager.saveAllStudents(studentList.getAll());
        
        System.out.println("Student updated successfully!!");
        pressEnterToContinue();
    }
    
    private static void deleteStudent() throws IOException, StudentNotFoundException {
        System.out.println("DELETE STUDENT");
        System.out.print("Enter Student ID: ");
        int id = getIntInput("");
        
        Student student = studentList.find(id);
        if (student == null) {
            System.out.println("Student not found!!");
            pressEnterToContinue();
            return;
        }
        
        System.out.printf("Student: %-30s", student.getName());
        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.next();
        
        if (confirm.equalsIgnoreCase("y")) {
            studentList.delete(id);
            FileManager.saveAllStudents(studentList.getAll());
            System.out.println("Deleted Student Successfully!!");
        } else {
            System.out.println("Deletion cancelled!!");
        }
        pressEnterToContinue();
    }
    

    private static void addMarksToStudent() throws InvalidMarkException, IOException {
        System.out.println("ADD MARKS TO STUDENT ");
        System.out.print(" Enter Student ID: ");
        int id = getIntInput("");
        
        Student student = studentList.find(id);
        if (student == null) {
            System.out.println("Student not found in System!!");
            pressEnterToContinue();
            return;
        }
        
        System.out.printf("Student: %-30s", student.getName());
        System.out.print("Enter number of subjects: ");
        int numSubjects = getIntInput("");
        
        double oldPercentage = student.getPercentage();
        
        System.out.println("  Enter marks Between (0-100):                   │");
        for (int i = 1; i <= numSubjects; i++) {
            System.out.printf("Subject %d: ", i);
            int mark = getIntInput("");
            student.addMark(mark);
        }
        
        student.calculateResults();
        
        GradeRecord record = new GradeRecord(id, student.getName(), oldPercentage, student.getPercentage());
        stack.push(record);
        
        tree.insert(student.getGrade());
        
        FileManager.saveAllStudents(studentList.getAll());
        
        System.out.println(" Added Marks successfully! ");
        System.out.printf("  New Percentage: %.2f%% ", student.getPercentage());
        System.out.printf("Grade: %-33s", student.getGrade());
        pressEnterToContinue();
    }
    
    private static void viewStudentDetails() {
        System.out.println("STUDENT DETAILS WITH GRADE");
        System.out.print("Enter Student ID: ");
        int id = getIntInput("");
        
        try {
            Student student = studentList.find(id);
            if (student == null) {
                throw new StudentNotFoundException("Student not found");
            }
            student.displayInfo();
            System.out.println("   Remarks: " + GradeUtils.getRemarks(student.getPercentage()));
        } catch (StudentNotFoundException e) {
            System.out.println(" " + String.format("%-36s", e.getMessage()) + "");
        }
        pressEnterToContinue();
    }
    
    private static void sortStudents() {
        System.out.println("SORT STUDENTS ");
        System.out.println("1. Sort by % From Highest  ");
        System.out.println("2. Sort by Name ");
        System.out.println("3. Sort by ID ");
        System.out.print("Enter Your choice: ");
        int choice = getIntInput("");
        
        switch(choice) {
            case 1: studentList.sortByPercentage(); break;
            case 2: studentList.sortByName(); break;
            case 3: studentList.sortById(); break;
            default: System.out.println(" Invalid choice!!"); 
        }
        studentList.displayAll();
        pressEnterToContinue();
    }
    
    private static void processQueue() {
        queue.processAll();
        pressEnterToContinue();
    }
    
    private static void viewGradeHistory() {
        stack.display();
        pressEnterToContinue();
    }
    
    private static void showGradeDistribution() {
        System.out.println("GRADE DISTRIBUTION!!");
        tree.inorder();
        tree.preorder();
        tree.postorder();
        pressEnterToContinue();
    }
    
    private static void generateReport() throws IOException {
        System.out.println("           GENERATE REPORT              ");
        FileManager.generateReport(studentList.getAll());
        pressEnterToContinue();
    }
    
    private static void saveAndExit() throws IOException {
        System.out.println(" SAVING & EXITING");
        FileManager.saveAllStudents(studentList.getAll());
        System.out.println(" Thank You");
        System.exit(0);
    }
    
    private static int getIntInput(String prompt) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid!! Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static void pressEnterToContinue() {
        System.out.print(" Press Enter to continue   ");
        try {
            System.in.read();
        } catch (Exception e) {}
    }
}