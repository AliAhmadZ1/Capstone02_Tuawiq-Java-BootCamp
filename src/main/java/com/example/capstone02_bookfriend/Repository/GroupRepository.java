package com.example.capstone02_bookfriend.Repository;

import com.example.capstone02_bookfriend.Model.Groups;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Groups,Integer> {

    Groups findGroupById(Integer id);

    Groups findGroupsByName(String name);

//    @Query("select g from Groups g where g.id=?1")
//    Groups findGroupsByMax_capacity(Integer id);

}
