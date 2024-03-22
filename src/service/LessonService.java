package service;

import model.Group;
import model.Lesson;

import java.util.List;

public interface LessonService {
    String addNewLessonToGroup(String groupName, Lesson lesson);
    Lesson getLessonByName(String lessonName);
    List<Lesson> getAllLessonByGroupName(String groupName);
    String deleteLessonByName(String lessonName);
}
