package ressources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Justification;

@RestController
@RequestMapping("/justifications")
public class JustificationRessource {
	
	@PostMapping("")
	public ResponseEntity<Justification> addJustification(HttpServletRequest request, @RequestBody Map<String, Object> justificationMap){
		return null;
	}
	
	@GetMapping("")
	public ResponseEntity<Justification> getJustification(HttpServletRequest request, @RequestBody Map<String, Object> justificationMap){
		return null;
	}

}
