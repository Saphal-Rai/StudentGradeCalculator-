package groupassessment;
import java.util.*;

public class StudentProcessingQueue {
    private Queue<Student> queue;
    
    public StudentProcessingQueue() {
        queue = new LinkedList<>();
    }
    
    public void enqueue(Student student) {
        queue.offer(student);
        System.out.println("  " + student.getName() + " added For processing queue");
    }
    
    public Student dequeue() {
        return queue.poll();
    }
    
    public void processAll() {
        System.out.println("      PROCESSING STUDENT QUEUE        ");
        if (queue.isEmpty()) {
            System.out.println("        No students in queue!!         ");
        } else {
            int count = 1;
            while (!queue.isEmpty()) {
                Student s = dequeue();
                System.out.printf("  %d. Processing: %-25s", count++, s.getName());
            }
        }
    }
    
    public int size() { return queue.size(); }
    public boolean isEmpty() { return queue.isEmpty(); }
}