package com.crypto.dashboard.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "${allowed.cors.origins}", allowedHeaders = "${allowed.cors.headers}")
@RequestMapping("/location")
@RestController
public class GeolocationController {

    @GetMapping(value="/getuserip")
    public ResponseEntity<Object> getUserData(@RequestParam("username") String userName, HttpServletRequest request){
        String ipAddr = (request.getRemoteAddr());
        System.out.print(ipAddr);
		return new ResponseEntity<>(ipAddr, HttpStatus.ACCEPTED);
        
    }
		 
}
