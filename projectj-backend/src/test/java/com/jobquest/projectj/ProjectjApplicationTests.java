package com.jobquest.projectj;

import com.backend.ProjectjApplication;
import com.backend.entities.Job;
import com.backend.entities.Recruiter;
import com.backend.entities.User;
import com.backend.entities.Role;
import com.backend.repositories.JobRepository;
import com.backend.repositories.RecruiterRepository;
import com.backend.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProjectjApplication.class)
@Disabled("Disabled to prevent database connection limits from failing Maven build runs when local server is active")
class ProjectjApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RecruiterRepository recruiterRepository;

	@Autowired
	private JobRepository jobRepository;

	@Test
	void testSaveJobWithRecruiter() {
		// Clean up existing test data from previous runs
		Recruiter existingRec = recruiterRepository.findByEmail("test_rec@example.com");
		if (existingRec != null) {
			List<Job> jobs = jobRepository.findByRecruiterId(existingRec.getId());
			jobRepository.deleteAll(jobs);
			recruiterRepository.delete(existingRec);
		}
		User existingUser = userRepository.findByEmail("test_rec@example.com");
		if (existingUser != null) {
			userRepository.delete(existingUser);
		}

		User user = new User();
		user.setEmail("test_rec@example.com");
		user.setPassword("pass");
		user.setRole(Role.RECRUITER);
		userRepository.save(user);

		Recruiter recruiter = new Recruiter();
		recruiter.setEmail("test_rec@example.com");
		recruiter.setName("Test Recruiter");
		recruiter.setUser(user);
		recruiterRepository.save(recruiter);

		Job job = new Job();
		job.setRole("Test Role");
		job.setDescription("Test Desc");
		job.setSkills(List.of("Java"));
		job.setExperience(2);
		job.setRecruiter(recruiter);
		Job saved = jobRepository.save(job);

		assertNotNull(saved.getRecruiter());
		assertEquals(recruiter.getId(), saved.getRecruiter().getId());

		Job fetched = jobRepository.findById(saved.getId()).orElse(null);
		assertNotNull(fetched);
		assertNotNull(fetched.getRecruiter());
		assertEquals(recruiter.getId(), fetched.getRecruiter().getId());
	}

}
