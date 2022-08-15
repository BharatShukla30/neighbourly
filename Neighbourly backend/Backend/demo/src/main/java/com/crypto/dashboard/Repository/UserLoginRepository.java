package com.crypto.dashboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.dashboard.Entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String>{
	UserLogin findByUsername(String userName);
}
