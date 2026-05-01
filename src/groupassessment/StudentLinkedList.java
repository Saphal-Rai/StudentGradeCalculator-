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
    
    public void remove(int id) throws StudentNotFoundException {
        Student s = idMap.remove(id);
        if (s == null) throw new StudentNotFoundException("Student ID " + id + " not found");
        list.remove(s);
    }
    
    public void displayAll() {
        System.out.println("\n📋 All Students (LinkedList):");
        if (list.isEmpty()) {
            System.out.println("   No students");
            return;
        }
        for (Student s : list) {
            System.out.println("   " + s.getName() + " (ID: " + s.getId() + ") - " + s.getGrade());
        }
    }
    
    public void sortByPercentage() {
        list.sort((a, b) -> Double.compare(b.getPercentage(), a.getPercentage()));
        System.out.println("✅ Sorted by percentage");
    }
    
    public int size() { return list.size(); }
    public List<Student> getAll() { return new ArrayList<>(list); }
}
