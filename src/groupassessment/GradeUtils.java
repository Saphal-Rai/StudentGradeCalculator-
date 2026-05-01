package groupassessment;

import java.util.HashMap;
import java.util.Map;

public class GradeUtils {
    private static final int[] GRADE_BOUNDARIES = {90, 80, 70, 60, 50, 40, 33, 0};
    private static final String[] GRADES = {"A+", "A", "B+", "B", "C+", "C", "D", "F"};
    private static final String[] REMARKS = {
        "🎉 Excellent! Outstanding performance!",
        "🌟 Very Good! Keep up the great work!",
        "👍 Good! Can achieve even more!",
        "📚 Satisfactory! Need consistent effort!",
        "⚠️ Below Average! Requires improvement!",
        "⚠️ Poor performance! Must work harder!",
        "⚠️ Just passed! Need serious effort!",
        "❌ Failed! Please try again next time!"
    };
    
    private static Map<String, Integer> gradePoints = new HashMap<>();
    
    static {
        gradePoints.put("A+", 10);
        gradePoints.put("A", 9);
        gradePoints.put("B+", 8);
        gradePoints.put("B", 7);
        gradePoints.put("C+", 6);
        gradePoints.put("C", 5);
        gradePoints.put("D", 4);
        gradePoints.put("F", 0);
    }
    
    public static String getGrade(double percentage) {
        for (int i = 0; i < GRADE_BOUNDARIES.length; i++) {
            if (percentage >= GRADE_BOUNDARIES[i]) {
                return GRADES[i];
            }
        }
        return "F";
    }
    
    public static String getRemarks(double percentage) {
        for (int i = 0; i < GRADE_BOUNDARIES.length; i++) {
            if (percentage >= GRADE_BOUNDARIES[i]) {
                return REMARKS[i];
            }
        }
        return REMARKS[REMARKS.length - 1];
    }
    
    public static int getGradePoint(String grade) {
        return gradePoints.getOrDefault(grade, 0);
    }
    
    public static double calculateCGPA(double percentage) {
        return percentage / 9.5;
    }
    
    public static boolean isPassing(double percentage) {
        return percentage >= 33;
    }
    
    public static String getClassRank(double percentage) {
        if (percentage >= 90) return "Distinction";
        if (percentage >= 75) return "First Class";
        if (percentage >= 60) return "Second Class";
        if (percentage >= 45) return "Third Class";
        if (percentage >= 33) return "Pass";
        return "Fail";
    }
}