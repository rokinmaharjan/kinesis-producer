package com.rokin.hero;

import lombok.Data;

@Data
public class Hero {
	public enum HeroType {
		AGILITY, INTELLIGENCE, STRENGTH
	}
	
	private String name;
	private HeroType type;
}
