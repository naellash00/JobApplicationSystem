package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.getById(id);
        if(oldJobPost==null){
            return false; // not found
        }
        // set to the new values
        oldJobPost.setPostingDate(jobPost.getPostingDate());
        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setDescription(jobPost.getDescription());
        jobPostRepository.save(oldJobPost);
       // System.out.println(oldJobPost.getTitle());
        return true;
    }

    public Boolean deleteJobPost(Integer id){
        // check if id exist
        JobPost jobPost = jobPostRepository.getById(id);
        if(jobPost==null){
            return false;
        }
        jobPostRepository.delete(jobPost);
        return true;
    }
}

















