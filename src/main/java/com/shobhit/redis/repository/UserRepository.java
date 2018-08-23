package com.shobhit.redis.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.shobhit.redis.bean.User;

@Repository
public class UserRepository {
	private static final String KEY = "USER";

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	public void saveUser(User user) {
		this.redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
	}

	public List<User> findUsers() {
		List<User> users = this.redisTemplate.opsForHash().entries(KEY).values()
				.stream()
				.map(user -> (User) user)
				.collect(Collectors.toList());

		return users;
	}

	public User findUserById(int userId) {
		Optional<User> optional = Optional.ofNullable((User) this.redisTemplate.opsForHash().get(KEY, userId));

		return optional.orElse(new User(0, "NULL", "NULL"));
	}

	public void updateUser(User user) {
		this.redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
	}

	public void deleteUser(int userId) {
		this.redisTemplate.opsForHash().delete(KEY, userId);
	}
}