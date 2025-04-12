package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Model.Groups;
import com.example.capstone02_bookfriend.Model.User;
import com.example.capstone02_bookfriend.Repository.GroupRepository;
import com.example.capstone02_bookfriend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Boolean addUser(User user) {
        User checkEmail = userRepository.findUserByEmail(user.getEmail());
        if (checkEmail != null)
            return false;
        userRepository.save(user);
        return true;
    }

    public Boolean updateUser(Integer id, User user) {
        User checkEmail = userRepository.findUserByEmail(user.getEmail());
        User oldUser = userRepository.findUserById(id);
        if (checkEmail != null || oldUser == null)
            return false;

        oldUser.setBalance(user.getBalance());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null)
            return false;
        userRepository.delete(user);
        return true;
    }

//    Groups groups = groupRepository.findGroupById(user.getGroup_id());
//        if (groups.getMax_capacity()== groups.getNumber_of_users())
//                return false;
//        groups.setNumber_of_users(groups.getNumber_of_users()+1);
}
