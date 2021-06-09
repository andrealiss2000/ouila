package ressources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.User;

@RestController
@RequestMapping("/classes")
public class ClassRessource {

	@PostMapping("")
	public ResponseEntity<Class> addClass(HttpServletRequest request, @RequestBody Map<String, Object> classMap){
		return null;
	}
	
	@GetMapping("")
	public ResponseEntity<Class> getClasses(HttpServletRequest request, @RequestBody Map<String, Object> classMap){
		return null;
	}
	
	@PutMapping("presences/notify")
	public ResponseEntity<User> notifyPresence(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null;  
	}
	
	@PutMapping("presences/signing")
	public ResponseEntity<User> createSigning(HttpServletRequest request, @RequestBody Map<String, Object> userMap){
		return null;  
	}
}
