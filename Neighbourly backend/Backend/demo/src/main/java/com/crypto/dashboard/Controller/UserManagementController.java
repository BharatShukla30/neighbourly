package com.crypto.dashboard.Controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.dashboard.Entity.UserDetails;
import com.crypto.dashboard.Entity.UserLogin;
import com.crypto.dashboard.Service.LoginSignupService;
import com.crypto.dashboard.Service.Message;
import com.crypto.dashboard.dto.ErrorMessageConstants;
import com.crypto.dashboard.dto.LoginInfo;
import com.crypto.dashboard.dto.UserInfo;


@CrossOrigin(origins = "${allowed.cors.origins}", allowedHeaders = "${allowed.cors.headers}")
@RequestMapping("/user-data")
@RestController
public class UserManagementController {
	
	@Autowired 
	LoginSignupService loginSignupService ;
	
	 @GetMapping(value="/getuser")
	public ResponseEntity<Object> getUserData(@RequestParam("username") String userName){
		UserDetails userDetailsRow = loginSignupService.getUserDetails(userName);
		return new ResponseEntity<>(userDetailsRow, HttpStatus.ACCEPTED);
	}
	@PostMapping(value="/saveuser")
	public ResponseEntity<Object> setUserData(@RequestBody UserInfo userInfo){
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail(userInfo.getEmail());
		userDetails.setFirstname(userInfo.getFirstname());
		userDetails.setLastname(userInfo.getLastname());
		userDetails.setUsername(userInfo.getUsername());
		
		UserLogin userLogin = new UserLogin();
		String password = BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt());
		userLogin.setUsername(userInfo.getUsername());
		userLogin.setPasssword(password);
		userLogin.setLoggedIn(false); 
		
		String msg = loginSignupService.saveUserDetails(userDetails, userLogin);
		
		return new ResponseEntity<>(new Message(msg), HttpStatus.ACCEPTED);
	}
	@PostMapping(value="/login")
	public ResponseEntity<Object> verifyLogin(@RequestBody LoginInfo logininfo){
		UserLogin userLogin = loginSignupService.CheckEmail(logininfo.getUserName());
		if(userLogin != null)
		{
			if(BCrypt.checkpw(logininfo.getUserPassword(), userLogin.getPasssword()))
			{
				return new ResponseEntity<>(new Message(ErrorMessageConstants.LOGIN_SUCCESSFUL),HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>(new Message(ErrorMessageConstants.INVALID_PASSWORD),HttpStatus.UNAUTHORIZED);
			}
		}
		else
		{
			return new ResponseEntity<>(new Message(ErrorMessageConstants.INVALID_USERNAME),HttpStatus.UNAUTHORIZED);
		}
		
		
	}
	
	
	
	
}
