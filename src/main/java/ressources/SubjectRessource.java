package ressources;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectRessource {
	
	
	@PostMapping("/subjects")
	public String addSubjects(@RequestBody Map<String, Object> classMap) {
		String classTitle = (String) classMap.get("title");
		return classTitle; 
	}
	
	

}
