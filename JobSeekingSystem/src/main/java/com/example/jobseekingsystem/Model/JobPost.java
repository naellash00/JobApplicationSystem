package com.example.jobseekingsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "length(title) > 4 and salary >= 0")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title cannot be empty")
    @Size(min = 5, message = "title must be more than 4 letters")
    @Column(columnDefinition = "varchar(15) not null")
    private String title;

    @NotEmpty(message = "description cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String description;

    @NotEmpty(message = "location cannot be empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String location;

    @NotNull(message = "salary cannot be empty")
    @Min(value = 0, message = "salary must be positive")
    @Column(columnDefinition = "int not null")
    private Integer salary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date" )
    private LocalDate postingDate;
}