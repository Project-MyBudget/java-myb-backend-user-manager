package br.com.mybudget.usermanager.enums;

public enum UserMaritalStatusEnum {

	SINGLE('S'),
	MARRIED('M'),
	DIVORCED('D'),
	WIDOWER('W');
	
	private char maritalStatus;

	private UserMaritalStatusEnum(char maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public char getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritinalStatus(char maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
}
