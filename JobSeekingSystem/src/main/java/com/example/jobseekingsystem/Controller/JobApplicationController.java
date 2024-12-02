package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.ApiResponse.ApiResponse;
import com.example.jobseekingsystem.Service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job/application")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplications(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/apply/{userid}/{jobpostid}")
    public ResponseEntity applyForJob(@PathVariable Integer userid, @PathVariable Integer jobpostid){
       Integer applyingResult =  jobApplicationService.applyForJob(userid, jobpostid);
       if(applyingResult == 1){
           return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
       } else if(applyingResult == 2){
           return ResponseEntity.status(400).body(new ApiResponse("job post id not found"));
       } else if(applyingResult == 3){
           return ResponseEntity.status(400).body(new ApiResponse("user id and job post id not found"));
       } else if(applyingResult == 4){
           return ResponseEntity.status(400).body(new ApiResponse("this job application already exist"));
       }
        return ResponseEntity.status(200).body(new ApiResponse("applied for job successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity withdrawJobApplication(@PathVariable Integer id){
        Boolean isWithdraw = jobApplicationService.withdrawJobApplication(id);
        if(isWithdraw){
            return ResponseEntity.status(200).body(new ApiResponse("job application withdrawn successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job application not found"));
    }

}
