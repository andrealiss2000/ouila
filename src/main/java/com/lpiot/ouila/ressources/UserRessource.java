package com.lpiot.ouila.ressources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpiot.ouila.domain.*;

@RestController
@RequestMapping("/users")
public class UserRessource {

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
		//String username = (String) userMap.get("username");
		//String password = (String) userMap.get("password");  
		
		return null;
	}
	
	@PostMapping("")
	public ResponseEntity<User> addUser(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null;  
	}
	
	@DeleteMapping("")
	public ResponseEntity<User> deleteUser(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null; 
		 
	}
	
	@GetMapping("/students")
	public ResponseEntity<User> getStudents(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null;  
	}
	
	@GetMapping("/student")
	public ResponseEntity<User> getStudent(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null;  
	}
	
	@GetMapping("")
	public ResponseEntity<User> getUsers(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null;  
	}
	
	
	
	
	
	
	
}
