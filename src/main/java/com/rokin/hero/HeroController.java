package com.rokin.hero;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/heroes")
public class HeroController {
	
	@Autowired
	private HeroService heroService;

	@PostMapping
	public ResponseEntity<String> addHeroes(@RequestBody List<Hero> heroes) {
		try {
			heroService.sendHeroesToKinesis(heroes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Saved heroes to kinesis", HttpStatus.OK);
	}
}
