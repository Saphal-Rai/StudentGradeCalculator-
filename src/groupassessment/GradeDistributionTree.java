package groupassessment;

public class GradeDistributionTree {
    
    private static class TreeNode {
        String grade;
        int count;
        TreeNode left, right;
        
        TreeNode(String grade) {
            this.grade = grade;
            this.count = 1;
            left = right = null;
        }
    }
    
    private TreeNode root;
    
    public void insert(String grade) {
        root = insertRec(root, grade);
    }
    
    private TreeNode insertRec(TreeNode root, String grade) {
        if (root == null) return new TreeNode(grade);
        
        if (grade.compareTo(root.grade) < 0)
            root.left = insertRec(root.left, grade);
        else if (grade.compareTo(root.grade) > 0)
            root.right = insertRec(root.right, grade);
        else
            root.count++;
        
        return root;
    }
    
    public void inorder() {
        System.out.print("\n🌳 Inorder Traversal: ");
        inorderRec(root);
        System.out.println();
    }
    
    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.grade + "(" + root.count + ") ");
            inorderRec(root.right);
        }
    }
    
    public void preorder() {
        System.out.print("📊 Preorder Traversal: ");
        preorderRec(root);
        System.out.println();
    }
    
    private void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.grade + "(" + root.count + ") ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }
    
    public void postorder() {
        System.out.print("📊 Postorder Traversal: ");
        postorderRec(root);
        System.out.println();
    }
    
    private void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.grade + "(" + root.count + ") ");
        }
    }
}