package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Model.Groups;
import com.example.capstone02_bookfriend.Repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupsService {

    private final GroupRepository groupRepository;

    public List<Groups> getAllgroups() {
        return groupRepository.findAll();
    }

    public Boolean addGroup(Groups groups) {
        Groups existGroup = groupRepository.findGroupsByName(groups.getName());
        if (existGroup == null) {
            groupRepository.save(groups);
            return true;
        }
        return false;
    }

    public Boolean updateGroup(Integer id, Groups groups) {
        Groups oldGroup = groupRepository.findGroupById(id);
        Groups existGroup = groupRepository.findGroupsByName(groups.getName());
        if (oldGroup == null || existGroup != null)
            return false;

        oldGroup.setName(groups.getName());
        oldGroup.setUser_id(groups.getUser_id());
        oldGroup.setBook_id(groups.getBook_id());
        oldGroup.setMax_capacity(groups.getMax_capacity());
        oldGroup.setNumber_of_users(groups.getNumber_of_users());
        groupRepository.save(oldGroup);
        return true;
    }

    public Boolean deleteGroup(Integer id) {
        Groups groups = groupRepository.findGroupById(id);
        if (groups == null)
            return false;
        groupRepository.delete(groups);
        return true;
    }

}
