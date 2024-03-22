package database;

public class GenerateId {
    public static Long personId = 0L;
    public static Long groupId = 0L;
    public static Long studentId = 0L;
    public static Long lessonId = 0L;
    public static long genPersonId() {
        return ++personId;
    }
    public static long genGroupId() {
        return ++groupId;
    }
    public static long genStudentId() {
        return ++studentId;
    }
    public static long genLessonId() {
        return ++lessonId;
    }
}
