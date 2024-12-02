package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "user id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotNull(message = "job post id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer job_post_id;
}
