package com.crypto.dashboard.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema="cryptodash", name="userlogin")
public class UserLogin {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String passsword;
	
	@Column(name="loggedin")
	private boolean loggedIn;
}
