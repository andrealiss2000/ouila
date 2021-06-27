package ressources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpiot.ouila.Constants;

import domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import services.UserService;

@RestController
@RequestMapping("/users")
public class UserRessource {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
		String username = (String) userMap.get("username");
		String password = (String) userMap.get("password");  
		User user = userService.validateUser(username, password);
		return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
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
	
	
	private Map<String, String> generateJWTToken(User user){
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("id", user.getId())
				.claim("mail", user.getMail())
				.claim("firstname", user.getFirstname())
				.claim("surname", user.getSurname())
				.compact();
		
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		
		return map;
	}
	
	
	
	
}
