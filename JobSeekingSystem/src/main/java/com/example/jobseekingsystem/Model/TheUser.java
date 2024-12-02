package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
//@Check(constraints = "length(name)>4")
//@Check(constraints = "age > 21")
//@Check(constraints = "role = 'job seeker' or role = 'employer'")
@Check(constraints = "length(name) > 4 and age > 21 and (role = 'job seeker' or role = 'employer')")
public class TheUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4, message = "name cannot be less than 4")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "name must contains characters only")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @Email(message = "enter a valid email")
    @Column(columnDefinition = "varchar(30) unique")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String password;

    @NotNull(message = "age cannot be empty")
    @Min(value = 22, message = "age cannot be less than 21")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(job seeker|employer)$", message = "role must be either job seeker or employer ")
    private String role;

}
