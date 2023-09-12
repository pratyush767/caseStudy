package com.casestudy.LoginAndRegistration.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.casestudy.LoginAndRegistration.model.User;
import com.casestudy.LoginAndRegistration.Exception.ResourceNotFoundException;
import com.casestudy.LoginAndRegistration.Repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	    private UserRepo repo;
	 @Autowired
	 	private RestTemplate restTemplate;

	 	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	 			
	    public User createUser(User users) {
	    	
	        return repo.save(users);
	    }
	    //Update the user
	    public User update(User user , Integer userId) {
	    	
	    	User user1 = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userId));
	    	
	    	user1.setUserName(user.getUserName());
	    	user1.setEmail(user.getEmail());
	    	user1.setPassword(user.getPassword());
	    	user1.setMobile(user.getMobile());
	    	
	    	User updatedUser = this.repo.save(user1);
	    	return updatedUser;
	    }
	    //get all user
	    public List<User> listAll() {

	        return this.repo.findAll();
	    }

	    //delete the user
	    public void deleteUser(Integer userId) {
	    	
	    	User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userId));
	        repo.delete(user);
	    }
	    //get Single User
		@Override
		public User getUserByID(Integer userId) {
			//get user from database with the help of user Repository
			User user  = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id" ,userId));
			//fetch reward points of the above user from REWARD POINT SERVICE
			Map<String, Integer> forObject = restTemplate.getForObject("http://localhost:8080/getRewardPointsById/users/2", Map.class);
			logger.info("{}" , forObject);
			return user;
		}

		

		

		

}
