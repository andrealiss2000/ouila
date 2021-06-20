package ressources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Subject;
import services.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectRessource {
	
	@Autowired
	SubjectService subjectService;
	
	
	@PostMapping("/subjects")
	public ResponseEntity<Map<String, String>> addSubject(@RequestBody Map<String, Object> classMap) {
		String classTitle = (String) classMap.get("title");
		Integer subjectId = subjectService.addSubject(classTitle);
		Map<String,String> map = new HashMap<>();
		map.put("status", "subject added successfully");
		map.put("idMatiere", subjectId.toString());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	

}
