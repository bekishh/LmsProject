package service;

import model.Group;
import model.Lesson;
import model.Student;

import java.util.List;

public interface StudentService {
    String addNewStudentToGroup(String groupName, Student student);
    Student updateStudent(String oldStudentEmail, Student newStudent);
    Student findStudentByFirstName(String firstName);
    List<Student> getAllStudentsByGroupName(String groupName);
    List<Lesson> getAllStudentLesson(String studentEmail);
    String deleteStudentByEmail(String studentEmail);
}