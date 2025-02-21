import java.util.List;
import java.util.Random;

abstract class Teacher implements Runnable {
    protected final List<StudentGroup> groups;
    protected final Random random;
    protected final int weeksToGrade;
    protected final String name;

    public Teacher(String name, List<StudentGroup> groups, int weeksToGrade) {
        this.name = name;
        this.groups = groups;
        this.weeksToGrade = weeksToGrade;
        this.random = new Random();
    }
}