package com.example.capstone02_bookfriend.Repository;

import com.example.capstone02_bookfriend.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
}
