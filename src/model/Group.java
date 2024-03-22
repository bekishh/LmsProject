package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Long id;
    private String groupName;
    private String description;
    private List<Lesson> lessons;
    private List<Student> students;

    public Group() {
    }

    public Group(Long id, String groupName, String description, List<Lesson> lessons, List<Student> students) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.lessons = lessons;
        this.students = students;
    }
    public Group(Long id, String groupName, String description) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudent(Student student) {
        if(students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }

    public void setLesson(Lesson lesson) {
        if (lessons== null) {
            lessons=new ArrayList<>();
        }
        lessons.add(lesson);
    }

    public void deleteLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    @Override
    public String toString() {
        return "\nGroup:" +
                "\nid=" + id +
                "\ngroupName='" + groupName +
                "\ndescription='" + description +
                "\nlessons=" + lessons +
                "\nstudents=" + students + "\n";
    }
}
