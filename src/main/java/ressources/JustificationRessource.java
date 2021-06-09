package ressources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Justification;
import services.JustificationService;

@RestController
@RequestMapping("/justifications")
public class JustificationRessource {

	@Autowired
	JustificationService justificationService;

	@PostMapping("")
	public ResponseEntity<Justification> addJustification(HttpServletRequest request,
			@RequestBody Map<String, Object> justificationMap) {
		return null;
	}

	@GetMapping("")
	public ResponseEntity<Justification> getJustification(HttpServletRequest request,
			@RequestBody Map<String, Object> justificationMap) {
		return null;
	}

	@GetMapping("/{justificationId}")
	public ResponseEntity<Justification> getJustificationById(HttpServletRequest request,
			@PathVariable("justificationId") Integer justificationId) {
		Justification justification = justificationService.fetchJustificationById(justificationId);
		return new ResponseEntity<>(justification, HttpStatus.OK);
	}
}
