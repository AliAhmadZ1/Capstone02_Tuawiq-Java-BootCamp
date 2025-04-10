package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
}
