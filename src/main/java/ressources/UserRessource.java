package ressources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lpiot.ouila.Constants;

import domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import services.UserService;

@Controller
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
	public ResponseEntity<Map<String, String>> addUser(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		String firstname = (String) userMap.get("firstname");
		String password = (String) userMap.get("password");  
		String username = (String) userMap.get("username");
		String surname = (String) userMap.get("lastname");  
		String mail = (String) userMap.get("email");
		int course = (int) userMap.get("course");  
		int role =  (int) userMap.get("role"); 
		
		User user = userService.addUser(firstname, surname, mail, username, password, course, role);
		Map<String, String> map = new HashMap<>();
		map.put("message", "user created !");
		return new ResponseEntity<>(map, HttpStatus.OK);  
	}
	
	@DeleteMapping("")
	public ResponseEntity<User> deleteUser(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		int id = (int) userMap.get("id");
		User user = userService.deleteUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);  
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<User>> getStudents(HttpServletRequest request, @RequestBody Map<String, Object> userMap){ 
		int idClass = (int) userMap.get("idClass");
		List<User> students = userService.getStudents(idClass);
		return new ResponseEntity<>(students, HttpStatus.OK); 
	}
	
	@GetMapping("/student")
	public ResponseEntity<User> getStudent(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		int id = (int) userMap.get("id");
		User student = userService.getStudent(id);
		return new ResponseEntity<>(student, HttpStatus.OK); 
	}
	
	@GetMapping("")
	public ResponseEntity<List<User>> getUsers(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	private Map<String, String> generateJWTToken(User user){
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("id", user.getId())
				.claim("email", user.getMail())
				.claim("firstname", user.getFirstname())
				.claim("lastname", user.getLastName())
				.compact();
		
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		
		return map;
	}
	
	
	
	
}
