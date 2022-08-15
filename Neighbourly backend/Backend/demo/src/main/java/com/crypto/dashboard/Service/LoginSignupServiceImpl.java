package com.crypto.dashboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crypto.dashboard.Entity.UserDetails;
import com.crypto.dashboard.Entity.UserLogin;
import com.crypto.dashboard.Repository.UserDetailsRepository;
import com.crypto.dashboard.Repository.UserLoginRepository;
import com.crypto.dashboard.dto.ErrorMessageConstants;


@Component
@Service
public class LoginSignupServiceImpl implements LoginSignupService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepo;
	
	@Autowired
	private UserLoginRepository userLoginRepo;

	@Override
	public String saveUserDetails(UserDetails ud, UserLogin ul) {
		try{
			userDetailsRepo.save(ud);
			userLoginRepo.save(ul);
			return ErrorMessageConstants.DETAILS_SAVED;
		}
		catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}

	@Override
	public UserDetails getUserDetails(String userName) {
		return userDetailsRepo.findByUsername(userName);
	}
	
	//if username matches any user in db then return the object of userlogin else return null
	@Override
	public UserLogin CheckEmail(String userName) {
		UserLogin ul = userLoginRepo.findByUsername(userName);
		
		return ul;
	}
	
	
}
