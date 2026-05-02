package groupassessment;
import java.util.*;


public class StudentLinkedList {
    private LinkedList<Student> list;
    private Map<Integer, Student> idMap;
    
    public StudentLinkedList() {
        list = new LinkedList<>();
        idMap = new HashMap<>();
    }
    
    public void add(Student student) {
        list.add(student);
        idMap.put(student.getId(), student);
    }
    
    public Student find(int id) {
        return idMap.get(id);
    }
    
    public void update(int id, String name, String email, String section) throws StudentNotFoundException {
        Student student = find(id);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + id + " not found");
        }
        student.updateInfo(name, email, section);
    }
    
    public void delete(int id) throws StudentNotFoundException {
        Student student = idMap.remove(id);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + id + " not found");
        }
        list.remove(student);
    }
    
    public void displayAll() {
        System.out.println("      LIST OF ALL STUDENTS            ");
        if (list.isEmpty()) {
            System.out.println("       No students registered    ");
        } else {
            int index = 1;
            for (Student s : list) {
                System.out.printf("  %d. %-20s ID: %-10d", index++, s.getName(), s.getId());
                System.out.printf("     Grade: %-5s (%.2f%%)          ", s.getGrade(), s.getPercentage());
            }
        }
    }
    
    public void displayStudentDetails(int id) throws StudentNotFoundException {
        Student student = find(id);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + id + " not found");
        }
        student.displayInfo();
    }
    
    public void sortByPercentage() {
        list.sort((a, b) -> Double.compare(b.getPercentage(), a.getPercentage()));
        System.out.println(" Sorted by % From highest");
    }
    
    public void sortByName() {
        list.sort(Comparator.comparing(Student::getName));
        System.out.println(" Sorted by name");
    }
    
    public void sortById() {
        list.sort(Comparator.comparingInt(Student::getId));
        System.out.println(" Sorted by Student ID");
    }
    
    public int size() { return list.size(); }
    public List<Student> getAll() { return new ArrayList<>(list); }
}