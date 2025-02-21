import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<StudentGroup> groups = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            List<String> studentNames = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                studentNames.add(String.format("Student %d-%d", i, j));
            }
            groups.add(new StudentGroup("Group " + i, studentNames));
        }

        int weeksToGrade = 4;

        Thread lecturerThread = new Thread(new Lecturer(groups, weeksToGrade));
        Thread assistant1Thread = new Thread(new Assistant(1, groups, weeksToGrade));
        Thread assistant2Thread = new Thread(new Assistant(2, groups, weeksToGrade));
        Thread assistant3Thread = new Thread(new Assistant(3, groups, weeksToGrade));

        lecturerThread.start();
        assistant1Thread.start();
        assistant2Thread.start();
        assistant3Thread.start();

        try {
            lecturerThread.join();
            assistant1Thread.join();
            assistant2Thread.join();
            assistant3Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Final Report ===");
        for (StudentGroup group : groups) {
            group.printGroupReport();
        }
    }
}