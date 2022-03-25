package com.educ.models;

public enum Level {
	BEGINNER("BEGINNER"),
	MIDDLE("MIDDLE"),
	ADVANCE("ADVANCE");
	
	private String value;
	Level(String value){
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	

}

