package com.educ.models;

public enum Language {
	FR("FR"), 
	EN("EN");
	
	private String value;
	Language(String value){
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	

}