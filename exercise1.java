import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Base class Student
abstract class Student {
    private String name;
    protected List<Integer> quizScores = new ArrayList<>();

    public Student(String name, List<Integer> quizScores) {
        this.name = name;
        this.quizScores = quizScores;
    }

    public String getName() {
        return name;
    }

    public double getQuizAverage() {
        int sum = 0;
        for (int score : quizScores) {
            sum += score;
        }
        return (double) sum / quizScores.size();
    }

    public List<Integer> getQuizScores() {
        return quizScores;
    }

    public abstract void printDetails();  // Abstract method to print student details
}

// Derived class for Part-time students
class PartTimeStudent extends Student {
    public PartTimeStudent(String name, List<Integer> quizScores) {
        super(name, quizScores);
    }

    @Override
    public void printDetails() {
        System.out.println("Part-Time Student: " + getName());
    }
}

// Derived class for Full-time students
class FullTimeStudent extends Student {
    private int exam1Score;
    private int exam2Score;

    public FullTimeStudent(String name, List<Integer> quizScores, int exam1, int exam2) {
        super(name, quizScores);
        this.exam1Score = exam1;
        this.exam2Score = exam2;
    }

    @Override
    public void printDetails() {
        System.out.println("Full-Time Student: " + getName());
        System.out.println("Exam 1: " + exam1Score + ", Exam 2: " + exam2Score);
    }

    public void printExamScores() {
        System.out.println("Exam Scores: " + exam1Score + ", " + exam2Score);
    }
}

// Class to represent a session of students
class Session {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printQuizAverages() {
        for (Student student : students) {
            System.out.println(student.getName() + "'s Quiz Average: " + student.getQuizAverage());
        }
    }

    public void printSortedQuizScores() {
        for (Student student : students) {
            List<Integer> sortedScores = new ArrayList<>(student.getQuizScores());
            Collections.sort(sortedScores);
            System.out.println(student.getName() + "'s Quiz Scores in Ascending Order: " + sortedScores);
        }
    }

    public void printPartTimeStudents() {
        System.out.println("Part-Time Students:");
        for (Student student : students) {
            if (student instanceof PartTimeStudent) {
                student.printDetails();
            }
        }
    }

    public void printFullTimeStudentsExamScores() {
        System.out.println("Full-Time Students' Exam Scores:");
        for (Student student : students) {
            if (student instanceof FullTimeStudent) {
                ((FullTimeStudent) student).printExamScores();
            }
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create dummy quiz and exam scores
        List<Integer> quizScores1 = List.of(80, 85, 90, 95, 70, 75, 60, 85, 88, 90, 76, 82, 89, 91, 78);
        List<Integer> quizScores2 = List.of(70, 75, 80, 65, 85, 80, 90, 88, 89, 90, 84, 87, 85, 92, 95);

        // Create a session
        Session session = new Session();

        // Add students to the session
        session.addStudent(new PartTimeStudent("Alice", quizScores1));
        session.addStudent(new FullTimeStudent("Bob", quizScores2, 92, 88));

        // Call the public methods to test the functionality
        session.printQuizAverages();
        session.printSortedQuizScores();
        session.printPartTimeStudents();
        session.printFullTimeStudentsExamScores();
    }
}
