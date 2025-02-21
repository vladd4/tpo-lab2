import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class StudentGroup {
    private final String groupName;
    private final List<Student> students;
    private final ReentrantLock lock;

    public StudentGroup(String groupName, List<String> studentNames) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
        this.lock = new ReentrantLock();

        for (String name : studentNames) {
            students.add(new Student(name));
        }
    }

    public String getGroupName() {
        return groupName;
    }

    public Student getStudent(int index) {
        lock.lock();
        try {
            return students.get(index);
        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        return students.size();
    }

    public void printGroupReport() {
        lock.lock();
        try {
            System.out.println("\nGroup: " + groupName);
            System.out.println("----------------------------------------");
            for (Student student : students) {
                System.out.printf("%-20s Average: %.2f Grades: %s%n",
                        student.getName(),
                        student.getAverageGrade(),
                        student.getGrades());
            }
            System.out.println("----------------------------------------");
        } finally {
            lock.unlock();
        }
    }
}
