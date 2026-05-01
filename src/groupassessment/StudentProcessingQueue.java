package groupassessment;

import java.util.*;

public class StudentProcessingQueue {
    private Queue<Student> queue;
    
    public StudentProcessingQueue() {
        queue = new LinkedList<>();
    }
    
    public void enqueue(Student student) {
        queue.offer(student);
        System.out.println("✅ " + student.getName() + " added to queue");
    }
    
    public Student dequeue() {
        return queue.poll();
    }
    
    public void processAll() {
        System.out.println("\n🔄 Processing Queue (FIFO):");
        while (!queue.isEmpty()) {
            Student s = dequeue();
            if (s != null) {
                System.out.println("   Processing: " + s.getName());
                s.displayInfo();
                System.out.println();
            }
        }
    }
    
    public int size() { return queue.size(); }
    public boolean isEmpty() { return queue.isEmpty(); }
}
