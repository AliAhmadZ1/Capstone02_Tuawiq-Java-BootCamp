package com.example.capstone02_bookfriend.Repository;

import com.example.capstone02_bookfriend.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
