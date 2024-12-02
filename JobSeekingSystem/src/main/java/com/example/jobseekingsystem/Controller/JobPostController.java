package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.ApiResponse.ApiResponse;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job/post")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("job post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdated = jobPostService.updateJobPost(id, jobPost);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("job post updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job post not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        Boolean isDeleted = jobPostService.deleteJobPost(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("job post deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job post not found"));
    }

}









