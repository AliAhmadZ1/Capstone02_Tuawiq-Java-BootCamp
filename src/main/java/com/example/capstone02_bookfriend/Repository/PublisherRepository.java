package com.example.capstone02_bookfriend.Repository;

import com.example.capstone02_bookfriend.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Integer> {

}
