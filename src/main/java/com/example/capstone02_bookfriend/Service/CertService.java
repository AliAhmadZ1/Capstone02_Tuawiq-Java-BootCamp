package com.example.capstone02_bookfriend.Service;

import com.example.capstone02_bookfriend.Repository.CertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertService {
    private final CertRepository certRepository;
}
