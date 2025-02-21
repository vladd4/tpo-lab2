import java.util.List;

class Lecturer extends Teacher {
    public Lecturer(List<StudentGroup> groups, int weeksToGrade) {
        super("Lecturer", groups, weeksToGrade);
    }

    @Override
    public void run() {
        for (int week = 1; week <= weeksToGrade; week++) {
            for (StudentGroup group : groups) {
                int studentIndex = random.nextInt(group.getSize());
                int grade = random.nextInt(41) + 60;
                group.getStudent(studentIndex).addGrade(grade);

                System.out.printf("%s graded student %s from group %s with %d (Week %d)%n",
                        name,
                        group.getStudent(studentIndex).getName(),
                        group.getGroupName(),
                        grade,
                        week);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}