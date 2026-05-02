package groupassessment;

public class GradeUtils {
    public static String getGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B+";
        else if (percentage >= 60) return "B";
        else if (percentage >= 50) return "C+";
        else if (percentage >= 40) return "C";
        else if (percentage >= 33) return "D";
        else return "F";
    }
    
    public static String getRemarks(double percentage) {
        if (percentage >= 90) return " !!Excellent!!";
        if (percentage >= 75) return "Very Good!";
        if (percentage >= 60) return "Good!!";
        if (percentage >= 45) return "Satisfactory!";
        if (percentage >= 33) return "Just Passed!";
        return "Failed! Need to Improve";
    }
}