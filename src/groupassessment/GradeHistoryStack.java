package groupassessment;
import java.util.Stack;

public class GradeHistoryStack {
    private Stack<GradeRecord> stack;
    
    public GradeHistoryStack() {
        stack = new Stack<>();
    }
    
    public void push(GradeRecord record) {
        stack.push(record);
        System.out.println("✅ Pushed to stack");
    }
    
    public GradeRecord pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return null;
    }
    
    public void display() {
        System.out.println("\n📚 Grade History Stack (LIFO):");
        if (stack.isEmpty()) {
            System.out.println("   Stack is empty");
            return;
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println("   " + (stack.size() - i) + ". " + stack.get(i));
        }
    }
}