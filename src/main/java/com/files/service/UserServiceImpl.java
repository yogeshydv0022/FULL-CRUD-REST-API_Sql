package com.files.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.files.customException.UserNotFoundException;
import com.files.entities.User;
import com.files.repository.UserRepository;
import com.files.repository.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	// Save User
	@Override
	public User saveUser(User user) {
		User u = repo.save(user);
		return u;
	}

	// getOneUser
	@Override
	public User getSingleUser(int id) {
		User u = repo.findById(id).orElseThrow(() -> new UserNotFoundException(
				new StringBuffer().append("User '").append(id).append("'not exist").toString()));
		return u;
	}

//	// Update User
//	@Override
//	public User updateuser(int id, User user) {
//		User u = null;
//		u = repo.findById(id).get();
//		if (u != null) {
//			u.setId(id);
//			repo.save(u);
//		}
//		return u;
//	}

	
	@Override
	public User updateuser(int id, User user) {
		// Retrieve the existing entity from the database
		User u = repo.findById(id).orElseThrow(() -> new UserNotFoundException(
				new StringBuffer().append("User '").append(id).append("'not exist").toString()));

		// Update fields of the existing entity with the new values
		u.setName(user.getName());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setAddress(user.getAddress());
		User updatedUser = repo.save(u);
		// Save the updated entity back to the database
		return updatedUser;
	}

	// Delete UserbyId
	@Override
	public void deleteUser(int id) {
		User uid = getSingleUser(id);
		repo.delete(uid);
	}

	// get All User
	@Override
	public List<User> getAllUsers() {
		List<User> list = repo.findAll();
		return list;
	}

}
