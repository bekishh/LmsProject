package service.serviceImpl;

import database.CustomException;
import database.Database;
import model.Group;
import service.GroupService;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    @Override
    public Group addNewGroup(Group group) {
        boolean isExists = false;

        for (Group group1 : Database.groups) {
            if (group.getGroupName().equalsIgnoreCase(group1.getGroupName())) {
                isExists = true;
                break;
            }
        }

        try {
            if (!isExists) {
                Database.groups.add(group);
                return group;
            } else {
                throw new CustomException("Such group name is already exist!\nTry again");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Group getGroupByName(String groupName) {
        try {
            if (!Database.groups.isEmpty()) {
                for (Group group : Database.groups) {
                    if (group.getGroupName().equalsIgnoreCase(groupName)) {
                        return group;
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
    public String updateGroupName(String oldGroupName, String newGroupName) {
        try {
            for (Group group : Database.groups) {
                if (group.getGroupName().equalsIgnoreCase(oldGroupName)) {
                    group.setGroupName(newGroupName);
                    return "Group name successfully updated!";
                }
            }
            throw new CustomException("There is no such group in database!\nTry again");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        try {
            if (!Database.groups.isEmpty()) {
                return Database.groups;
            } else {
                throw new CustomException("You don't have any groups yet!");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return Database.groups;
    }

    @Override
    public String deleteGroupByName(String groupName) {
        try {
            if (!Database.groups.isEmpty()) {
                try {
                    for (Group group : Database.groups) {
                        if (group.getGroupName().equalsIgnoreCase(groupName)) {
                            Database.groups.remove(group);
                            return groupName + " group successfully deleted!";
                        } else {
                            throw new CustomException("There is no such group in database!\nTry again");
                        }
                    }
                } catch (CustomException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new CustomException("You don't have any groups yet!");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public <E> void addNewGroup(long l, String groupName, String description, ArrayList<E> es, ArrayList<E> es1) {
    }
}