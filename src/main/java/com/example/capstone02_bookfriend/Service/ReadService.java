package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Repository.ReadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReadService {
    private final ReadRepository readRepository;
}
