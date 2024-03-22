package service;

import model.Group;

import java.util.List;

public interface GroupService {
    Group addNewGroup(Group group);
    Group getGroupByName(String groupName);
    String updateGroupName(String oldGroupName, String newGroupName);
    List<Group> getAllGroups();
    String deleteGroupByName(String groupName);
}
