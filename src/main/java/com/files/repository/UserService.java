package com.files.repository;

import java.util.List;

import com.files.entities.User;

public interface UserService {

	/**
	 * Takes Invoice Object as input and returns PK generated
	 */
	User saveUser(User user);
	
	/**
	 * Takes existing Invoice data as input and updates values
	 */
	User updateuser(int id,User u);
	
	/**
	 * Takes PK(ID) as input and deletes Invoice Object data
	 */
	void deleteUser(int id);	
	
	
	/**
	 * select all rows and provides result as a List<Invoice>
	 */
	List<User> getAllUsers();
	
	
	User getSingleUser(int id); 
		
	
	
}
