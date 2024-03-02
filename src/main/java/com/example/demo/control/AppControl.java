package com.example.demo.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entidades.Persona;
import com.example.demo.repositorio.PersonaRepository;




@Controller
@RequestMapping("/api")
@CrossOrigin
public class AppControl {
 @Autowired PersonaRepository personaRepository;
 
	 @GetMapping("/inicio")
	    public String getAllBooks() {
	        return "hola";
	    }
	 
	 @RequestMapping(value="/listaPersona",method = RequestMethod.GET)
	 public ResponseEntity<?> listaPersona()  {
			return ResponseEntity.ok(personaRepository.findAll());
	}
	 
	 @RequestMapping(value="/buscarPorId/{id}",method = RequestMethod.GET)
	 public ResponseEntity<?> buscarPorId( @PathVariable("id") Integer id)  {
			return ResponseEntity.ok(personaRepository.findById(id));
	}
	 
	 @RequestMapping(value="/guardar",method = RequestMethod.POST)
	 public ResponseEntity<?> guardar(@RequestBody  Persona  p)  {
		 Persona p_ = new Persona();
		// Integer idPersona = personaRepository.findById(p.getId()).get().getId(); 
		 if(p.getId() !=  null ){
			 p_ = personaRepository.findById(p.getId()).get();
		 }
			BeanUtils.copyProperties(p, p_);
			Integer idPersona =	 personaRepository.saveAndFlush(p).getId();
		return ResponseEntity.ok(personaRepository.findById(idPersona));
	}
	 
}
