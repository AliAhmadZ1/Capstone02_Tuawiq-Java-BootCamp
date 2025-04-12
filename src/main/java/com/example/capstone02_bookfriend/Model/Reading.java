package com.example.capstone02_bookfriend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "state cannot be empty")
    @Pattern(regexp = "^(done|still|interviewed)$",message = "state should be (done | still | interviewed)")
    @Column(columnDefinition = "varchar(12) not null")
    @Check(constraints = "state='done' or state='still' or state='interviewed'")
    private String state; // done | still | interviewed
    @NotNull(message = "book id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer book_id;
    @NotNull(message = "user id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

}
