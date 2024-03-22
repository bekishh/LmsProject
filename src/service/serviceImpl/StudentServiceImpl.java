package service.serviceImpl;

import database.CustomException;
import database.Database;
import model.Group;
import model.Lesson;
import model.Student;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public String addNewStudentToGroup(String groupName, Student student) {
        boolean isExists = false;
        try {
            if (!Database.groups.isEmpty()) {
                for (Group group : Database.groups) {
                    if (group.getGroupName().equalsIgnoreCase(groupName)) {
                        for (Student groupStudent : group.getStudents()) {
                            if (groupStudent.getEmail().equalsIgnoreCase(student.getEmail())) {
                                isExists = true;
                                break;
                            }
                        }
                        if (!isExists) {
                            for (Lesson lesson : group.getLessons()) {
                                student.setLesson(lesson);
                            }
                            group.setStudent(student);
                            Database.students.add(student);
                            return "Student " + student.getFirstName() + " " + student.getLastName() + " has been successfully added to the " + groupName + " group!";
                        }
                        throw new CustomException("The student is already in this group!");
                    }
                }
                throw new CustomException("There is no such group in database!\nTry again");
            } else {
                throw new CustomException("You don't have any groups yet!");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Student updateStudent(String oldStudentEmail, Student newStudent) {
        boolean isStudentExists = false;
        try {
            if (!Database.groups.isEmpty()) {
                for (Group group : Database.groups) {
                    for (int i = 0; i < group.getStudents().size(); i++) {
                        Student student = group.getStudents().get(i);
                        if (student.getEmail().equalsIgnoreCase(oldStudentEmail)) {
                            newStudent.setId(student.getId());
                            group.getStudents().set(i, newStudent);
                            isStudentExists = true;
                        }
                    }
                }
                if (isStudentExists) {
                    for (int i = 0; i < Database.students.size(); i++) {
                        if (Database.students.get(i).getEmail().equalsIgnoreCase(oldStudentEmail)) {
                            System.out.println("Student successfully updated!");
                            newStudent.setId(Database.students.get(i).getId());
                            Database.students.set(i, newStudent);

                            return Database.students.get(i);
                        }
                    }
                }
                throw new CustomException("Student not found!");
            } else {
                throw new CustomException("You don't have any groups and students yet!");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Student findStudentByFirstName(String firstName) {
        try {
            for (Student student : Database.students) {
                if (student.getFirstName().equalsIgnoreCase(firstName)) {
                    return student;
                }
            }
            throw new CustomException("Student not found!");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudentsByGroupName(String groupName) {
        try {
            if (!Database.groups.isEmpty()) {
                for (Group group : Database.groups) {
                    if (group.getGroupName().equalsIgnoreCase(groupName)) {
                        return group.getStudents();
                    }
                }
                throw new CustomException("There is no such group in database!\nTry again");
            } else {
                throw new CustomException("You don't have any groups yet!");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllStudentLesson(String studentEmail) {
        try {
            for (Student student : Database.students) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                    return student.getLessons();
                }
            }
            throw new CustomException("Student not found!");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteStudentByEmail(String studentEmail) {
        try {
            for (Student student : Database.students) {
                if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                    Database.students.remove(student);
                    return "Student successfully deleted!";
                }
            }
            throw new CustomException("Student not found!");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}