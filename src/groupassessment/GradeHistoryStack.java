package groupassessment;
import java.util.Stack;

public class GradeHistoryStack {
    private Stack<GradeRecord> stack;
    
    public GradeHistoryStack() {
        stack = new Stack<>();
    }
    
    public void push(GradeRecord record) {
        stack.push(record);
        System.out.println(" Chaged Grade are saved to stack");
    }
    
    public GradeRecord pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return null;
    }
    
    public void display() {
        System.out.println("        HISTORY GRADE       ");
        if (stack.isEmpty()) {
            System.out.println("         No available!!!, grade history     ");
        } else {
            System.out.println(" Recent - Oldest        ");
            for (int i = stack.size() - 1; i >= 0; i--) {
                System.out.printf("  %d. %-36s ", (stack.size() - i), stack.get(i).toString().substring(0, Math.min(36, stack.get(i).toString().length())));
            }
        }
    }
}