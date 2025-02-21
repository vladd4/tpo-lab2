import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Student {
    private final String name;
    private final List<Integer> grades;
    private final ReentrantLock lock;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public void addGrade(int grade) {
        lock.lock();
        try {
            if (grade >= 0 && grade <= 100) {
                grades.add(grade);
            }
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        lock.lock();
        try {
            return new ArrayList<>(grades);
        } finally {
            lock.unlock();
        }
    }

    public double getAverageGrade() {
        lock.lock();
        try {
            if (grades.isEmpty()) return 0;
            return grades.stream().mapToInt(Integer::intValue).average().orElse(0);
        } finally {
            lock.unlock();
        }
    }
}