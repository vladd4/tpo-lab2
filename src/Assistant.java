import java.util.List;

class Assistant extends Teacher {
    private final int assistantId;

    public Assistant(int id, List<StudentGroup> groups, int weeksToGrade) {
        super("Assistant " + id, groups, weeksToGrade);
        this.assistantId = id;
    }

    @Override
    public void run() {
        for (int week = 1; week <= weeksToGrade; week++) {
            StudentGroup group = groups.get(assistantId - 1);

                int studentIndex = random.nextInt(group.getSize());
                int grade = random.nextInt(41) + 60;
                group.getStudent(studentIndex).addGrade(grade);

                System.out.printf("%s graded student %s from group %s with %d (Week %d)%n",
                        name,
                        group.getStudent(studentIndex).getName(),
                        group.getGroupName(),
                        grade,
                        week);

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}