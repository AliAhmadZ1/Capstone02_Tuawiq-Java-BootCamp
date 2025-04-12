package com.example.capstone02_bookfriend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name cannot be empty")
    @Column(columnDefinition = "varchar(20)")
    private String name;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(columnDefinition = "varchar(30) not null")
    @Check(constraints = "email like '%_@__%.__%'")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8,max = 16,message = "password should be in length 8-16 characters")
    @Column(columnDefinition = "varchar(16) not null")
    @Check(constraints = "length(password)>=8 and length(password)<=16")
    private String password;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(user|admin)$",message = "role should be ('user' or 'admin')")
    @Column(columnDefinition = "varchar(5) not null")
    @Check(constraints = "role='user' or role='admin'")
    private String role;
    @NotNull(message = "balance cannot be null")
    @Column(columnDefinition = "double not null")
    private Double balance=0.0;
    @Column(columnDefinition = "int")
    private Integer group_id;
    
}
