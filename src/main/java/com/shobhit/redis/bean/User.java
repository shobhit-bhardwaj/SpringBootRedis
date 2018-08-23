package com.shobhit.redis.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userId;
	private String userName;
	private String password;

	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
}