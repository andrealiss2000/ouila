package com.lpiot.ouila.ressources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpiot.ouila.domain.*;
import com.lpiot.ouila.exceptions.SubjectFormatException;
import com.lpiot.ouila.services.SClassService;
import com.lpiot.ouila.services.SubjectService;

@RestController
@RequestMapping("/")
public class SClassRessource {
	
	@Autowired
	SClassService sclassService;
/**
 * Permet d'ajouter un ou plusieurs cours en même temps
 * @param request
 * @param classMap
 * @return
 */
	@PostMapping("/classes")
	public ResponseEntity<Map<String,String>> addClass(HttpServletRequest request, @RequestBody Map<String, Object> classMap){
		
		List<LinkedHashMap<String, String>> classesList = (ArrayList<LinkedHashMap<String, String>>) classMap.get("classesList");
		Map<String,String> map = new HashMap<>();
		if(classesList != null) {
			for(LinkedHashMap<String, String> sClass : classesList) {
				String[] info = new String[4];
				info[0]=sClass.get("idMatiere");
				info[1]=sClass.get("idProf");
				info[2]=sClass.get("dateDebut");
				info[3]=sClass.get("dateFin");
				Map<String,String> hashClass = new HashMap<String, String>();
				hashClass.put("idMatiere", info[0]); 
				hashClass.put("idProf", info[1]); 
				hashClass.put("dateDebut", info[2]); 
				hashClass.put("dateFin", info[3]);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
				try {
					Date dateDebut = formatter.parse(hashClass.get("dateDebut"));
					Date dateFin = formatter.parse(hashClass.get("dateFin")); 
					Integer classId = sclassService.addSClass(Integer.valueOf(hashClass.get("idMatiere")), Integer.valueOf(hashClass.get("idProf")), dateDebut, dateFin);
					map.put(hashClass.get("idMatiere"), "Class added successfully");
				} catch (ParseException e) {
					System.out.println(e.getMessage());
					//new SubjectFormatException("Format de date non correct"); 
				}
					
			}
		}else {
			map.put("Error", "Wrong request");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	/**
	 * Permet de récupérer la liste de cours pour un utilisateur donnée
	 * @param request
	 * @param classMap
	 * @return
	 */
	@GetMapping("/classes/{idUser}")
	public ResponseEntity<List<SClass>> getClasses(HttpServletRequest request, @PathVariable("idUser") Integer userId){
		Map<String,String> map = new HashMap<>();
		if(userId != null) {
			List<SClass> classesList = sclassService.getSClassByUser(userId);
			return new ResponseEntity<>(classesList, HttpStatus.OK);
		}
			map.put("result", "Indicate id of user");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		
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
