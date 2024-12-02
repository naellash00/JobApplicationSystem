package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Repository.JobApplicationRepository;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    //check user_id and job_post_id
    public Integer applyForJob(Integer user_id, Integer job_post_id) {
        Boolean validUser = false;
        Boolean validJobPost = false;
        Boolean jobApplicationExist = false;

        // check correct user id flag
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if (user_id.equals(userRepository.findAll().get(i).getId())) {
                validUser = true; // user exist
            }
        }

        //check correct job post flag
        for (int i = 0; i < jobPostRepository.findAll().size(); i++) {
            if (job_post_id.equals(jobPostRepository.findAll().get(i).getId())) {
                validJobPost = true;
            }
        }

        if (validUser && validJobPost) {
            for (JobApplication ja : jobApplicationRepository.findAll()) {
                // get the job application with the correct IDs
                if (ja.getUser_id().equals(user_id) && ja.getJob_post_id().equals(job_post_id)) {
                    jobApplicationExist = true;
                    break;
                    //return 4; // job application exist
                }
            }
        }
        if (!validUser)
            return 1; // incorrect user id
        else if (!validJobPost)
            return 2; // incorrect job post id
        else if (!validUser && !validJobPost)
            return 3; //incorrect job post id and user id
        else if (jobApplicationExist)
            return 4; // job application exist

        if (!jobApplicationExist) { // is there is no job application with these IDs
            // create a new one and add to it the IDs
            JobApplication newJobApplication = new JobApplication();
            newJobApplication.setUser_id(user_id);
            newJobApplication.setJob_post_id(job_post_id);
            jobApplicationRepository.save(newJobApplication);
        }
        return 0; // everything correct
    }

    public Boolean withdrawJobApplication(Integer id){
        // check if this job application exist
        JobApplication jobApplication = jobApplicationRepository.getById(id);
        if(jobApplication==null){
            return false; // id not found
        }
        jobApplicationRepository.delete(jobApplication);
        return true;
    }
}
