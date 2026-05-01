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
        studentList = new StudentLinkedList();
        queue = new StudentProcessingQueue();
        stack = new GradeHistoryStack();
        tree = new GradeDistributionTree();
        
        loadData();
        System.out.println("║   STUDENT GRADE CALCULATOR APPLICATION    ║");
        
        
        while (true) {
            showMenu();
            int choice = getIntInput("Choice: ");
            
            try {
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: addMarks(); break;
                    case 3: displayStudents(); break;
                    case 4: processQueue(); break;
                    case 5: showStack(); break;
                    case 6: showTree(); break;
                    case 7: searchStudent(); break;
                    case 8: sortStudents(); break;
                    case 9: generateReport(); break;
                    case 10: saveAndExit(); return;
                    default: System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Add Marks to Student");
        System.out.println("3. Display All Students");
        System.out.println("4. Process Queue (FIFO)");
        System.out.println("5. View Grade History");
        System.out.println("6. Show Grade Distribution");
        System.out.println("7. Search Student");
        System.out.println("8. Sort Students by %");
        System.out.println("9. Generate Report");
        System.out.println("10. Save & Exit");
    }
    
    private static void addStudent() throws IOException {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter ID: ");
        int id = getIntInput("");
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter section (A/B/C): ");
        String section = scanner.next().toUpperCase();
        
        Student s = new Student(name, id, email, section);
        studentList.add(s);
        queue.enqueue(s);
        FileManager.saveStudent(s);
        
        System.out.println("✅ Student added! Total: " + studentList.size());
    }
    
    private static void addMarks() throws InvalidMarkException, IOException {
        System.out.print("Enter Student ID: ");
        int id = getIntInput("");
        
        Student s = studentList.find(id);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Enter number of subjects: ");
        int num = getIntInput("");
        
        double oldPerc = s.getPercentage();
        
        for (int i = 1; i <= num; i++) {
            System.out.print("Subject " + i + " marks (0-100): ");
            int mark = getIntInput("");
            s.addMark(mark);
        }
        
        s.calculateResults();
        
        GradeRecord record = new GradeRecord(id, s.getName(), oldPerc, s.getPercentage());
        stack.push(record);
        
        tree.insert(s.getGrade());
        
        System.out.println("\n✨ RESULTS:");
        s.displayInfo();
        System.out.println("Remarks: " + GradeUtils.getRemarks(s.getPercentage()));
        
        FileManager.saveStudent(s);
    }
    
    private static void displayStudents() {
        studentList.displayAll();
    }
    
    private static void processQueue() {
        queue.processAll();
    }
    
    private static void showStack() {
        stack.display();
    }
    
    private static void showTree() {
        System.out.println("\n=== BINARY TREE TRAVERSALS ===");
        tree.inorder();
        tree.preorder();
        tree.postorder();
    }
    
    private static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = getIntInput("");
        Student s = studentList.find(id);
        if (s != null) {
            s.displayInfo();
        } else {
            System.out.println("Student not found!");
        }
    }
    
    private static void sortStudents() {
        studentList.sortByPercentage();
        displayStudents();
    }
    
    private static void generateReport() throws IOException {
        FileManager.generateReport(studentList.getAll());
    }
    
    private static void loadData() {
        try {
            List<String[]> records = FileManager.loadStudents();
            System.out.println("📁 Loaded " + records.size() + " previous records");
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
    
    private static void saveAndExit() throws IOException {
        System.out.println("💾 Saving data...");
        System.out.println("👋 Goodbye!");
        System.exit(0);
    }
    
    private static int getIntInput(String prompt) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid! Enter number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}