package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
}
