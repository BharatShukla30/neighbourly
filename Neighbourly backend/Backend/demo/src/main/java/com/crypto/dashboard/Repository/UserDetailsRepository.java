package com.crypto.dashboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.dashboard.Entity.UserDetails;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String>{
	UserDetails findByUsername(String username);
}