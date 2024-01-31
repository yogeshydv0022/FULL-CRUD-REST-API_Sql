package com.files.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.customException.UserNotFoundException;
import com.files.entities.User;
import com.files.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserServiceImpl service;

   //Add User	
	@PostMapping("/user")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		ResponseEntity<?> resp = null;
		try {
			User u = service.saveUser(user);
			resp = new ResponseEntity<String>("User '" + u.getId() + "' created", HttpStatus.CREATED);
			// 201-created
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to save User",
					HttpStatus.INTERNAL_SERVER_ERROR); // 500-Internal Server Error
		}
		return resp;
	}

	// Get AllUsers
	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		ResponseEntity<?> resp = null;
		try {
			List<User> list = service.getAllUsers();
			resp = new ResponseEntity<List<User>>(list, HttpStatus.FOUND);//200 OK
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to get users",
					HttpStatus.INTERNAL_SERVER_ERROR);// 500-Internal Server Error
		}
		return resp;
	}

	// Get Single User
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getOneUser(@PathVariable("id") int id) {
		ResponseEntity<?> resp = null;
		try {
			User user = service.getSingleUser(id);
			resp = new ResponseEntity<User>(user, HttpStatus.OK);//200 OK
		 
	}catch (UserNotFoundException unfe) {
		throw unfe;
	}catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to find user",
					HttpStatus.INTERNAL_SERVER_ERROR);// 404-NOT_FOUND
		}
		return resp;
	}

	// Update UserByID
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateInvoice(@PathVariable("id") int id, @RequestBody User user) {

		ResponseEntity<?> resp = null;
		try {
			User u = service.updateuser(id, user);
			resp = new ResponseEntity<String>("User Id: " + u.getId() + " Updated Successfull "+u, HttpStatus.OK);
		}catch (UserNotFoundException unfe) {
			throw unfe;																										

		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(" Unable to Update User",
					HttpStatus.INTERNAL_SERVER_ERROR); // 404-NOT_FOUND
		}
		return resp;
	}

	// Delete userById
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteOneUser(@PathVariable("id") int id) {
		ResponseEntity<?> resp = null;
		try {
			service.deleteUser(id);
			resp = new ResponseEntity<String>("User " +id + " deleted", HttpStatus.OK);
		}catch (UserNotFoundException unfe) {
			throw unfe;		
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(" Unable to delete User",
					HttpStatus.INTERNAL_SERVER_ERROR);//404-NOT_FOUND
		}
		return resp;
	}

}
