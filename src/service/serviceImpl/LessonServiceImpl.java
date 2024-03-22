package service.serviceImpl;

import database.CustomException;
import database.Database;
import model.Group;
import model.Lesson;
import model.Student;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    @Override
    public String addNewLessonToGroup(String groupName, Lesson lesson) {
        try {
            if (!Database.groups.isEmpty()) {
                for (Group group : Database.groups) {
                    if (group.getGroupName().equalsIgnoreCase(groupName)) {
                        group.setLesson(lesson);
                        Database.lessons.add(lesson);
                        System.out.println("group.getStudents() = " + group.getStudents());
                        for (Student student : group.getStudents()) {
                            student.setLesson(lesson);
                        }
                        return "Lesson " + lesson.getLessonName() + " has been successfully added to the " + groupName + " group!";
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
    public Lesson getLessonByName(String lessonName) {
        try {
            for (Lesson lesson : Database.lessons) {
                if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                    return lesson;
                }
            }
            throw new CustomException("There is no such lesson!\nTry again");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllLessonByGroupName(String groupName) {
        try {
            if (!Database.groups.isEmpty()) {
                for (Group group : Database.groups) {
                    if (group.getGroupName().equalsIgnoreCase(groupName)) {
                        return group.getLessons();
                    }
                }
            } else {
                throw new CustomException("You don't have any groups yet!");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteLessonByName(String lessonName) {
        try {
            for (Group group : Database.groups) {
                group.getLessons().removeIf(lesson -> lesson.getLessonName().equalsIgnoreCase(lessonName));
            }
            for (Lesson lesson : Database.lessons) {
                if (lesson.getLessonName().equalsIgnoreCase(lessonName)) {
                    Database.lessons.remove(lesson);
                    return lessonName + " lesson successfully deleted!";
                }
            }
            throw new CustomException("There is no such lesson in this group!\nTry again");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}