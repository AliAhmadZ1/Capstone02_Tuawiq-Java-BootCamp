package com.example.capstone02_bookfriend.Repository;

import com.example.capstone02_bookfriend.Model.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<Join,Integer> {

}
