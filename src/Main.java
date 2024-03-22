import database.CustomException;
import database.Database;
import database.GenerateId;
import enums.Gender;
import model.Group;
import model.Lesson;
import model.Person;
import model.Student;
import service.serviceImpl.GroupServiceImpl;
import service.serviceImpl.LessonServiceImpl;
import service.serviceImpl.StudentServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerNum = new Scanner(System.in);
        Scanner scannerLn = new Scanner(System.in);

        GroupServiceImpl groupService = new GroupServiceImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        LessonServiceImpl lessonService = new LessonServiceImpl();

        Person admin = new Person(GenerateId.genPersonId(), "Admin", "Admin", "admin@gmail.com", "Admin123", Gender.MALE);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String wish;

        if (time.getHour() > 6 && time.getHour() <= 12) {
            wish = "Good morning";
        } else if (time.getHour() > 12 && time.getHour() <= 18) {
            wish = "Good afternoon";
        } else if (time.getHour() > 18 && time.getHour() <= 21) {
            wish = "Good evening";
        } else {
            wish = "Good night";
        }

        System.out.println(date + "\n" + wish + ". Time is -> " + time.getHour() + ":" + time.getMinute());

        while (true) {
            System.out.println("""
                    1) I have an account
                    2) I forget my password and want to change
                    """);

            try {
                switch (scannerNum.nextInt()) {
                    case 1:
                        while (true) {
                            try {
                                while (true) {
                                    int command = 0;
                                    System.out.println("Write email: ");
                                    String authEmail = scannerLn.nextLine();

                                    System.out.println("Write password: ");
                                    String authPassword = scannerLn.nextLine();

                                    if (admin.getEmail().equalsIgnoreCase(authEmail) && admin.getPassword().equalsIgnoreCase(authPassword)) {
                                        while (command != 16) {

                                            System.out.println("""
                                                    ---------------------------------------------------------------------------------------
                                                    -----------------------------------Choose operation------------------------------------
                                                    ---------------------------------------------------------------------------------------
                                                    1 -> Add new group                              9  -> Get all student's lesson
                                                    2 -> Get group by name                          10 -> Delete student
                                                    3 -> Update group name                          11 -> Add new lesson to group
                                                    4 -> Get all groups                             12 -> Get lesson by name
                                                    5 -> Add new student to group                   13 -> Get all lesson by group name
                                                    6 -> Update student                             14 -> Delete lesson
                                                    7 -> Find student by first name                 15 -> Delete group
                                                    8 -> Get all students by group name             16 -> Exit
                                                    """);

                                            try {
                                                switch (command = scannerNum.nextInt()) {
                                                    case 1 -> {
                                                        System.out.println("Write group name: ");
                                                        String groupName = scannerLn.nextLine();

                                                        System.out.println("Write group description: ");
                                                        String description = scannerLn.nextLine();

                                                        Group group = new Group(GenerateId.genGroupId(), groupName, description, new ArrayList<>(), new ArrayList<>());
                                                        System.out.println(groupService.addNewGroup(group));
                                                    }
                                                    case 2 -> {
                                                        System.out.println("Write group name: ");
                                                        String groupName = scannerLn.nextLine();

                                                        System.out.println(groupService.getGroupByName(groupName));
                                                    }
                                                    case 3 -> {
                                                        System.out.println("Write the name of the group you want to update: ");
                                                        String oldGroupName = scannerLn.nextLine();

                                                        System.out.println("Write new group name: ");
                                                        String newGroupName = scannerLn.nextLine();

                                                        System.out.println(groupService.updateGroupName(oldGroupName, newGroupName));
                                                    }
                                                    case 4 -> System.out.println(groupService.getAllGroups());
                                                    case 5 -> {
                                                        if (!Database.groups.isEmpty()) {
                                                            System.out.println("Write group name: ");
                                                            String groupName = scannerLn.nextLine();

                                                            System.out.println("Write student's first name: ");
                                                            String firstName = scannerLn.nextLine();

                                                            System.out.println("Write student's last name: ");
                                                            String lastName = scannerLn.nextLine();

                                                            System.out.println("Write student's email: ");
                                                            String studentEmail = scannerLn.nextLine();

                                                            if (!studentEmail.contains("@") && !studentEmail.contains(".")) {
                                                                throw new CustomException("Email should contain '@' and 'dot'.\nTry again");
                                                            }

                                                            System.out.println("Create a new password with the length at least of 8 symbols");
                                                            System.out.println("Write student's password: ");
                                                            String studentPassword = scannerLn.nextLine();

                                                            if (studentPassword.length() < 8) {
                                                                throw new CustomException("Password should consist of at least 8 symbols");
                                                            }

                                                            System.out.println("Write student's gender: ");
                                                            String studentGender = scannerLn.nextLine();

                                                            if (studentGender.equalsIgnoreCase(Gender.MALE.name())) {
                                                                studentGender = Gender.MALE.name();
                                                            } else if (studentGender.equalsIgnoreCase(Gender.FEMALE.name())) {
                                                                studentGender = Gender.FEMALE.name();
                                                            } else {
                                                                throw new CustomException("Wrong gender!");
                                                            }

                                                            Student student = new Student(GenerateId.genGroupId(), firstName, lastName, studentEmail, studentPassword, studentGender, new ArrayList<>());
                                                            System.out.println(studentService.addNewStudentToGroup(groupName, student));
                                                        } else {
                                                            System.out.println("You don't have any groups yet!");
                                                        }
                                                    }
                                                    case 6 -> {
                                                        System.out.println("Write old student email: ");
                                                        String oldStudentEmail = scannerLn.nextLine();

                                                        System.out.println("Write new student first name: ");
                                                        String newStudentFirstName = scannerLn.nextLine();

                                                        if (newStudentFirstName.isEmpty()) {
                                                            throw new CustomException("Name can't be empty!\nTry again");
                                                        }

                                                        System.out.println("Write new student last name: ");
                                                        String newStudentLastName = scannerLn.nextLine();

                                                        if (newStudentLastName.isEmpty()) {
                                                            throw new CustomException("Name can't be empty!\nTry again");
                                                        }

                                                        System.out.println("Write new student's email");
                                                        String newStudentEmail = scannerLn.nextLine();
                                                        if (newStudentEmail.isEmpty()) {
                                                            throw new CustomException("Email can't be empty.\nTry again");
                                                        } else if (!newStudentEmail.contains("@")) {
                                                            throw new CustomException("Email should contain '@'.\nTry again");
                                                        }
                                                        System.out.println("Create a password for a new student");
                                                        String newStudentPassword = scannerLn.nextLine();
                                                        if (newStudentPassword.length() < 7) {
                                                            throw new CustomException("Password should consist of at least 7 symbols");
                                                        }
                                                        System.out.println("Write new student's gender");
                                                        String newStudentGender = scannerLn.nextLine();
                                                        if (newStudentGender.equalsIgnoreCase(Gender.FEMALE.name())) {
                                                            newStudentGender = String.valueOf(Gender.FEMALE);
                                                        } else if (newStudentGender.equalsIgnoreCase(Gender.MALE.name())) {
                                                            newStudentGender = String.valueOf(Gender.MALE);
                                                        } else {
                                                            throw new CustomException("Error gender\nTry again");
                                                        }

                                                        Student newStudent = new Student(newStudentFirstName, newStudentLastName, newStudentEmail, newStudentPassword, newStudentGender);
                                                        System.out.println(studentService.updateStudent(oldStudentEmail, newStudent));
                                                    }
                                                    case 7 -> {
                                                        System.out.println("Write the name of the student you want to find: ");
                                                        String studentFirstName = scannerLn.nextLine();

                                                        System.out.println(studentService.findStudentByFirstName(studentFirstName));
                                                    }
                                                    case 8 -> {
                                                        System.out.println("Write group name: ");
                                                        String groupName = scannerLn.nextLine();

                                                        System.out.println(studentService.getAllStudentsByGroupName(groupName));
                                                    }
                                                    case 9 -> {
                                                        System.out.println("Write student's email: ");
                                                        String studentEmail = scannerLn.nextLine();

                                                        System.out.println(studentService.getAllStudentLesson(studentEmail));
                                                    }
                                                    case 10 -> {
                                                        System.out.println("Write student's email: ");
                                                        String studentEmail = scannerLn.nextLine();

                                                        System.out.println(studentService.deleteStudentByEmail(studentEmail));
                                                    }
                                                    case 11 -> {
                                                        System.out.println("Write the name of the group you want to add lesson: ");
                                                        String groupName = scannerLn.nextLine();

                                                        System.out.println("Write lesson's name: ");
                                                        String lessonName = scannerLn.nextLine();

                                                        System.out.println("Write lesson's description");
                                                        String description = scannerLn.nextLine();

                                                        Lesson lesson = new Lesson(GenerateId.genLessonId(), lessonName, description);
                                                        System.out.println(lessonService.addNewLessonToGroup(groupName, lesson));
                                                    }
                                                    case 12 -> {
                                                        if (!Database.lessons.isEmpty()) {
                                                            System.out.println("Write the name of the lesson you want to receive: ");
                                                            String lessonName = scannerLn.nextLine();

                                                            System.out.println(lessonService.getLessonByName(lessonName));
                                                        } else {
                                                            throw new CustomException("You don't have any lessons yet!");
                                                        }
                                                    }
                                                    case 13 -> {
                                                        System.out.println("Write the group from which lessons you want to get: ");
                                                        String groupName = scannerLn.nextLine();

                                                        System.out.println(lessonService.getAllLessonByGroupName(groupName));
                                                    }
                                                    case 14 -> {
                                                        System.out.println("Write the title of the lesson you want to delete: ");
                                                        String lessonName = scannerLn.nextLine();

                                                        System.out.println(lessonService.deleteLessonByName(lessonName));
                                                    }
                                                    case 15 -> {
                                                        System.out.println("Write the name of the group you want to delete: ");
                                                        String groupName = scannerLn.nextLine();

                                                        System.out.println(groupService.deleteGroupByName(groupName));
                                                    }
                                                    case 16 -> {
                                                        System.out.println("Exit completed successfully!");
                                                    }
                                                }
                                            } catch (CustomException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    } else {
                                        throw new CustomException("Wrong email or password input. \nTry again");
                                    }
                                }
                            } catch (CustomException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    case 2:
                        System.out.println("Write your email");
                        String emailScan = scannerLn.nextLine();
                        System.out.println("Create a new password(at least 7symbols)");
                        String passwordScan = scannerLn.nextLine();
                        try {
                            if (emailScan.equalsIgnoreCase(admin.getEmail()) && passwordScan.length() > 7) {
                                System.out.println("Successfully changed password");
                                admin.setPassword(passwordScan);
                                System.out.println(admin);
                            } else {
                                throw new CustomException("Wrong password creating");
                            }
                        } catch (CustomException e) {
                            System.out.println(e.getMessage());
                        }
                    default:
                        System.out.println("Available numbers 1-2 only");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input only numbers");
                break;
            }
        }
    }
}

